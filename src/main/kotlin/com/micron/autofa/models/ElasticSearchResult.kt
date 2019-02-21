package com.micron.autofa.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class SearchNode (
    val id: String? = null,
    val name: String,
    val type: String,
    val children: List<SearchNode>
)

data class ElasticSearchResult(val nodes: List<SearchNode>)

