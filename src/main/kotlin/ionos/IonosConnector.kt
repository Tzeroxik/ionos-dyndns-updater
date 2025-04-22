package online.shroo.ionos.manager.ionos

import io.ktor.client.HttpClient

class IonosConnector(httpClient: HttpClient = DEFAULT_HTTP_CLIENT) {
    companion object {
        private val DEFAULT_HTTP_CLIENT: HttpClient = HttpClient()
    }
}