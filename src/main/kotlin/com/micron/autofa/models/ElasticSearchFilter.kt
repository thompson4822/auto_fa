package com.micron.autofa.models

data class ElasticSearchFilter(
        val bySHA: Boolean?,
        val startDate: String?,
        val endDate: String?,
        val primaryKey: String,
        val secondaryKeys: List<String>,
        val sources: List<String>
)