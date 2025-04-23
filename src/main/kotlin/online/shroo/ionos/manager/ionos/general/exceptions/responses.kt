package online.shroo.ionos.manager.ionos.general.exceptions

import kotlinx.serialization.Serializable

@Serializable
data class Error(val code: String = "", val message: String = "")