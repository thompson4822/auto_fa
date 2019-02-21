package com.micron.autofa.controllers

import com.micron.autofa.models.ElasticSearchEntry
import com.micron.autofa.models.ElasticSearchFilter
import com.micron.autofa.models.ElasticSearchResult
import com.micron.autofa.services.ElasticSearchService
import org.springframework.web.bind.annotation.*

data class Greeting(val content: String)

data class Acknowledgement(val response: String = "I got your criteria", val criteria: ElasticSearchFilter)

data class DateCriteria(val startDate: String, val endDate: String)


@CrossOrigin(origins = ["http://localhost:8080", "http://10.107.38.76:8080", "lmt-z270-u28:8080"], maxAge = 3000)
@RestController
@RequestMapping("/auto_fa")
class MainController(private val service: ElasticSearchService) {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting("Hello, $name")

    @PostMapping("/search-criteria")
    fun searchCriteria(@RequestBody criteria: ElasticSearchFilter): Acknowledgement =
        Acknowledgement("The Rain In Spain Falls Mainly On My Brain", criteria)

    @PostMapping("/for-dates")
    fun forDates(@RequestBody dates: DateCriteria): List<ElasticSearchEntry> =
        service.findBetweenDates(dates.startDate, dates.endDate)

    @PostMapping("/find-tree")
    fun findTree(@RequestBody filter: ElasticSearchFilter): ElasticSearchResult =
        service.findTree(filter)

}