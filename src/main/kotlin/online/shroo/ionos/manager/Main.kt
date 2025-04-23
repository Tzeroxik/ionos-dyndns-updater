package online.shroo.ionos.manager

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import online.shroo.ionos.manager.configuration.getConfiguration
import online.shroo.ionos.manager.ionos.activateDynamicDns
import online.shroo.ionos.manager.ionos.dns.ddns.DynDnsRequest
import online.shroo.ionos.manager.ionos.http.DEFAULT_HTTP_CLIENT
import online.shroo.ionos.manager.ionos.updateDomainIp

private val LOG = KotlinLogging.logger {}

suspend fun main(args: Array<String>) {
    LOG.info { "Starting program..." }

    val configuration =
        getConfiguration("configuration.toml")
            ?: throw IllegalStateException("configuration.toml not found")

    val client = DEFAULT_HTTP_CLIENT

    val updateFrequency =
        configuration.update.frequency.coerceAtLeast(120000L)

    for (domain in configuration.domains) {
        coroutineScope {
            val domainDescription = domain.description
            LOG.info { "Starting update scope for domain: $domainDescription" }
            val apiKey = domain.apiKey
            val request = DynDnsRequest(domain.domains, domain.description)

            LOG.info { "Activating Dynamic DNS for $domainDescription" }
            val dynamicDns = try {
                client.activateDynamicDns(apiKey, request)
            } catch (e: Exception) {
                LOG.error(e) {
                    "Failed to activate dynamic dns after retries expired, delaying crash fo 2 minutes"
                }
                // delay exception
                delay(configuration.update.frequency)
                LOG.info { "Crashing on $domainDescription context" }
                throw e
            }

            while (true) {
                LOG.info { "Updating DNS for $domainDescription" }
                try {
                    client.updateDomainIp(apiKey, dynamicDns.updateUrl)
                } catch (e: Exception) {
                    LOG.error(e) { "Error during domain update" }
                }
                LOG.info { "Update iteration finished for $domainDescription" }
                delay(updateFrequency)
            }
        }
    }
}