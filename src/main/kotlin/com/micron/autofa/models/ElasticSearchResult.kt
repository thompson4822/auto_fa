package com.micron.autofa.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class SearchNode (
    val id: String?,
    val name: String,
    val type: String,
    val children: List<SearchNode>
)

data class ElasticSearchResult(val nodes: List<SearchNode>)

@Document
data class ElasticSearchEntry(
    @Id val id: Long? = null,
    val jenkinsJob: String,
    val jenkinsBuild: String,
    val script: String,
    val testStation: String,
    val dateCreated: String,
    val mainFailMessage: String,
    val sha: String,
    val source: String,
    val log: String
)