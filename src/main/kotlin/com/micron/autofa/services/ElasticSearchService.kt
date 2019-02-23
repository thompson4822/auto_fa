package com.micron.autofa.services

import com.micron.autofa.models.*
import com.micron.autofa.repositories.ElasticSearchRepository
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale


const val MainFailMessage = "Main Fail Message"
const val ScriptName = "Script Name"
const val TestStation = "Test Station/SSN"
const val SHA = "SHA"
const val Build = "Build"

interface ElasticSearchService {
    fun findTree(filter: ElasticSearchFilter): ElasticSearchResult
    fun findLog(id: String): String
    fun findBetweenDates(startDate: String, endDate: String): List<ElasticSearchEntry>
    fun retrieveLogs(logIds: LogIDs): LogMessages
}


@Service
class FakeElasticSearchService(private val repository: ElasticSearchRepository) : ElasticSearchService {
    private fun String.toDate(): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return format.parse(this)
    }

    private fun today(): Date {
        val today = Calendar.getInstance()
        today.set(Calendar.HOUR_OF_DAY, 0)
        return today.time
    }

    private fun findChildren(records: List<ElasticSearchEntry>, childType: String, includeId: Boolean = false): Map<String, List<ElasticSearchEntry>> {
        return when(childType) {
            MainFailMessage -> records.groupBy { it.mainFailMessage }
            ScriptName -> records.groupBy { it.script }
            TestStation -> records.groupBy { it.testStation }
            SHA -> records.groupBy { it.sha }
            Build -> records.groupBy { it.jenkinsBuild }
            else -> throw IllegalArgumentException("I have no idea what '$childType' is!")
        }
    }

    private fun treeName(entry: ElasticSearchEntry, key: String) = when(key) {
        MainFailMessage -> entry.mainFailMessage
        ScriptName -> entry.script
        TestStation -> entry.testStation
        SHA -> entry.sha
        Build -> entry.jenkinsBuild
        else -> throw IllegalArgumentException("I have no idea what '$key' is!")
    }


    private fun treeTypeMap(key: String) = when(key) {
        MainFailMessage -> "error"
        ScriptName -> "script"
        TestStation -> "station"
        SHA -> "SHA"
        Build -> "build"
        else -> throw IllegalArgumentException("I have no idea what '$key' is!")
    }

    /**
     * Retrieve the logs by the given ids
     */
    override fun retrieveLogs(logIds: LogIDs): LogMessages {
        return LogMessages(messages = repository.findAllById(logIds.ids).map{ LogMessage(id = it.id!!, log = it.log) })
    }

    /**
     * Get the records that match the search filter and build a tree (result) that can be displayed in the browser.
     */
    override fun findTree(filter: ElasticSearchFilter): ElasticSearchResult {
        // If the dates are null, set them to today's date ...
        var records = repository.findByDateCreatedBetween(filter.startDate?.toDate() ?: today(), filter.endDate?.toDate() ?: today())

        // If the filter is limited to particular sources, filter out anything that doesn't match
        if(filter.sources.isNotEmpty()) {
           records = records.filter { it.sources.intersect(filter.sources).isNotEmpty() }
        }
        
        val keys = listOf(filter.primaryKey) + filter.secondaryKeys

        // On the front end, every node in the tree needs a unique id. This will be ours for all 1st and 2nd level nodes.
        // It is basically a hack.
        var id = 0;

        // NOTE - One of the nice things about this approach is that there could be an arbitrary number of levels and it would still work.
        fun findTreeRec(records: List<ElasticSearchEntry>, keys: List<String>): List<SearchNode> {
            return if(keys.size > 1) {
                findChildren(records, keys[0]).map { (name, entries) -> SearchNode(id = "${id++}", name = name, type=treeTypeMap(keys[0]), children = findTreeRec(entries, keys.drop(1))) }
            }
            else {
                records.map{ SearchNode(id = it.id, name=treeName(it, keys[0]), type=treeTypeMap(keys[0])) }
            }
        }
        return ElasticSearchResult(nodes = findTreeRec(records, keys))
    }

    override fun findLog(id: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findBetweenDates(startDate: String, endDate: String): List<ElasticSearchEntry> =
            repository.findByDateCreatedBetween(startDate.toDate(), endDate.toDate())

}
