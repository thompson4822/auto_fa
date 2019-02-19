package com.micron.autofa.generators

import com.micron.autofa.models.ElasticSearchEntry
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
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

    /**
     * Really handy extension function to retrieve a random item from a list of items.
     */
    fun <T> List<T>.random() = this[Random.nextInt(this.size)]


    // I thought this would be useful, but now I'm not so sure ...
    fun List<String>.randomUniqueList(min: Int, max: Int): List<String> {
        val result = mutableSetOf<String>()
        val length = Random.nextInt(min, max)
        while(result.size < length)
            result.add(this.random())
        return result.toList()
    }

    /**
     * Create a random list of Jenkins job names
     */
    fun generateJenkinsJobs(count: Int): List<String> {
        val names = listOf("RAPTOR", "HAPS", "Common", "Utility", "Nightly", "Matrix", "Evaluation",
                "OpenShift", "Test", "CodeStyle", "Integration", "FTE", "PVE", "LMT", "MP", "Overall",
                "Incident", "Verification", "FPGA", "GenSim", "FTL", "Serial", "Parallel", "Bypass",
                "Connector", "Target", "Source")

        // Logic here ...
        fun generateJobName(length: Int) = (1..length).map { _ -> names.random() }.joinToString("-")
        return (0..count).map { _ -> generateJobName(Random.nextInt(2, 4)) }
    }

    /**
     * Create a random list of test script names, with up to 3 extensions for some variety
     */
    fun generateScriptNames(count: Int): List<String> {
        val first = listOf("intense", "automatic", "clown", "mongoose", "beast", "snake", "weasel", "wicked", "cruel", "slow", "screeching", "meticulous")
        val second = listOf("hammer", "defenestrator", "injector", "ripper", "dismantler", "charmer", "crippler", "stabber", "poker", "pounder", "jabber", "breaker", "rifle", "shotgun", "incinerator", "blender", "grinder")
        val extensions = listOf("py", "tcl", "sh")

        return (0..count).map { _ -> "${first.random()}_${second.random()}.${extensions.random()}"}
    }

    /**
     * Create a sort of random list of test station names
     */
    fun generateTestStations(count: Int): List<String> {
        // Logic here ...
        val start = Random.nextInt(100, 300)
        return (0..count).map{"lmt-z270-fa${start + it}"}
    }

    /**
     * Create a random list of believable SHA values
     */
    fun generateShas(count: Int): List<String> {
        val values = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")
        fun createSha() = (0..7).map { values.random() }.joinToString("")
        return (0..count).map { createSha() }
    }

    /**
     * Randomly create a date between the given start and end dates
     */
    fun generateDateCreated(dateStart: LocalDate, dateEnd: LocalDate): LocalDate {
        val defaultZoneId = ZoneId.systemDefault()
        val starting = Date.from(dateStart.atStartOfDay(defaultZoneId).toInstant())
        val ending = Date.from(dateEnd.atStartOfDay(defaultZoneId).toInstant())
        val period = ending.time - starting.time
        // Very fuggly. Thanks on this one go to the stellar job Java did to totally f-up dates!
        return Date(starting.time + Random.nextLong(period)).toInstant().atZone(defaultZoneId).toLocalDate()
    }

    /*
    An example of how the above might be called:
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
	val startDate = LocalDate.parse("2019/02/10", formatter)
	val endDate = LocalDate.parse("2019/02/26", formatter)
	(0..10).forEach { println(Generators.generateDateCreated(startDate, endDate)) }

     */

    /**
     * Randomly select a message to be associated with a failure
     */
    fun generateMainFailMessage() = listOf(
        "Out Of Memory Error",
        "Stack Overflow",
        "Index Out Of Bounds",
        "Null Pointer Exception",
        "Disk Space Full",
        "Buffer Overrun",
        "Buffer Underrun",
        "Unspecified Memory Error",
        "Core Dump",
        "Device Not Found",
        "Network Unavailable",
        "Halt and Catch Fire",
        "Smoking Capacitors Detected On Motherboard",
        "FPGA Malfunction",
        "Unexpected Serial Port Hang Up",
        "Checksum Failed",
        "Maximum Timeout Period Exceeded",
        "File Not Found",
        "Bad Instruction",
        "Premature Code Exit",
        "General Failure"
    ).random()


    /**
     * Randomly select one or more sources
     */
    fun generateSources(): List<String> {
        val sources = listOf("FTE", "Jenkins(AM)", "PVE", "CVE")
        return (0..Random.nextInt(1, sources.size)).map{ sources.random() }
    }

    /**
     * Ouch. This one might be a PITA
     */
    fun generateLog(): List<String> {
        val messages = listOf(
                "quis nostrud indoles nec nam.",
                "conubia indoles nullam abluo posuere consectetur. secundum nutus pagus dapibus transverbero consequat esse abigo ille.",
                "rutrum gemino urna modo cras. dapibus posuere cui camur quidne.",
                "dolor suscipit pagus iaceo te opes gemino macto. ipsum patria ingenium macto hendrerit vestibulum convallis.",
                "quia sagittis faucibus egestas dictumst tincidunt. gravis nimis lucidus foras magna paulatim varius.",
                "esse huic adipiscing vulputate causa wisi vero.",
                "dictumst eu minim mollis a volutpat meus damnum. usitas zelus valetudo paratus nullam tego.",
                "mos aptent abluo dui torqueo fere scisco.",
                "velit cursus nostrud maecenas luptatum jumentum.",
                "tempor ultrices bibendum opes sodales odio vel indoles. epulae odio quidne dictum accumsan.",
                "demoveo orem phasellus vestibulum rusticus.",
                "non premo patria tego bibendum eleifend parturient. macto conventio opto commoveo egestas tum.",
                "mattis virtus probo exerci vulputate nibh cursus ornare inceptos. bibendum esca causa refero veniam refoveo.",
                "appellatio adsum luctus curabitur si commoveo.",
                "ventosus fringilla obruo illum voco ex scisco neo. tellus posuere tation esca ex incassum ludus.",
                "dictum fusce pellentesque feugiat quidne ut in. magnis fermentum et pagus conventio id tum patria.",
                "singularis dictumst sagaciter lenis laoreet vicis nibh magnis vulpes. cum voco elementum viverra nibh camur odio bibendum.",
                "ingenium vicis pala similis sociosqu ex congue causa.",
                "paratus sudo quidem si nisl pneum consectetuer.",
                "est consectetuer vel saluto porttitor exerci lacus.",
                "wisi macto ligula humo hendrerit sit. mus sociosqu posuere libero meus scisco.",
                "vehicula tempor ulciscor iusto erat. nam orem consequat jugis esse acsi iustum indoles.",
                "ridiculus meus modo ipsum venenatis indoles. ornare camur cui lenis melior.",
                "etiam ymo veniam molestie torqueo dignissim. fringilla hendrerit dictum qui vero appellatio.",
                "regula cursus congue antehabeo decet. id eum blandit scisco tego quis.",
                "sociis foras pertineo voco imperdiet immitto.",
                "quidem pertineo loquor habitasse similis aliquip habitasse ratis aliquet.",
                "indoles nisi diam meus maecenas convallis iriure tortor platea.",
                "camur dignissim vereor vero letatio importunus pertineo.",
                "morbi class voco ea torqueo rusticus. indoles valetudo conubia quidne rhoncus sapien a.",
                "refero orci nulla commodo dictum orci.",
                "dui molior euismod facilisi iaculis conubia mattis. luctus nullus aliquip magnis distineo comis gravis sapien abdo.",
                "magna ideo odio premo nimis fere utrum.",
                "magnis molestie ea montes qui.",
                "mauris wisi taciti mus imperdiet scisco ibidem.",
                "litora tempus plaga vulputate gravida proin.",
                "mus dignissim vestibulum foras urna magnis.",
                "hos venenatis wisi blandit gravida.",
                "massa luptatum distineo quidne duis nonummy pharetra.",
                "tamen pneum ulciscor mi virtus paratus ultricies.",
                "vulputate a id ideo plaga nonummy nonummy penatibus. molior ac sollicitudin gemino pecus populus fringilla neque semper.",
                "saluto esse non curabitur haero habitasse. veniam ac mus leo nec letatio laoreet sed.",
                "caecus huic multo persto lucidus nec neo.",
                "morbi tellus ventosus autem eros opes mauris.",
                "eget venio eleifend at sudo suscipere abdo. sudo tation neo valetudo ille rhoncus.",
                "consequat proprius patria obruo pertineo conventio nisi. ante sollicitudin tempor rusticus montes pretium opto brevitas.",
                "pagus suspendisse dolore vulputate fusce regula luctus. viverra sed rusticus minim natu caecus vitae.",
                "eligo torqueo haero sudo opes blandit paulatim. nimis scisco sociosqu iusto euismod antehabeo.",
                "cum dis vestibulum praesent plaga ibidem donec.",
                "quidem fusce sociosqu torquent ea jus torqueo. plaga esse aliquet praemitto lobortis.",
                "purus hendrerit commoveo brevitas epulae per jus.",
                "felis ante paratus phasellus olim plaga.",
                "imputo immitto ulciscor cogo eget saepius.",
                "curabitur delenit facilisi parturient nunc.",
                "pretium sollicitudin ymo pecus voco utinam quidem.",
                "montes hos ad plaga praesent risus.",
                "ridiculus consectetur phasellus wisi iriure consectetuer jugis nec condimentum. accumsan himenaeos paulatim demoveo virtus viverra facilisis iusto.",
                "transverbero antehabeo quadrum et augue eleifend magna.",
                "quae mara justo dis tempor saepius congue wisi. paratus lacus ad tellus donec paratus.",
                "zelus pagus nec tortor volutpat bene torquent.",
                "elit amet aptent macto roto fermentum.",
                "penatibus brevitas defui regula fermentum risus. conventio nulla magna ullamcorper refero feugait.",
                "mara risus mara habitasse eros lacus viverra. venio feugait nulla premo accumsan autem lucidus.",
                "indoles probo tum cui epulae accumsan abigo rutrum interdico illum.",
                "hos quidne lenis erat abluo maecenas pertineo enim.",
                "vero epulae autem quis nostrud qui natu diam. natu amet veniam opes caecus damnum.",
                "exerci delenit orci macto nibh imputo saepius pretium laoreet.",
                "imperdiet sociosqu quam gilvus ex sagittis ac curabitur. ad purus aliquam felis lobortis hendrerit.",
                "consectetuer facilisi blandit semper molestie. tempus urna eligo plaga luctus aliquam risus.",
                "lucidus capto lenis nutus semper.",
                "metuo ne virtus ante sino eu.",
                "opes metuo porta iriure bene causa.",
                "montes sodales ut qui amet decet.",
                "nascetur parturient purus appellatio mattis letatio augue genitus."
        )
        val messageType = listOf(" INFO", " WARN", "DEBUG", "ERROR")
        return (0..20).map { "2019-02-19 08:30:33.907  ${messageType.random()} --- ${messages.random()}" }
    }

    fun generateElasticSearchEntry(): ElasticSearchEntry {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val startDate = LocalDate.parse("2019/02/01", formatter)
        val endDate = LocalDate.parse("2019/02/28", formatter)
        return ElasticSearchEntry(
                jenkinsJob = jenkinsJobs.random(),
                jenkinsBuild = "${Random.nextInt(100, 200)}",
                script = scriptNames.random(),
                testStation = testStations.random(),
                dateCreated = generateDateCreated(startDate, endDate).toString(),
                mainFailMessage = generateMainFailMessage(),
                sha = shas.random(),
                sources = generateSources(),
                log = generateLog()
        )
    }
}
