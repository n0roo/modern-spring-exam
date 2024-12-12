pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "modern-spring-exam"

include(
    "components:common", // 공통 Util, Const, Type, Model 등 Spring 이나 여타 의존성이 없는 컴포넌트
    "components:aws", // aws components
    "components:mail-sender",   // SMTP email Sender
    "components:excel-handler",   // POI Excel Handler

    "domain:common-cached",
    "domain:authenticate-entities", // authenticate 는 사용자의 인증만을 담당하므로, 인증만을 위한 entity 를 제어한다.
    "domain:samples-entities", // sample entity
    "domain:samples-documents", // sample documents

    "service:authenticate-api", // 인증 서버
    "service:sample-api",

    "api-gateway",  // 게이트 웨이 서버
    "api-discovery", // eureka discovery register
    "vault-server", // vault server
)