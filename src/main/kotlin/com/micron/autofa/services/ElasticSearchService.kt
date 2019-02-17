package com.micron.autofa.services

import com.micron.autofa.models.ElasticSearchFilter
import com.micron.autofa.models.ElasticSearchResult
import org.springframework.stereotype.Service

interface ElasticSearchService {
    fun find(filter: ElasticSearchFilter): ElasticSearchResult
    fun findLog(id: String): String
}

@Service
class FakeElasticSearchService : ElasticSearchService {
    override fun find(filter: ElasticSearchFilter): ElasticSearchResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findLog(id: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}