plugins {
    val kotlinVersion = "1.9.25"
    val springBootVersion = "3.4.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("org.jetbrains.kotlin.kapt") version kotlinVersion
    id("org.springframework.boot") version springBootVersion

    id("io.spring.dependency-management") version "1.1.6"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

// Jpa Kotlin Plugin
allOpen {
    annotation("javax.persistence.Entity")
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.asciidoctor.jvm.convert")

    group = "dev.n0roo.toy"
    version = "0.0.1"

    extra["snippetsDir"] = file("build/generated-snippets")
    extra["springCloudVersion"] = "2024.0.0"

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }


    dependencies {
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        // spring dev tools 및 configuration, Spring Test 모듈은 편의상 미리 주입.
        implementation("org.springframework.boot:spring-boot-starter-logging")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }



    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.test {
        outputs.dir(project.extra["snippetsDir"]!!)
    }

    tasks.asciidoctor {
        inputs.dir(project.extra["snippetsDir"]!!)
        dependsOn(tasks.test)
    }
}