plugins {
    id("project.common-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    api(project(":universal"))
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

tasks {
    shadowJar {
        archiveBaseName.set("ThunderEconomy")
        destinationDirectory.set(file("$rootDir/bin/bukkit/"))
        minimize()
    }

    clean {
        delete("${rootDir}/bin/bukkit/")
    }
}