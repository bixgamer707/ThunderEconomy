plugins {
    id("project.common-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    maven("https://maven.enginehub.org/repo/")
}

tasks {
    shadowJar {
        archiveBaseName.set("ThunderEconomy")
        destinationDirectory.set(file("$rootDir/bin/universal/"))
        minimize()
    }

    clean {
        delete("${rootDir}/bin/universal/")
    }
}