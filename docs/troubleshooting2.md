# 트러블슈팅 #2 - DDL 실행 에러

## 에러 상황
```text
Hibernate: drop table if exists user cascade 
2025-03-15T13:26:39.669+09:00  WARN 37464 --- [    Test worker] o.h.t.s.i.ExceptionHandlerLoggedImpl     : GenerationTarget encountered exception accepting command : Error executing DDL "drop table if exists user cascade " via JDBC [Syntax error in SQL statement "drop table if exists [*]user cascade "; expected "identifier";]

org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "drop table if exists user cascade " via JDBC [Syntax error in SQL statement "drop table if exists [*]user cascade "; expected "identifier";]
```

## 해결 방안
### #1. 설정에서 USER 테이블이 예약어가 아님을 설정한다.
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb;NON_KEYWORDS=USER
```

### #2. 엔티티에 테이블 이름을 명시한다.
```kotlin
@Entity
@Table(name="user_info")
class User {}
```

### #3 클래스명을 바꾼다.
```kotlin
@Entity
class UserInfo {}
```
