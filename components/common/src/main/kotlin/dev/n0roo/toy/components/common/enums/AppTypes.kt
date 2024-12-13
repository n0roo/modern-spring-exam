package dev.n0roo.toy.components.common.enums

class AppTypes {

    object Log {
        enum class TraceCases { INFO, WARNING, CRITICAL, NON_HANDLED }
    }

    object Common {
        enum class PlatformType {
            IOS, AND, WEB, PWA_APP, ETC
        }
    }

    object Service {
        enum class Resources {
            SYSTEM
        }
    }

    object Authenticate {
        enum class Status {
            ON,         // 기본 상태
            OK,         // 이용 가능
            BLOCK,      // 차단
            LEAVE,      // 탈퇴
            REMOVE,      // 삭제
            RELEASE      // 연동해제
        }

        enum class ApprovalResources {
            EMAIL, APPLE, GOOGLE, SIGNATURE, KV
        }

        enum class TokenType(value: String) {
            BEARER("Bearer"),
            JWT("jwt"),
            BASIC("Basic"),
            OAUTH("OAuth"),
            SCRAM_SHA_256("SCRAM-SHA-256")
        }

        enum class AccountAuthorities {
            ANONYMOUS,
            USER,
            ADMIN,
        }
    }
}