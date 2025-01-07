# Modern Spring Example

본 프로젝트는 다음과 같은 구성 및 개발 환경에서 테스트 되었습니다.

- m3Max macosx
- zulu open jdk 21
- kotlin 1.9.25
- spring boot 3.4.0
- spring cloud 2024.0.0
- junit 5   
- spring Rest Doc (not yet) 
- spring JPA
- spring Jooq     (not yet)
- spring Vault    (not yet)
- jooq Gradle CodeGen   (not yet)
- aws sdk 2.x           (not yet)
- postgreSQL 
- valkey
- docker, docker-compose
- liquibase

## Insist This Project

본 프로젝트의 형상에 영향을 끼친 생각 및 주장들.
동의가 어렵거나, 괴론이라 생각되면, 프로젝트 형상이 마음에 안들 수 있으므로 확인 필요.

- r2dbc 에 대해서..
  - 아직 Spring 에서 r2dbc 가 성숙하지 않았다고 보임.
  - 때문에 성숙도가 높은 JPA 와 Jooq 가 메인
  - 20241218 내용 추가. 실제 authenticate-entities 도메인 모듈의 경우 복잡도가 낮고, 성능이 우선되어서, 단순 CRUD 를 체크해본결과 `Webflux + r2dbc` 와 `Web + JPA` 와의 속도가 유의미한 수준의 차이가 있어 변경.
  - webflux 와 web 의 기본적인 속도 차이로 볼수 있지만, r2dbc 와 jdbc의 속도차이로 여길수 있는 구간이라 생각되는 결과.
  - 하지만 여전히 jpa 기반으로 이루어진 성숙도를 버리기에는 여러 문제가 있어보임.
- why jooq?
  - QueryDSL에서 구현한 JPQL 이미 성숙한 상태. 때문에 해당 라이브러리의 업데이트가 진행 되지 않는 부분에 대해서는 문제삼고 싶지 않음.
  - RDBMS 를 다루는 표준은 SQL이고, JPQL은 방언. 때문에 SQL 친화적인 Jooq를 선택.
    - Readability 가 더 좋다고 생각.
    - Build Time 및 물리적 파일의 크기 및 관리 정책은 지불할 수 있음.
- JPA Relations
  - ONE to ONE , MANY to ONE 외에 사용하지 않음.
  - Table 설계상 정규화된 DB 는 Inner Join 만 허용.
  - One to Many 는 단방향도 양방향도 사용하지 않음.
    - 단방향은 성능상의 문제를 불러일으 킬수 있으며, 양방향은 객체지향에서는 순환 참조로 인한 컨플릭트를 일으킬 수 있음.
    - One to Many 는 Many to One 으로 치환가능함. 코드상의 지불 비용이 잠재적 문제점보다 훨씬 낮다고 주장.
- OCP 와 DIP 를 지키지 않음.
  - `@Service` 와 `@Repository` 의 Impl을 구현함으로 개방폐쇄와 의존관계 역전을 완벽히 지키는 경우는 매우적다고 주장.
  - Spring 에서 동일 Interface 의 구현체를 동시에 사용이 필요한 경우 `Primary` 나 `Qualifier` 로 회피하는 경우는 분리가 가능한 경우가 더많다고 생각.
- MSA 건 Monolith 건 Aggregator(Compositor) Service 가 필요
  - 코드 스파게티가 가장 빈번하게 일어나는 구간은 `@Service`
  - 기본적으로 Controller, Service, Repository, Dao 4계층을 선호.
  - MultiModule 또는 Monorepo 를 구현하게 되면, Domain(Data Storage)레이어를 주관하는 모듈에 필연적으로 Service 레이어까지 구현하게 됨.
  - Routing(Controller) Mapper 에서는 요청 분배와 위임을 담당하고, 각각의 DomainService 가 사용되고 최종적인 Response 형상을 집계하는 Aggregator Service 가 필요.
  - Aggregator Service 는 결과적으로 Composite 하게 복수의 데이터를 단일 명세로 반환.
  - 단일책임 원칙을 고수하기 쉬워짐.
  - Aggregator 에 주입되는 의존성을 5개 이하 수준으로 관리하게끔 코드가 구현되면, 서비스가 간결해짐.
- Dev 와 Main Branch 의 형상은 항상 동일해야함.
  - Main(Prod)는 현재 서비스의 형상.
  - Dev(Stage) 는 앞으로의 형상.
  - `liquibase` 와 `jooq` 는 배포시 DB 형상 동기화로 인한 장애 전파의 문제가 있음. 때문에 Dev 에서 **배포로 인해 변화되는 형상에 대한 장애 발생 가능성을 염두에 두어야함**
  - `MongoDB` 나 `Valkey(Redis)` 등의 NoSQL도 데이터 반영으로 인해, Null 대응을 항상 염두에 두어야함.
  - 이걸 확인할 수 있는 방식은 Dev 를 통해 Main 의 장애를 예측하는 것.
- build.gradle 파일은 분할된 명세보다 합쳐진 명세가 보기 편할수 있음.
  - 본 소스에서는 각 모듈별로 `build.gradle` 명세를 분리했음. `asciidoctor` 나 `jooq`, `liquibase` 등의 설정이 많을수록 내용파악이 어려워지기 때문에 분리.
  - 위에거 열거한 방대한 설정만 분리된 `gradle` 스크립트로 관리할 수 있으나, 일반적으로 `build.gradle` 의 분리에 대해서 보편적으로 행하므로 이를 따름.
- Spring 은 Cloud 에서 고비용 자원임.
  - Spring 프로젝트는 초기 구동시 Cpu 와 Memory 요구사항이 높은편.
  - 다른 진영의 프레임워크보다 메모리 압박이 심해서, AWS 등의 클라우드 운영시 peak 고점과 그렇지 않은 상황에서의 고점의 간극이 큰편.
  - 때문에 프로젝트 구성을 DDD 방식보다 CQRS 나 컴퓨팅파워의 소모도가 비슷한 서비스단위로 묶는게 비용최적화에서 이로운 편이라고 주장.
  - 실제로 MSA 구성시 `Gateway` 는 Webflux 로, 기타 서비스들은 CQRS 로, IO 나 Remote Server(3rd) 로 인한 지연이 있는 경우 Go 를 사용해서 동시성 제어를 함으로, 성능과 비용 이득을 많이 봤음.
  - FeignClient 사용의 권장. gRPC 와 rSocket 등의 직렬화 라이브러리를 연계할 경우, AutoScale 구성이 까다로와짐. k8 쪽에서는 크게 문제가 없다하지만, k8 을 안써서;;

---

## Summary
```text
├── api-discovery
├── api-gateway
├── components
│   ├── common
│   ├── excel-handler
│   ├── jwt-supporter                       <-- jwt-supporter
│   └── mail-sender
├── domain
│   ├── authenticate-entities               <-- authenticate with jpa
│   ├── authenticate-entities-r2            <-- authenticate with r2dbc
│   ├── common-cached                       <-- spring redis reactive
│   ├── samples-documents
│   └── samples-entities
│── gradle
│── service
    ├── authenticate-api                          <-- webflux api
    └── sample-api
```

Update Logs

- 작업중. 