plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.50"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    install("common")
    install("common-5")
    install("module-ai")
    install("module-nms")
    install("module-nms-util")
    install("module-configuration")
    install("module-database")
    install("module-lang")
    install("module-chat")
    install("module-kether")
    install("module-metrics")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("module-ui")
    install("expansion-javascript")
    description {
        contributors {
            name("inrhor")
            desc("Minecraft Pet Core")
        }
        dependencies {
            name("ModelEngine").optional(true)
            name("OrangeEngine").optional(true)
            name("GermEngine").optional(true)
            name("DragonCore").optional(true)
            name("UiUniverse").optional(true)
        }
    }
    classifier = null
    version = "6.0.10-21"
}

repositories {
    mavenCentral()
    maven("https://mvn.lumine.io/repository/maven-public/") // model-engine
}

dependencies {
    compileOnly("public:GermPlugin:4.0.3")
    compileOnly("com.ticxo.modelengine:api:R3.0.1")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    compileOnly(fileTree("libs"))
    // compileOnly("ink.ptms:nms-all:1.0.0")
    // compileOnly("ink.ptms.core:v11800:11800-minimize:api")
    // compileOnly("ink.ptms.core:v11800:11800-minimize:mapped")
    // NOTE: those removed lines are for local builds, but as for Github Actions
    //   it seems that which cant access ptms.ink:8080
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        // create<MavenPublication>("library") {
        //     from(components["java"])
        //     groupId = project.group.toString()
        // }
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main")
        }
    }
}

java {
    withSourcesJar()
}