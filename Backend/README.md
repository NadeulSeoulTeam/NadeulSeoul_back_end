# NadeulSeoul_back_end

### 목차
1. [**ERD 모델링**](#1)
2. [**프로젝트 구조**](#2)
3. [**환경설정**](#3)


<div id="1"></div>

## ERD 모델링

**https://lucid.app/lucidchart/21697a07-83d7-4fd6-956e-d792cfe59ff8/edit?invitationId=inv_1ce7d1ef-2add-48ff-91e9-980174e37b67**

- ERD cloud로 변경

  erd 속성 표시가 어렵고, erdcloud의 경우 자동으로 sql문을 생성해주는 이점이 있어 변경하였다.

https://www.erdcloud.com/d/uPQQ8DXfRNoF7qPKk

제 1 정규화 Atomic

큐레이션에서 store 정보 분리

제 2정규화 완전 함수 종속(기본키의 부분집합이 결정자 X)

local tag 분리

제 3정규화 A → B. B → C 이행적 종속 X

<div id="2"></div>

## 프로젝트 구조

참고

https://cheese10yun.github.io/spring-guide-directory/

https://velog.io/@dhwlddjgmanf/꼬리별-오류일지-패키지-구조는-어떻게-짜는-게-맞을까

```json
com
 ㄴ dsm
     ㄴ clematis
         ㄴ domain
         |   ㄴ account
         |   |   ㄴ controller
         |   |   |   ㄴ request
         |   |   |   ㄴ response
         |   |   ㄴ service
         |   |   |   ㄴ provider
         |   |   ㄴ repository
         |   ㄴ project
         |   |   ㄴ controller
         |   |   |   ㄴ request
         |   |   |   ㄴ response
         |   |   ㄴ service
         |   |   |   ㄴ provider
         |   |   ㄴ repository
         |   ...
         ㄴ global
             ㄴ configuration
             ㄴ dto
             ㄴ attribute
             ㄴ security
             |   ㄴ configuration
             |   ㄴ filter
             |   ㄴ provider
             ㄴ exception
             |   ㄴ entrypoint
             |   ㄴ handler
             |   ㄴ response
             ㄴ validation
```
<div id="3"></div>

## 환경 설정

- JDK version: 8 vs 11

  패키지 보안: JAVA8에서는 모든 패키지들 사용 가능 → JAVA 11부터 개발자가 사용하지 말아야 할 패키지를 모듈 내부에 숨길수 있음

  런타임 시간: java8은 250MB 용량의 java.lang/java.util/java/.se와 같은 모든 패키지를 가져와서 실행하지만 java11부터는 모듈화를 통해 필요한 모듈만 가져와서 프로그램 실행

  | JDK                         | Spring |
  | --------------------------- | ------ |
  | 6-8(공식적으로 end of life) | 4.3.X  |
  | 8-10                        | 5.0.X  |
  | 8-12                        | 5.1.X  |
  | 8-15                        | 5.2.X  |
  | 8-19(expected)              | 5.3.X  |

  | JDK     | Spring boot |
  | ------- | ----------- |
  | 2.3이상 | 9 이상      |
  | 2.1이하 | 8-11        |

  +. 현재 LTS로 2021.9 Oracle에서 LTS 버전으로 JAVA17발표

  **WHY?**

  **참고**

  https://daily-life-of-bsh.tistory.com/129

  https://daddyprogrammer.org/post/10411/jdk-roadmap-change-jdk9-11/

  https://innovation-communication.tistory.com/42

- Framework: spring vs spring boot

  자바 프레임워크 : DI(Dependency Injection) + 제어의 역전(IoC, Inversion of Control)

  Spring → Spring boot:

  Auto Configuration

  쉬운 의존성 관리

  내장 서버

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/56378b33-1d04-415a-9e92-90a6af8ebad1/Untitled.png)

  **WHY?**

  **참고**

  https://server-engineer.tistory.com/739

  http://dawoonjeong.com/spring-spring_mvc-vs-spring_boot-vs-spring_mvc/

- Build tool: maven vs gradle

  빌드 관리 도구 : java코드와 프로젝트 내 xml, properties, jar 파일들을 JVM이나 WAS가 인식할 수 있도록 패키징 해주는 빌드 과정을 하는 도구

  **Maven**

  Java용 프로젝트 관리도구, Apache의 Ant 대안.

  빌등 중인 프로젝트, 빌드 순서, 외부 라이브러리 종속성 관계를 pom.xml에 명시

  외부저장소에서 필요한 라이브러리와 플러그인을 다운로드 후 로컬시스템의 캐시에 모두 저장

  고정적이고 선형적인 단계의 모델 기반

  **Gradle**

  Maven과 Ant 대안으로 나온 빌드관리툴(오픈소스)

  Groovy 언어를 사용한 Domain-specific language → xml보다 훨씬 간결

  업데이트가 반영된 빌드는 재실행 X → 빌드 시간 단축

  작업 의존성 그래프 기반

  **WHY?**

  **참고**

  https://jisooo.tistory.com/entry/Spring-빌드-관리-도구-Maven과-Gradle-비교하기

  https://bkim.tistory.com/13

  https://gradle.org/maven-vs-gradle/#performance

- DB: NoSQL vs RDBMS | mysql vs mariadb vs postgresql ... | In-Memory DB: redis

  **WHY?**

  1. 

  기획에 따라 필요한 데이터를 정리하고 분류한 결과, 명확하게 정의된 스키마를 도출 가능

  필요한 데이터 사이에 관계를 정의할 수 있기 때문에 중복없이 저장 가능

  진행하는 프로젝트의 큰 틀인 SNS가 아닌 큐레이션 코스에 중점을 두었기 때문에 nosql보다는 rdb 구조가 적합하다고 판단

  sns → 특성상 쓰기보다는 읽기 연산이 많음

  댓글 → 유저와 큐레이션 사이 관계 존재. join연산 고려

  1. 

  refresh token 저장소 = redis

  캐시 기능 사용

  **참고**

  https://devuna.tistory.com/25

  https://velog.io/@jisoo1170/Oracle-MySQL-PostgreSQL-차이점은

- Persistence Framework: SQL Mapper(MyBatis) vs ORM(Hibernate, JPA → spring data JPA)

  **WHY?**

  JPA 의 단점인 러닝커브를 이번 프로젝트를 통해 학습하면서 해결 + 객체 지향 설계 가능

  이번 프로젝트의 세부목표 중 하나인 유지보수가 쉬움

  SQL 쿼리 관련 내용 추가

  **참고**

  https://deveun.tistory.com/entry/SQL-Mapper와-ORM-차이

  https://dreaming-soohyun.tistory.com/entry/JPA와-MyBatis의-차이-ORM과-SQL-Mapper

  http://dawoonjeong.com/spring-spring-orm/