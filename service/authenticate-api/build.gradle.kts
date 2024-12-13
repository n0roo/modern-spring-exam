dependencies {
    implementation(project(":components:common"))
    implementation(project(":domain:authenticate-entities"))
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-validation")
}