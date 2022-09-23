plugins {
    id("project.common-conventions")
    alias(libs.plugins.shadow)
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