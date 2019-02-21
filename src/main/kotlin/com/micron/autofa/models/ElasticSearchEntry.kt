package com.micron.autofa.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class ElasticSearchEntry(
        @Id val id: String? = null,
        val jenkinsJob: String,
        val jenkinsBuild: String,
        val script: String,
        val testStation: String,
        val dateCreated: Date,
        val mainFailMessage: String,
        val sha: String,
        val sources: List<String>,
        val log: List<String>
)