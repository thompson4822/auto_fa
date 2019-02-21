package com.micron.autofa

import com.micron.autofa.generators.Generators
import com.micron.autofa.repositories.ElasticSearchRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AutofaApplication {
	@Bean
	fun initialize(repository: ElasticSearchRepository)= CommandLineRunner {
		if(repository.count() == 0L) {
			println("Please hold on a minute ... adding records!")
			repository.insert((0..10000).map { Generators.generateElasticSearchEntry() })
		}
		println("Database ready!")
	}
}

fun main(args: Array<String>) {
	runApplication<AutofaApplication>(*args)
}
