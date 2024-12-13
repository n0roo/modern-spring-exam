-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.


-- Sequence and defined type
create sequence if not exists authenticate_account_id_seq;

create table public.authenticate_account
(
    id                bigint default nextval('authenticate_account_id_seq'::regclass) not null
        constraint authenticate_account_pk
            primary key,
    reference_user_id bigint                                                          not null,
    service_resources varchar                                                         not null,
    status            varchar                                                         not null,
    created_at        timestamp with time zone                                        not null,
    issued_at         timestamp with time zone                                        not null,
    deleted           boolean                                                         not null
);

comment on table public.authenticate_account is '인증 등록 유저';
comment on column public.authenticate_account.id is '인증 인가 등록 아이디';
comment on column public.authenticate_account.reference_user_id is '서비스 실제 사용자 아이디';
comment on column public.authenticate_account.service_resources is '인증 수단 등록 서비스';
comment on column public.authenticate_account.status is '인증 상태';
comment on column public.authenticate_account.created_at is '생성일';
comment on column public.authenticate_account.issued_at is '최종 수정일';
comment on column public.authenticate_account.deleted is '삭제여부';
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.


-- Sequence and defined type
create sequence if not exists authenticate_account_resource_id_seq;

create table authenticate_account_resource
(
    id                     bigint  default nextval('authenticate_account_resource_id_seq'::regclass) not null
        primary key,
    account_id             bigint                                                                    not null,
    reference_user_id      bigint                                                                    not null,
    service_resources      varchar                                                                   not null,
    key                    varchar default ''::character varying                                     not null,
    secret                 varchar default ''::character varying                                     not null,
    approval_resource_type varchar                                                                   not null,
    status                 varchar                                                                   not null,
    issued_at              timestamp with time zone                                                  not null,
    created_at             timestamp with time zone                                                  not null,
    deleted                boolean                                                                   not null,
    error_reason           varchar default ''::character varying                                     not null
);

comment on column authenticate_account_resource.id is '인증 사용자 등록 로그인 수단';
comment on column authenticate_account_resource.account_id is '인증 사용자 등록 아이디';
comment on column authenticate_account_resource.reference_user_id is '서비스 실제 사용자 아이디';
comment on column authenticate_account_resource.service_resources is '인증 수단 등록 서비스';
comment on column authenticate_account_resource.key is '아이디/이메일/소셜아이디/시그니쳐코드';
comment on column authenticate_account_resource.secret is '비밀번호/pairkey';
comment on column authenticate_account_resource.approval_resource_type is '로그인 유형';
comment on column authenticate_account_resource.status is '로그인 유형 상태';
comment on column authenticate_account_resource.issued_at is '최종 수정일';
comment on column authenticate_account_resource.created_at is '생성일';
comment on column authenticate_account_resource.deleted is '삭제여부';
comment on column authenticate_account_resource.error_reason is '에러 상태. 사유';

-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
create sequence if not exists authenticate_account_approvals_id_seq;

create table authenticate_account_approvals
(
    id                bigint  default nextval('authenticate_account_approvals_id_seq'::regclass) not null
        primary key,
    resource_id       bigint                                                                     not null,
    device_id         bigint                                                                     not null,
    account_id        bigint                                                                     not null,
    reference_user_id bigint                                                                     not null,
    token             varchar default ''::character varying                                      not null,
    refresh_token     varchar default ''::character varying                                      not null,
    token_type        varchar                                                                    not null,
    created_at        timestamp with time zone                                                   not null,
    expired_at        timestamp with time zone                                                   not null,
    deleted           boolean                                                                    not null
);

comment on column authenticate_account_approvals.id is '인증 인가 이력 아이디';
comment on column authenticate_account_approvals.resource_id is '인증 인가 리소스 아이디';
comment on column authenticate_account_approvals.device_id is '인증 인가 기기 아이디';
comment on column authenticate_account_approvals.account_id is '인증 사용자 등록 아이디';
comment on column authenticate_account_approvals.reference_user_id is '서비스 실제 사용자 아이디';
comment on column authenticate_account_approvals.token is '억세스 토큰';
comment on column authenticate_account_approvals.refresh_token is '리프레시 토큰';
comment on column authenticate_account_approvals.token_type is '토큰 타입';
comment on column authenticate_account_approvals.created_at is '생성일';
comment on column authenticate_account_approvals.expired_at is '만료일/삭제일';
comment on column authenticate_account_approvals.deleted is '삭제 폐기 여부';

-- Sequence and defined type
create sequence if not exists devices_id_seq;

create table public.devices
(
    id                 bigint  default nextval('devices_id_seq'::regclass) not null
        primary key,
    source_device_id   varchar default ''::character varying               not null,
    user_agent         varchar                                             not null,
    platform_type      varchar                                             not null,
    registration_id    varchar                                             not null
        constraint devices_pk unique,
    notification_uid   varchar default ''::character varying               not null,
    app_version        varchar                                             not null,
    activated          boolean                                             not null,
    multiple_connected boolean                                             not null,
    created_at         timestamp with time zone                            not null,
    issued_at          timestamp with time zone                            not null
);
comment on column devices.id is '기기 관리 아이디';
comment on column devices.source_device_id is '기기 UUID';
comment on column devices.user_agent is 'UserAgentString';
comment on column devices.platform_type is '기기 플랫폼 유형';
comment on column devices.registration_id is '기기 식별용 아이디/서버측 발행';
comment on column devices.notification_uid is 'fcm/apns 푸시아이디';
comment on column devices.app_version is '요청 앱버전';
comment on column devices.activated is '활성여부';
comment on column devices.multiple_connected is '다중계정 접속 여부';
comment on column devices.created_at is '생성일';
comment on column devices.issued_at is '최종 수정일';