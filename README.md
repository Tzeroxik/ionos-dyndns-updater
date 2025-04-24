# Getting Started

Create and configure the ``configuration.toml`` file in the ``src/main/kotlin/resources/`` directory

```TOML[update]
# Example configuration file, replace what your configuration
frequency = 900000 # frequency of updates (configured to 15 minutes), cannot be less than 2 minutes

[[domains]]
apiKey = "prefix.secret" # the api key from IONOS, more info at https://developer.hosting.ionos.com/docs/getstarted
domains = ["www.xpto.com", "xpto.com"] # the domains to update
description = "xpto" # a description for this dynamic updater
```

Compile the program using Maven using ``mvn clean install ``, the executable jar (``ionos-dyndns-updater-1.0-SNAPSHOT.jar``) 
will be inside the ``target`` directory

Run program using ``java -jar ionos-dyndns-updater-1.0-SNAPSHOT.jar``
