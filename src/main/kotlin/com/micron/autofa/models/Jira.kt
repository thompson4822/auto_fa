package com.micron.autofa.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class JiraUser(val jiraId: String)

data class JiraComment(val creator: JiraUser, val text: String, val creationDate: String)

@Document(collection = "jira")
data class Jira(
        @Id val id: Long? = null,
        val name: String,
        val description: String,
        val comments: List<JiraComment>,
        val creationDate: String
)