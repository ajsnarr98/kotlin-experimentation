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
