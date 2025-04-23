import io.github.oshai.kotlinlogging.KotlinLogging
import online.shroo.ionos.manager.configuration.getConfiguration
import online.shroo.ionos.manager.configuration.toml.Configuration
import online.shroo.ionos.manager.configuration.toml.Domains
import online.shroo.ionos.manager.configuration.toml.UpdateConfiguration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ConfigurationTests {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `test configuration loads`() {
        val expected = Configuration(
            update = UpdateConfiguration(
                frequency = 900000L,
            ),
            domains = listOf(
                Domains(
                    apiKey = "prefix.secret",
                    domains = listOf("www.xpto.com", "xpto.com"),
                    description = "xpto"
                )
            ),
        )

        val configName = "configuration.example1.toml"
        val config = getConfiguration(configName)
        assertNotNull(config)

        assertEquals(expected, config)

        logger.info { config }
    }
}