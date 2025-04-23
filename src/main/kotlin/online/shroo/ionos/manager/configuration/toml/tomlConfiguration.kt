package online.shroo.ionos.manager.configuration.toml

import online.shroo.ionos.manager.annotations.NoArgsCtor

@NoArgsCtor
data class Configuration(val domains: List<Domains>, val update: UpdateConfiguration)

@NoArgsCtor
data class UpdateConfiguration(val frequency: Long)

@NoArgsCtor
data class Domains(val apiKey: String, val domains: List<String>, val description: String)