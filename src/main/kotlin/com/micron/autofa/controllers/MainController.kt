package com.micron.autofa.controllers

import com.micron.autofa.models.ElasticSearchFilter
import org.springframework.web.bind.annotation.*

data class Greeting(val content: String)

data class Acknowledgement(val response: String = "I got your criteria", val criteria: ElasticSearchFilter)


@CrossOrigin(origins = ["http://localhost:8080"], maxAge = 3000)
@RestController
@RequestMapping("/auto_fa")
class MainController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting("Hello, $name")

    @PostMapping("/search-criteria")
    fun searchCriteria(@RequestBody criteria: ElasticSearchFilter): Acknowledgement =
            Acknowledgement("The Rain In Spain Falls Mainly On My Brain", criteria)

}