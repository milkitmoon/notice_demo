# 1. 개요
- 간단한 공지사항 웹페이지를 구현하였습니다.
- 공지사항 목록 조회/등록/수정/삭제를 할 수 있습니다.
- 공지사항에는 1개 이상의 첨부파일이 등록가능합니다. (이지미일 경우 간단한 미리보기를 제공합니다.)
- 첨부파일은 보기 및 다운로드가 가능합니다.
- 인터페이스는 REST 형식의 API로 제공됩니다.


# 2. 기술명세
- 언어 : Java 1.8
- 프레임워크 : spring boot 2.3.1
- 의존성 & 빌드 관리 : gradle
- 인증 : spring security
- Persistence : JPA (Hibernate) 
- Database : H2 (in memory)
- OAS : Swagger

> Swagger API명세 페이지 보기
- 어플리케이션 기동 후 아래와 같이 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 접속하여 API페이지를 조회할 수 있습니다.
<img src="https://user-images.githubusercontent.com/61044774/90470832-7a640100-e157-11ea-8122-ed5dc8e955e5.jpg" width="90%"></img>


> H2 database 웹콘솔 보기
- H2 웹console 접속경로는 다음과 같습니다. [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) 
<img src="https://user-images.githubusercontent.com/61044774/85590819-b0b56080-b67f-11ea-8415-3eb50f5b82b8.jpg" width="90%"></img>

- Driver Class : org.h2.Driver
- JDBC URL : jdbc:h2:mem:testdb
- User Name : sa
- Password : [없음]


# 3. 실행

> Tips 
- **만약 lombok 관련 오류가 발생하면 아래의 url을 참조해 주세요**
[https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts](https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts)
[https://countryxide.tistory.com/16](https://countryxide.tistory.com/16)  
[https://planbsw.tistory.com/109](https://planbsw.tistory.com/109)

---

## 실행 하기

> 소스 main Application 실행하기 
- com.milkit.app.DemoApplication 을 STS에서 run하여 바로 실행할 수 있습니다.
 <img src="https://user-images.githubusercontent.com/61044774/85557671-f44da180-b662-11ea-90cd-d25288cadf2f.jpg" width="90%"></img>


# 4. 로그인

> 웹접속경로는 다음과 같습니다. [http://localhost:8080/](http://localhost:8080/) 

- id : test
- password : test

<img src="https://user-images.githubusercontent.com/61044774/85558776-0a0f9680-b664-11ea-9efd-e7fa8829266f.jpg" width="90%"></img>


# 5. 구현기능
> 구현기능
- 공지 등록/수정/삭제기능
- 공지목록 조회 기능 (제목, 작성일, 작성자, 최종수정일, 내용)
- 페이징 기능 (10개 마다)
- 여러개의 첨부파일 등록 기능

**파일업로드 후 이미지가 안보일 수 있습니다. 그럴경우 Project를 refresh 해주세요.**

