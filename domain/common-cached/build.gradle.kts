dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
}
val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
bootJar.archiveClassifier.set("boot")
jar.enabled = true