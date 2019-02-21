package com.micron.autofa.services

import com.micron.autofa.models.ElasticSearchEntry
import com.micron.autofa.models.ElasticSearchFilter
import com.micron.autofa.models.ElasticSearchResult
import com.micron.autofa.models.SearchNode
import com.micron.autofa.repositories.ElasticSearchRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.Locale
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.util.Calendar





interface ElasticSearchService {
    fun findTree(filter: ElasticSearchFilter): ElasticSearchResult
    fun findLog(id: String): String
    fun findBetweenDates(startDate: String, endDate: String): List<ElasticSearchEntry>
}


const val MainFailMessage = "Main Fail Message"
const val ScriptName = "Script Name"
const val TestStation = "Test Station/SSN"
const val SHA = "SHA"
const val Build = "Build"
/*
    TestScriptFailMessage("Test Script Fail Message"),
    JIRA("JIRA"),
    DriveCapacity("Drive Capacity"),
    FTE("FTE"),
    PVE("PVE"),
    CVE("CVE"),
*/


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

    fun treeName(entry: ElasticSearchEntry, key: String) = when(key) {
        MainFailMessage -> entry.mainFailMessage
        ScriptName -> entry.script
        TestStation -> entry.testStation
        SHA -> entry.sha
        Build -> entry.jenkinsBuild
        else -> throw IllegalArgumentException("I have no idea what '$key' is!")
    }


    fun treeTypeMap(key: String) = when(key) {
        MainFailMessage -> "error"
        ScriptName -> "script"
        TestStation -> "station"
        SHA -> "SHA"
        Build -> "build"
        else -> throw IllegalArgumentException("I have no idea what '$key' is!")
    }

    /**
     * Get the records that match the search filter and build a tree (result) that can be displayed in the browser.
     */
    override fun findTree(filter: ElasticSearchFilter): ElasticSearchResult {
        // If the dates are null, set them to today's date ...
        val records = repository.findByDateCreatedBetween(filter.startDate?.toDate() ?: today(), filter.endDate?.toDate() ?: today())
        val keys = listOf(filter.primaryKey) + filter.secondaryKeys

        // On the front end, every node in the tree needs a unique id. This will be ours for all 1st and 2nd level nodes
        var id = 0;

        fun findTreeRec(records: List<ElasticSearchEntry>, keys: List<String>): List<SearchNode> {
            return if(keys.size > 1) {
                findChildren(records, keys[0]).map { (name, entries) -> SearchNode(id = "${id++}", name = name, type=treeTypeMap(keys[0]), children = findTreeRec(entries, keys.drop(1))) }
            }
            else {
                records.map{ SearchNode(id = it.id, name=treeName(it, keys[0]), type=treeTypeMap(keys[0]), children=listOf()) }
            }
        }
        return ElasticSearchResult(nodes = findTreeRec(records, keys))
    }



    /*
    data class SearchNode (
        val id: String?,
        val name: String,
        val type: String,
        val children: List<SearchNode>
    )

    data class ElasticSearchResult(val nodes: List<SearchNode>)
     */


    override fun findLog(id: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findBetweenDates(startDate: String, endDate: String): List<ElasticSearchEntry> =
        repository.findByDateCreatedBetween(startDate.toDate(), endDate.toDate())

}