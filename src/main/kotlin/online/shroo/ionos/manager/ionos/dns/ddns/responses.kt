package online.shroo.ionos.manager.ionos.dns.ddns

import kotlinx.serialization.Serializable

@Serializable
data class DynDns(
    val bulkId: String,
    val updateUrl: String,
    val domains: List<String>,
    val description: String,
)