package online.shroo.ionos.manager.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.InputStream

val DEFAULT_TOML_MAPPER: Lazy<TomlMapper> = lazy { TomlMapper() }
val DEFAULT_CLASS_LOADER: ClassLoader = ClassLoader.getSystemClassLoader()

fun getInputStream(configName: String, classLoader: ClassLoader = DEFAULT_CLASS_LOADER): InputStream? {
    return classLoader.getResourceAsStream(configName)
}

inline fun <reified T> getResource(
    configName: String,
    objectMapper: ObjectMapper = DEFAULT_TOML_MAPPER.value,
    classLoader: ClassLoader = DEFAULT_CLASS_LOADER
): T? {
    return getInputStream(configName, classLoader)
        ?.use(objectMapper::readValue)
}