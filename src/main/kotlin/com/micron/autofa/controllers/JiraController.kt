package com.micron.autofa.controllers

import com.micron.autofa.models.Jira
import com.micron.autofa.services.JiraService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8080", "http://10.107.38.76:8080", "lmt-z270-u28:8080"], maxAge = 3000)
@RestController
@RequestMapping("/jira")
class JiraController(private val jiraService: JiraService) {
    @PostMapping("/create")
    fun createJira(@RequestBody jira: Jira) =
            jiraService.add(jira)

    @GetMapping("/{id}")
    fun getJira(@PathVariable("id") id: String): Jira =
            jiraService.get(id)

    @PutMapping("/{id}")
    fun updateJira(@PathVariable("id") id: String, @RequestBody jira: Jira) =
            jiraService.update(jira)

    @DeleteMapping("/{id}")
    fun deleteJira(@PathVariable("id") id: String) =
            jiraService.delete(id)

    @GetMapping("/like/{term}")
    fun jirasMatching(@PathVariable("term") term: String): List<Jira> {
        return jiraService.jirasMatching(term)
    }
}