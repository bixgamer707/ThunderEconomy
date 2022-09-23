plugins {
    id("project.common-conventions")
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    api(project(":universal"))
    compileOnly(libs.spigot)
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