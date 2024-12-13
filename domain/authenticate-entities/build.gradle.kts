plugins {
    id("org.liquibase.gradle") version "2.2.0"
    id("org.jooq.jooq-codegen-gradle") version "3.19.10"
}


val postgreSqlVersion = "42.7.4"
val liquibaseCoreVersion = "4.24.0"
extra["postgreSqlVersion"] = postgreSqlVersion
extra["liquibaseCoreVersion"] = liquibaseCoreVersion

dependencies {
    apply(plugin = "org.liquibase.gradle")
    apply(plugin = "org.jooq.jooq-codegen-gradle")

    implementation(project(":components:common"))
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    // liquibase 형상관리
    implementation("org.postgresql:postgresql:${property("postgreSqlVersion")}")
    liquibaseRuntime("org.postgresql:postgresql:${property("postgreSqlVersion")}")
    implementation("org.liquibase:liquibase-core:${property("liquibaseCoreVersion")}")

    jooqCodegen("org.postgresql:postgresql:${property("postgreSqlVersion")}")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
bootJar.archiveClassifier.set("boot")
jar.enabled = true
