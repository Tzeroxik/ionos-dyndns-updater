package online.shroo.ionos.manager.ionos.general.exceptions

import io.ktor.http.*

class ErrorResponseException(statusCode: HttpStatusCode, val error: Error) : Exception()