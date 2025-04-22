import io.github.oshai.kotlinlogging.KotlinLogging
import online.shroo.ionos.manager.configuration.getResource
import online.shroo.ionos.manager.configuration.toml.Configuration
import online.shroo.ionos.manager.configuration.toml.DomainConfiguration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ConfigurationTests {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `test configuration loads`() {
        val expected = Configuration(
            listOf(DomainConfiguration(
                apiKey="prefix.secret",
                userId="012345679",
                domains=listOf("www.xpto.com", "xpto.com")
            )),
        )

        val configName = "configuration.example1.toml"
        val config = getResource<Configuration>(configName)
        assertNotNull(config)
        
        assertEquals(expected, config)

        logger.info { config }
    }
}