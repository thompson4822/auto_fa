package com.micron.autofa.services

import com.micron.autofa.models.Jira
import org.springframework.stereotype.Service

interface JiraService {
    fun matching(jiraName: String): List<String>
    fun jira(jiraName: String): Jira
    fun add(jira: Jira): Jira
    fun remove(id: String): Unit
}

@Service
class FakeJiraService : JiraService {
    override fun matching(jiraName: String): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jira(jiraName: String): Jira {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(jira: Jira): Jira {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}