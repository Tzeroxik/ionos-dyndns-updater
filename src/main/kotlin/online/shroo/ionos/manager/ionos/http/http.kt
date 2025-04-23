package online.shroo.ionos.manager.ionos.http

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.jetty.jakarta.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import online.shroo.ionos.manager.ionos.general.exceptions.Error
import online.shroo.ionos.manager.ionos.general.exceptions.ErrorResponseException

val DEFAULT_HTTP_CLIENT: HttpClient = HttpClient(Jetty) {
    install(ContentNegotiation) {
        json()
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 10000
        connectTimeoutMillis = 5000
        socketTimeoutMillis = 15000
    }
    install(HttpRequestRetry) {
        retryOnServerErrors(maxRetries = 5)
        exponentialDelay()
    }
}

fun HttpMessageBuilder.xApiKey(apiKey: String): Unit {
    header("X-API-KEY", apiKey)
}

suspend inline fun <reified T> HttpResponse.deserialize(): T {
    return when (status) {
        HttpStatusCode.OK -> body<T>()
        else -> {
            val error = body<Error>()
            throw ErrorResponseException(status, error)
        }
    }
}