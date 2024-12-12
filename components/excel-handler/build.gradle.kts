val poiVersion = "5.2.5"
dependencies {
    implementation("org.apache.poi:poi:$poiVersion")
    implementation("org.apache.poi:poi-ooxml:$poiVersion")
    implementation("org.apache.tika:tika-core:2.4.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
}
val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
bootJar.archiveClassifier.set("boot")
jar.enabled = true