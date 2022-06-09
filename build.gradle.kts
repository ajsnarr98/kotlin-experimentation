plugins {
    kotlin("jvm") version "1.4.32"
    java
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDirs(
                "src/main/kotlin/"
            )
        }
    }
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "com.github.ajsnarr98.ktexperimentation.MainKt"
        )
    }

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
