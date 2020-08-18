# 1. 개요
- 간단한 공지사항 웹페이지를 구현하였습니다.
- 공지사항 목록 조회/등록/수정/삭제를 할 수 있습니다.
- 공지사항에는 1개 이상의 첨부파일이 등록가능합니다. (이지미일 경우 간단한 미리보기를 제공합니다.)
- 첨부파일은 보기 및 다운로드가 가능합니다.
- 인터페이스는 REST 형식의 API로 제공됩니다.


# 2. 기술명세
- 언어 : Java 1.8
- IDE : STS 4
- 프레임워크 : spring boot 2.3.1
- 의존성 & 빌드 관리 : gradle
- 인증 : Spring Security
- Persistence : JPA (Hibernate) 
- Database : H2 (in memory)
- OAS : Swagger

> Swagger API명세 페이지 보기
- 어플리케이션 기동 후 아래와 같이 [http://localhost:8080/swagger-ui.html] 접속하여 API페이지를 조회할 수 있습니다.
<img src="https://user-images.githubusercontent.com/61044774/90228646-8d25bf80-de51-11ea-9adb-29d48b5dab6a.jpg" width="90%"></img>


> H2 database 웹console 접속경로는 다음과 같습니다. [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) 

<img src="https://user-images.githubusercontent.com/61044774/85590819-b0b56080-b67f-11ea-8415-3eb50f5b82b8.jpg" width="90%"></img>

- Driver Class : org.h2.Driver
- JDBC URL : jdbc:h2:mem:testdb
- User Name : sa
- Password : 없음


# 3. 빌드 및 실행

> Tips 
- 만약 빌드가 제대로 진행되지 않거나 오류가 발생 시 아래와 같이 STS에서 'Refresh Gradle Project' 를 클릭해 주세요

<img src="https://user-images.githubusercontent.com/61044774/85550736-34f5ec80-b65c-11ea-865b-981c6b72f2b9.jpg" width="90%"></img>

---

## build 하기 (war 파일 생성)

- 아래 그림과 같이 'Gradle Tasks' 메뉴에서 'bootWar' 를 오른쪽 클릭하여 'Run Gradle Tasks' 를 실행합니다.

<img src="https://user-images.githubusercontent.com/61044774/85556604-f5320380-b661-11ea-8020-04290b8762d4.jpg" width="90%"></img>

- [프로젝트경로]/build/lib 경로에 build 된 war 파일이 생성됩니다.

<img src="https://user-images.githubusercontent.com/61044774/85557150-7d180d80-b662-11ea-9fd8-2bd1bbc23df3.jpg" width="90%"></img>


## 실행 하기

> 소스 main Application 실행하기 
- com.milkit.app.DemoApplication 을 STS에서 run하여 바로 실행할 수 있습니다.
 
<img src="https://user-images.githubusercontent.com/61044774/85557671-f44da180-b662-11ea-90cd-d25288cadf2f.jpg" width="90%"></img>


# 4. 로그인

> 웹접속경로는 다음과 같습니다. [http://localhost:8080/](http://localhost:8080/) 

- id : test
- password : test

<img src="https://user-images.githubusercontent.com/61044774/85558776-0a0f9680-b664-11ea-9efd-e7fa8829266f.jpg" width="90%"></img>


# 5. 구현기능 및 요구사항
> 구현기능
- 공지 등록/수정/삭제기능
- 공지목록 조회 기능 (제목, 작성일, 작성자, 최종수정일, 내용)
- 페이징 기능 (10개 마다)
- 여러개의 첨부파일 등록 기능

**파일업로드 후 이미지가 안보일 수 있습니다. 그럴경우 아래와 같이 Project를 refresh 해주세요.**

<img src="https://user-images.githubusercontent.com/61044774/85596326-95008900-b684-11ea-9d44-ff2fd1bc166e.jpg" width="90%"></img>



