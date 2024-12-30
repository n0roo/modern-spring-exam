package dev.n0roo.toy.components.common.enums

object AppTypes {

    object Log {
        enum class TraceCases { INFO, WARNING, CRITICAL, NON_HANDLED }
    }

    object Common {
        enum class PlatformType {
            IOS, AND, WEB, PWA_APP, ETC
        }

        enum class Exists {
            EXISTS, NOT_EXISTS
        }
    }

    object Service {
        enum class Resources {
            SYSTEM, SAMPLES
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
            EMAIL, APPLE, GOOGLE, KV
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

        enum class VerifiedType {
            MOBILE, EMAIL, MOBILE_SIGNING, EMAIL_SIGNING
        }
    }

    object Users {
        enum class Status {
            ON, OK, BLOCK, LEAVE
        }
    }
}