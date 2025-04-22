package online.shroo.ionos.manager.configuration.toml

import online.shroo.ionos.manager.jackson.annotations.NoArgsCtor

@NoArgsCtor
data class Configuration(val domainConfigurations: List<DomainConfiguration>)

@NoArgsCtor
data class DomainConfiguration(
    val apiKey: String,
    val userId: String,
    val domains: List<String>
)