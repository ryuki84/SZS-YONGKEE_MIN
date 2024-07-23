# 삼쩜삼 백엔드 엔지니어 채용 과제

## 프로젝트 설명

이 프로젝트는 삼쩜삼 백엔드 엔지니어 채용 과제를 구현한 것입니다. 사용자가 서비스에 가입하고 로그인하며, 제공된 웹사이트로부터 소득 정보를 스크래핑하여 환급액을 계산하는 기능을 제공합니다. Spring Boot, Spring Security, JPA, H2 데이터베이스, JWT, Swagger를 사용하여 구현되었습니다.

## 주요 기능

1. **회원 가입**
   - 사용자가 회원 가입을 할 수 있습니다.
   - 오직 제공된 사용자 목록에 있는 사용자만 가입할 수 있습니다.

2. **로그인**
   - 사용자가 로그인할 수 있습니다.
   - 로그인 시 JWT(JSON Web Token)를 발급하여 인증을 처리합니다.

3. **스크래핑**
   - 인증된 사용자가 외부 API를 통해 자신의 소득 정보를 스크래핑할 수 있습니다.
   - 스크래핑된 데이터는 데이터베이스에 저장됩니다.

4. **환급액 계산**
   - 인증된 사용자가 자신의 환급액을 조회할 수 있습니다.
   - 스크래핑된 데이터를 바탕으로 환급액을 계산하여 반환합니다.

## 사용된 기술

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Security**
- **JPA (Java Persistence API)**
- **H2 Database (In-Memory Mode)**
- **JWT (JSON Web Token)**
- **Swagger (Springdoc OpenAPI)**


## 프로젝트 구성 및 설명
### 프로젝트 구성

```
szs-민용기기/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   ├── ApiProjectApplication.java
│   │   │   │   │   │   ├── config/
│   │   │   │   │   │   │   ├── WebSecurityConfig.java
│   │   │   │   │   │   │   ├── SwaggerConfig.java
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   │   ├── AuthController.java
│   │   │   │   │   │   │   ├── ScrapingController.java
│   │   │   │   │   │   │   ├── RefundController.java
│   │   │   │   │   │   ├── dto/
│   │   │   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   │   │   ├── JwtResponse.java
│   │   │   │   │   │   │   ├── ScrapeRequest.java
│   │   │   │   │   │   ├── entity/
│   │   │   │   │   │   │   ├── User.java
│   │   │   │   │   │   │   ├── ScrapingData.java
│   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   │   │   ├── ScrapingDataRepository.java
│   │   │   │   │   │   ├── service/
│   │   │   │   │   │   │   ├── UserService.java
│   │   │   │   │   │   │   ├── JwtUserDetailsService.java
│   │   │   │   │   │   │   ├── ScrapingService.java
│   │   │   │   │   │   │   ├── RefundService.java
│   │   │   │   │   │   ├── util/
│   │   │   │   │   │   │   ├── JwtUtil.java
│   │   │   │   │   │   │   ├── JwtAuthenticationFilter.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   ├── test/
│       ├── java/
│           ├── com/
│               ├── example/
│                   ├── api/
│                       ├── ApiProjectApplicationTests.java
```

### 설명 및 이유

1. **ApiProjectApplication.java**:
    - 메인 애플리케이션 클래스입니다. Spring Boot 애플리케이션의 진입점으로, 애플리케이션을 실행합니다.

2. **config** 패키지:
    - `WebSecurityConfig.java`: Spring Security 설정을 담당합니다. JWT 인증 필터를 추가하고, 모든 요청에 대해 인증을 요구해야하지만, 테스트인 관계로 모든 요청에 대한 접근가능하게 구현했습니다.
    - `SwaggerConfig.java`: Swagger 설정을 담당합니다. Swagger UI를 통해 API 문서를 제공하고, API 테스트를 쉽게 할 수 있도록 합니다.

3. **controller** 패키지:
    - `AuthController.java`: 사용자 인증(회원가입 및 로그인)을 처리하는 컨트롤러입니다.
    - `ScrapingController.java`: 사용자의 소득 정보를 스크래핑하는 요청을 처리하는 컨트롤러입니다.
    - `RefundController.java`: 사용자의 환급액을 계산하는 요청을 처리하는 컨트롤러입니다.

4. **dto** 패키지:
    - `LoginRequest.java`: 로그인 요청 데이터를 담는 DTO 클래스입니다.
    - `JwtResponse.java`: JWT 응답 데이터를 담는 DTO 클래스입니다.
    - `ScrapeRequest.java`: 스크래핑 요청 데이터를 담는 DTO 클래스입니다.

5. **entity** 패키지:
    - `User.java`: 사용자 정보를 담는 엔티티 클래스입니다.
    - `ScrapingData.java`: 스크래핑한 소득 정보를 담는 엔티티 클래스입니다.

6. **repository** 패키지:
    - `UserRepository.java`: 사용자 정보를 저장하고 조회하는 JPA 레포지토리입니다.
    - `ScrapingDataRepository.java`: 스크래핑 데이터를 저장하고 조회하는 JPA 레포지토리입니다.

7. **service** 패키지:
    - `UserService.java`: 사용자 등록 및 검증을 처리하는 서비스 클래스입니다.
    - `JwtUserDetailsService.java`: Spring Security의 `UserDetailsService`를 구현하여 사용자 인증을 처리하는 서비스 클래스입니다.
    - `ScrapingService.java`: 외부 API를 호출하여 소득 정보를 스크래핑하고, 데이터를 데이터베이스에 저장하는 서비스 클래스입니다.
    - `RefundService.java`: 스크래핑한 데이터를 기반으로 환급액을 계산하는 서비스 클래스입니다.

8. **util** 패키지:
    - `JwtUtil.java`: JWT 토큰 생성, 검증, 정보 추출을 처리하는 유틸리티 클래스입니다.
    - `JwtAuthenticationFilter.java`: JWT 인증 필터로, 모든 요청에 대해 JWT를 검증합니다.

### 소스코드 설명

각 소스코드에 대한 간략한 설명을 주석으로 추가하였습니다. 각 클래스와 메서드가 하는 역할과 이유는 다음과 같습니다.

1. `ApiProjectApplication.java`: Spring Boot 애플리케이션을 시작하는 진입점입니다.

2. `WebSecurityConfig.java`: Spring Security 설정 파일로, JWT 인증 필터를 등록하고, 특정 경로에 대한 인증을 설정합니다. JWT를 통해 모든 요청을 인증해야하지만, 인증을 하지 않고 넘어갈 수 있게 구현되었습니다.

3. `SwaggerConfig.java`: Swagger 설정 파일로, API 문서를 자동으로 생성하고 UI로 제공하여 API를 쉽게 테스트할 수 있도록 합니다.

4. `AuthController.java`: 회원가입 및 로그인 요청을 처리하는 컨트롤러입니다. 회원가입 시, 사용자 정보를 저장합니다. 로그인 성공 시 JWT를 생성하여 응답합니다.

5. `ScrapingController.java`: 스크래핑 요청을 처리하는 컨트롤러로, 외부 API를 호출하여 소득 정보를 가져옵니다.

6. `RefundController.java`: 환급액 계산 요청을 처리하는 컨트롤러로, 사용자별 스크래핑 데이터를 기반으로 환급액을 계산합니다.

7. `LoginRequest.java`: 로그인 요청 데이터를 담는 DTO로, `userId`와 `password` 필드를 가집니다.

8. `JwtResponse.java`: 로그인 성공 시 JWT를 담는 응답 DTO로, `accessToken` 필드를 가집니다.

9. `ScrapeRequest.java`: 스크래핑 요청 데이터를 담는 DTO로, `name`과 `regNo` 필드를 가집니다.

10. `User.java`: 사용자 정보를 담는 엔티티로, `userId`, `password`, `name`, `regNo` 필드를 가집니다.

11. `ScrapingData.java`: 스크래핑 데이터를 담는 엔티티로, `totalIncome`, `pensionDeduction`, `creditCardDeduction`, `taxDeduction` 필드를 가집니다.

12. `UserRepository.java`: 사용자 데이터를 저장하고 조회하는 JPA 레포지토리입니다.

13. `ScrapingDataRepository.java`: 스크래핑 데이터를 저장하고 조회하는 JPA 레포지토리입니다.

14. `UserService.java`: 사용자 등록 및 검증을 처리하는 서비스입니다. 유효한 사용자인지 검증하고, 비밀번호를 암호화하여 저장합니다.

15. `JwtUserDetailsService.java`: Spring Security의 `UserDetailsService`를 구현하여 사용자 인증을 처리하는 서비스입니다.

16. `ScrapingService.java`: 외부 API를 호출하여 소득 정보를 스크래핑하고, 데이터를 데이터베이스에 저장합니다.

17. `RefundService.java`: 스크래핑 데이터를 기반으로 환급액을 계산하는 서비스입니다.

18. `JwtUtil.java`: JWT 토큰 생성, 검증, 정보 추출을 처리하는 유틸리티 클래스입니다.

19. `JwtAuthenticationFilter.java`: 모든 요청에 대해 JWT를 검증하는 필터입니다. 

이 프로젝트는 Spring Boot와 Spring Security, JPA를 사용하여 사용자 인증과 데이터 관리를 수행하며, 외부 API를 통한 데이터 스크래핑과 계산 기능을 제공합니다. Swagger를 통해 API를 문서화하고 테스트할 수 있습니다.
