package com.micron.autofa.generators

import java.time.LocalDate
import kotlin.random.Random

object Generators {
    val jenkinsJobs = generateJenkinsJobs(30)
    var jenkinsBuilds = mutableMapOf<String, Int>()
    val scriptNames = generateScriptNames(10)
    val testStations = generateTestStations(10)
    val shas = generateShas(10)
    val random: Random = Random(System.currentTimeMillis())

    init {

    }

    fun generateJenkinsJobs(count: Int): List<String> {
        val names = listOf("RAPTOR", "HAPS", "Common", "Utility", "Nightly", "Matrix", "Evaluation",
                "OpenShift", "Test", "CodeStyle", "Integration", "FTE", "PVE", "LMT", "MP", "Overall",
                "Incident", "Verification", "FPGA", "GenSim", "FTL", "Serial", "Parallel", "Bypass",
                "Connector", "Target", "Source")

        // Logic here ...
        fun generateJobName(length: Int) = (1..length).map { _ -> names[Random.nextInt(names.size)] }.joinToString("-")
        return (0..count).map { _ -> generateJobName(Random.nextInt(2, 4)) }
    }

    fun generateScriptNames(count: Int): List<String> {
        // Logic here ...
        return listOf()
    }

    fun generateTestStations(count: Int): List<String> {
        // Logic here ...
        return listOf()
    }

    fun generateShas(count: Int): List<String> {
        // Logic here ...
        return listOf()
    }

    fun generateDateCreated(dateStart: LocalDate, dateEnd: LocalDate) {

    }

    fun generateMainFailMessage() {

    }

    fun generateSource() {

    }

    fun generateLog() {

    }

}