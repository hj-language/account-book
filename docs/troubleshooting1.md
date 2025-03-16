# 트러블슈팅 #1 - 레포지터리 등록 안 되는 현상

## 에러 상황

```text
defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Not a managed type
```


## 시도 #1

- `@Entity` 데코레이터 있는지 확인
- `JpaRepository<>` 에 자동완성 쓰다가 Repository를 넣는다거나, 엔티티가 아닌 클래스가 들어가있는지 확인


## 해결 방법
- GPT 의 답변에서 힌트를 얻어 패키지 지정이 필요하다는 것을 알게되었다.

```kotlin
import TransactionRecord
```
- 그리고 자꾸만 엔티티 클래스를 import 할 때 위와 같이 너무 간결하다는 것을 인지했다.

```kotlin
package com.hyejin.account_book.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User {}
//...
```
- 엔티티 파일들에 package 지정이 되어있는지 확인했고, 없길래 추가하니 해결됐다.


## Advanced

### 💡 Spring Boot가 엔티티를 찾는 원리
- 패키지를 명시하지 않으면, 엔티티 클래스들이 기본 패키지 (`default package`)에 속하게 된다.
- 그런데 Spring Boot는 "기본 패키지에서 엔티티를 자동으로 검색하지 않는다"는 규칙을 가지고 있다.
- 즉, `@EntityScan`이 따로 설정되지 않은 상태에서 엔티티가 패키지 없이 존재하면, Spring이 해당 클래스를 JPA 엔티티로 등록하지 못하고 무시한다.
- Spring이 엔티티를 인식할 수 없고, JPA Repository (`TransactionRecordRepository`)를 만들 때 "이거 엔티티가 아닌데?" 하면서 오류가 나는 것이다.


### 💡 Component Scan

- 컴포넌트 스캔(Component Scanning)은 Spring이 특정 패키지에서 `@Component`, `@Service`, `@Repository`, `@Controller` 등의 애너테이션이 붙은 클래스를 자동으로 찾아서 Bean으로 등록하는 기능이다.
- Spring Boot에서는 `@SpringBootApplication`이 선언된 패키지를 기준으로 하위 패키지들을 자동으로 스캔하는데, `@Entity`는 컴포넌트 스캔의 대상이 아니기 때문에 별도로 `@EntityScan`을 사용해야 한다.


### 💡 애플리케이션 실행 시 IoC 컨테이너가 뜨는지 확인
```kotlin
@Autowired
lateinit var context: ApplicationContext
```
- 코드에 ApplicationContext 주입받아서 getBean 등으로 뜨는지 확인 가능하다.
- import 주의: `import org.springframework.context.ApplicationContext`를 사용해야 한다.
  - `import org.apache.catalina.core.ApplicationContext` 쓰면 에러 남


