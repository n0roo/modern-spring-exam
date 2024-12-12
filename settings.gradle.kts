pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "modern-spring-exam"

include("components:common")  // 공통 Util, Const, Type, Model 등 Spring 이나 여타 의존성이 없는 컴포넌트
include("components:mail-sender")   // SMTP email Sender

include("domain:common-cached")
include("domain:authenticate-entities") // authenticate 는 사용자의 인증만을 담당하므로, 인증만을 위한 entity 를 제어한다.
include("domain:samples-entities")   // sample entity
include("domain:samples-documents")   // sample documents

include("service:authenticate-api")  // 인증 서버
include("service:sample-api")  // 인증 서버

include("api-gateway")          // 게이트 웨이 서버
include("api-discovery")        // eureka discovery register
include("vault-server")        // vault server