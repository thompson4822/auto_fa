package com.micron.autofa.models

data class SearchNode (
    val id: String?,
    val name: String,
    val type: String,
    val children: List<SearchNode>
)

data class ElasticSearchResult(val nodes: List<SearchNode>)
