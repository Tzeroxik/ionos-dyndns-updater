package online.shroo.ionos.manager.ionos.dns.ddns

import kotlinx.serialization.Serializable

@Serializable
data class DynDnsRequest(
    val domains: List<String>,
    val description: String,
)