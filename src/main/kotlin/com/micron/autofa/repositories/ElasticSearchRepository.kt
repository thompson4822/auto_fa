package com.micron.autofa.repositories

import com.micron.autofa.models.ElasticSearchEntry
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ElasticSearchRepository : MongoRepository<ElasticSearchEntry, String> {
    fun findDistinctByScript(): List<String>

    fun findByDateCreatedBetween(startDate: Date, endDate: Date): List<ElasticSearchEntry>


}