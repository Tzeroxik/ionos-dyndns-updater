package online.shroo.ionos.manager.ionos

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import online.shroo.ionos.manager.ionos.dns.ddns.DynDns
import online.shroo.ionos.manager.ionos.dns.ddns.DynDnsRequest
import online.shroo.ionos.manager.ionos.http.deserialize
import online.shroo.ionos.manager.ionos.http.xApiKey

private const val BASE_URL: String = "https://api.hosting.ionos.com"

suspend fun HttpClient.activateDynamicDns(apiKey: String, request: DynDnsRequest, baseUrl: String = BASE_URL): DynDns {
    val response = post("$baseUrl/dns/v1/dyndns") {
        xApiKey(apiKey)
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.deserialize<DynDns>()
}

suspend fun HttpClient.updateDomainIp(apiKey: String, url: String): HttpStatusCode {
    val response = get(url) {
        xApiKey(apiKey)
    }
    return response.status
}