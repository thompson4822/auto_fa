package com.micron.autofa.repositories

import com.micron.autofa.models.Jira
import org.springframework.data.mongodb.repository.MongoRepository

interface JiraRepository : MongoRepository<Jira, String>