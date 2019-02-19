package com.micron.autofa.repositories

import com.micron.autofa.models.ElasticSearchEntry
import org.springframework.data.mongodb.repository.MongoRepository


// No need implementation, just one interface, and you have CRUD, thanks Spring Data
interface ElasticSearchRepository : MongoRepository<ElasticSearchEntry, String> {

/*
    fun findFirstByDomain(domain: String): Domain

    fun findByDomainAndDisplayAds(domain: String, displayAds: Boolean): Domain

    //Supports native JSON query string
    @Query("{domain:'?0'}")
    fun findCustomByDomain(domain: String): Domain

    @Query("{domain: { \$regex: ?0 } })")
    fun findCustomByRegExDomain(domain: String): List<Domain>
*/
    
    fun findDistinctByScript(): List<String>

}
