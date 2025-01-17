package com.micron.autofa.controllers

import com.micron.autofa.models.*
import com.micron.autofa.services.ElasticSearchService
import com.micron.autofa.services.JiraService
import org.springframework.web.bind.annotation.*

data class Greeting(val content: String)

data class Acknowledgement(val response: String = "I got your criteria", val criteria: ElasticSearchFilter)

data class DateCriteria(val startDate: String, val endDate: String)



@CrossOrigin(origins = ["http://localhost:8080", "http://10.107.38.76:8080", "lmt-z270-u28:8080"], maxAge = 3000)
@RestController
@RequestMapping("/elastic-search")
class ElasticSearchController(private val elasticSearchService: ElasticSearchService, private val jiraService: JiraService) {

    @PostMapping("/for-dates")
    fun forDates(@RequestBody dates: DateCriteria): List<ElasticSearchEntry> =
        elasticSearchService.findBetweenDates(dates.startDate, dates.endDate)

    @PostMapping("/find-tree")
    fun findTree(@RequestBody filter: ElasticSearchFilter): ElasticSearchResult =
        elasticSearchService.findTree(filter)

    @PostMapping("/retrieve-logs")
    fun retrieveLogs(@RequestBody logIds: LogIDs): LogMessages =
        elasticSearchService.retrieveLogs(logIds)


}