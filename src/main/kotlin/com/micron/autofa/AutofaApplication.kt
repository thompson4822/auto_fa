package com.micron.autofa

import com.micron.autofa.generators.Generators
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutofaApplication

fun main(args: Array<String>) {
	println(Generators.jenkinsJobs)
	runApplication<AutofaApplication>(*args)
}
