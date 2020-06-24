
-- 게시글
CREATE TABLE `NOTICE_BULLETIN` (
	`ID`        BIGINT       NOT NULL, -- 일련번호
	`TITLE`     VARCHAR(600) NOT NULL, -- 글제목
	`CONTENT`   CLOB         NULL,     -- 글내용
	`READ_NUM`  INT          NULL,     -- 조회횟수
	`USER_IP`   VARCHAR(30)  NULL,     -- 작성자IP
	`USE_YN`    CHAR(1)      NULL,     -- 사용여부
	`INST_TIME` TIMESTAMP    NULL,     -- 등록시간
	`UPD_TIME`  TIMESTAMP    NULL,     -- 갱신시간
	`INST_USER` VARCHAR(20)  NULL,     -- 등록자
	`UPD_USER`  VARCHAR(20)  NULL      -- 갱신자
);

-- 게시글
ALTER TABLE `NOTICE_BULLETIN`
	ADD CONSTRAINT `PK_NOTICE_BULLETIN` -- 게시글 기본키
		PRIMARY KEY (
			`ID` -- 일련번호
		);

-- IDX_NOTICE_BULLETIN_TITLE
CREATE INDEX `IDX_NOTICE_BULLETIN_TITLE`
	ON `NOTICE_BULLETIN`( -- 게시글
		`TITLE` ASC,     -- 글제목
		`USE_YN` ASC,    -- 사용여부
		`INST_USER` ASC, -- 등록자
		`INST_TIME` ASC  -- 등록시간
	);

CREATE SEQUENCE NOTICE_BULLETIN_SEQ;


-- 첨부파일
CREATE TABLE `NOTICE_ATTACH` (
	`ID`             BIGINT       NOT NULL AUTO_INCREMENT, -- 일련번호
	`NOTICE_ID`		 	 BIGINT       NOT NULL, -- 글목록 일련번호
	`FILENAME`  		 VARCHAR(100) NOT NULL, -- 원본파일명
	`URL` 					 VARCHAR(256) NULL,     -- 경로
	`THUMB_URL`      BIGINT       NULL,     -- THUMB경로
	`USE_YN`         CHAR(1)      NULL,     -- 사용여부
	`INST_TIME`      TIMESTAMP    NULL,     -- 등록시간
	`UPD_TIME`       TIMESTAMP    NULL,     -- 갱신시간
	`INST_USER`      VARCHAR(20)  NULL,     -- 등록자
	`UPD_USER`       VARCHAR(20)  NULL      -- 갱신자
);

-- 첨부파일
ALTER TABLE `NOTICE_ATTACH`
	ADD CONSTRAINT `PK_NOTICE_ATTACH` -- 첨부파일 기본키
		PRIMARY KEY (
			`ID` -- 일련번호
		);

-- 첨부파일 인덱스
CREATE INDEX `IX_NOTICE_ATTACH`
	ON `NOTICE_ATTACH`( -- 첨부파일
		`NOTICE_ID` ASC, -- 글목록 일련번호
		`USE_YN` ASC,      -- 사용여부
		`INST_TIME` ASC    -- 등록시간
	);



-- 사용자정보
CREATE TABLE `USER_INFO` (
	`ID`        BIGINT       NOT NULL AUTO_INCREMENT, -- 일련번호
	`USER_ID`   VARCHAR(60)  NOT NULL, -- 사용자아이디
	`PASSWORD`  VARCHAR(256) NOT NULL, -- 비밀번호
	`USER_NM`   VARCHAR(60)  NULL,     -- 사용자명
	`AUTH_ROLE` VARCHAR(20)  NULL,     -- 권한
	`USE_YN`    CHAR(1)      NULL,     -- 사용여부
	`INST_TIME` TIMESTAMP    NULL,     -- 등록시간
	`UPD_TIME`  TIMESTAMP    NULL,     -- 갱신시간
	`INST_USER` VARCHAR(20)  NULL,     -- 등록자
	`UPD_USER`  VARCHAR(20)  NULL      -- 갱신자
);

-- 사용자정보
ALTER TABLE `USER_INFO`
	ADD CONSTRAINT `IDX_POS_USER_PK` -- IDX_POS_USER_PK
		PRIMARY KEY (
			`ID` -- 일련번호
		);

-- UIX_USER_INFO
CREATE UNIQUE INDEX `UIX_USER_INFO`
	ON `USER_INFO` ( -- 사용자정보
		`USER_ID` ASC -- 사용자아이디
	);

-- IX_USER_INFO
CREATE INDEX `IX_USER_INFO`
	ON `USER_INFO`( -- 사용자정보
		`USER_ID` ASC,   -- 사용자아이디
		`USER_NM` ASC,   -- 사용자명
		`AUTH_ROLE` ASC,   -- 권한
		`USE_YN` ASC,    -- 사용여부
		`INST_USER` ASC, -- 등록자
		`INST_TIME` ASC  -- 등록시간
	);
