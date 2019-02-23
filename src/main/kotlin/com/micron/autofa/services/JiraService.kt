package com.micron.autofa.services

import com.micron.autofa.models.Jira
import com.micron.autofa.repositories.JiraRepository
import org.springframework.stereotype.Service

interface JiraService {
    fun matching(jiraName: String): List<String>
    fun jira(jiraName: String): Jira
    fun add(jira: Jira): Unit
    fun delete(id: String): Unit
    fun update(jira: Jira): Unit
    fun get(id: String): Jira
    fun jirasMatching(term: String): List<Jira>
}

@Service
class FakeJiraService(private val jiraRepository: JiraRepository) : JiraService {
    override fun matching(jiraName: String): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jira(jiraName: String): Jira {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(jira: Jira): Unit {
        jiraRepository.insert(jira)
    }

    override fun delete(id: String) {
        jiraRepository.deleteById(id)
    }

    override fun update(jira: Jira) {
        jiraRepository.save(jira)
    }

    override fun get(id: String): Jira {
        // TODO - The following is *NOT* safe. Better to throw an exception if the optional doesn't return anything.
        return jiraRepository.findById(id).get()
    }

    override fun jirasMatching(term: String): List<Jira> {
        return jiraRepository.findAll().filter { it.name.startsWith(term) }
    }

}