package online.shroo.ionos.manager

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import online.shroo.ionos.manager.configuration.getResource
import online.shroo.ionos.manager.configuration.toml.Configuration

private val LOG = KotlinLogging.logger {}
private val TOML_MAPPER = TomlMapper()

suspend fun main(args: Array<String>) {
    LOG.info { "Starting program..." }

    val configuration =
        getResource<Configuration>("configuration.toml", TOML_MAPPER)
            ?: throw IllegalStateException("configuration.toml not found")

}