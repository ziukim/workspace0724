DROP TABLE TB_MEMBER;
DROP TABLE TB_BOOK;
DROP TABLE TB_PUBLISHER;

-- 실습문제 --
-- 도서관리 프로그램을 만들기 위한 테이블들 만들기 --
-- 이때, 제약조건에 이름을 부여할 것
-- 각 컬럼에 주석 달기
-- 1. 출판사들에 대한 데이터를 담기 위한 출판사 테이블(TB_PUBLISHER)
-- 컬럼 : PUB_NO (출판사 번호) - 기본키 (PUBLISHER_PK)
--        PUB_NAME (출판사명) - NOT NULL (PUBLISHER_NN)
--        PHONE (출판사 전화번호) - 제약조건 없음
CREATE TABLE TB_PUBLISHER (
    PUB_NO   NUMBER CONSTRAINT PUBLISHER_PK PRIMARY KEY,
    PUB_NAME VARCHAR2(100) CONSTRAINT PUBLISHER_NN NOT NULL,
    PHONE    VARCHAR2(13)
);

-- 샘플 3개 정도 생성
INSERT INTO TB_PUBLISHER
VALUES('1', 'KH', '010-1111-2222');
INSERT INTO TB_PUBLISHER
VALUES('2', '길벚', '010-3333-4444');
INSERT INTO TB_PUBLISHER
VALUES('3', '한빛미디어', '010-4444-2222');

SELECT * FROM TB_PUBLISHER;

COMMENT ON COLUMN TB_PUBLISHER.PUB_NO IS '출판사 번호';
COMMENT ON COLUMN TB_PUBLISHER.PUB_NAME IS '출판사명';
COMMENT ON COLUMN TB_PUBLISHER.PHONE IS '출판사 전화번호';

--------------------------------------------------------------------------------
-- 2. 도서들에 대한 데이터를 담기 위한 도서 테이블(TB_BOOK)
-- 컬럼 : BK_NO (도서번호) - 기본키 (BOOK_PK)
--        BK_TITLE (도서명) - NOT NULL (BOOK__NN_TITLE)
--        BK_AUTHOR (저자명) - NOT NULL (BOOK__NN_AUTHOR)
--        BK_PRICE (가격) - 제약조건 없음
--        BK_PUB_NO (출판사 번호) - 외래키 (BOOK_FK), TB_PUBLISHER 테이블 참조
--                                 이때 참조하고 있는 부모데이터 삭제 시 자식데이터도 삭제되도록 옵션 지정
CREATE TABLE TB_BOOK(
    BK_NO     NUMBER CONSTRAINT BOOK_PK PRIMARY KEY,
    BK_TITLE  VARCHAR2(200) CONSTRAINT BOOK_NN_TITLE NOT NULL,
    BK_AUHOR  VARCHAR2(30) CONSTRAINT BOOK_NN_AUTHOR NOT NULL,
    BK_PRICE  NUMBER, 
    BK_PUB_NO NUMBER
    CONSTRAINT BOOK_FK
    REFERENCES TB_PUBLISHER(PUB_NO) 
    ON DELETE CASCADE
);

-- 샘플 5개 정도 생성
INSERT INTO TB_BOOK VALUES(100, '책', '홍길동', 30000,1);
INSERT INTO TB_BOOK VALUES(101, '북책', '홍길동', 40000,1);
INSERT INTO TB_BOOK VALUES(102, '책북', '홍길동', 10000,1);
INSERT INTO TB_BOOK VALUES(103, '북북', '홍길동', 10000,1);
INSERT INTO TB_BOOK VALUES(104, '책북북', '홍길동', 10000,1);

SELECT * FROM TB_BOOK;

COMMENT ON COLUMN TB_BOOK.BK_NO IS '도서번호';
COMMENT ON COLUMN TB_BOOK.BK_TITLE IS '도서명';
COMMENT ON COLUMN TB_BOOK.BK_AUHOR IS '저자명';
COMMENT ON COLUMN TB_BOOK.BK_PRICE IS '가격';
COMMENT ON COLUMN TB_BOOK.BK_PUB_NO IS '출판사 번호';


--------------------------------------------------------------------------------
-- 3. 회원에 대한 데이터를 담기 위한 회원 테이블(TB_MEMBER)
-- 컬럼 : MEMBER_NO (회원번호) - 기본키 (MEMBER_PK)
--        MEMBER_ID (아이디) - 중복금지 (MEMBER_UQ_ID)
--        MEMBER_PWD (비밀번호) - NOT NULL (MEMBER_NN_PWD)
--        MEMBER_NAME (회원명) - NOT NULL (MEMBER_NN_NAME)
--        GENDER (성별) - M 또는 F로 제한 (MEMBER_CK_GEN)
--        ADDRESS (주소) - 제약조건 없음
--        PHONE (연락처) - 제약조건 없음
--        STATUS (탈퇴여부) - 기본값 N, N 또는 Y만 허용 (MEMBER_CK_STA)
--        ENROLL_DATE (가입일) - 기본값 SYSDATE, NOT NULL (MEMBER_NN_EN)
CREATE TABLE TB_MEMBER(
    MEMBER_NO   NUMBER       CONSTRAINT MEMBER_PK PRIMARY KEY, 
    MEMBER_ID   VARCHAR2(20) CONSTRAINT MEMBER_UQ_ID UNIQUE, 
    MEMBER_PWD  VARCHAR2(20) CONSTRAINT MEMBER_NN_PWD NOT NULL, 
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NN_NAME NOT NULL, 
    GENDER      CHAR(1)      CONSTRAINT MEMBER_CK_GEN CHECK(GENDER IN ('M', 'F')), 
    ADDRESS     VARCHAR2(50), 
    PHONE       CHAR(13), 
    STATUS      CHAR(1)      DEFAULT 'N' CONSTRAINT MEMBER_CK_STA CHECK(STATUS IN('N', 'Y')) , 
    ENROLL_DATE DATE         DEFAULT SYSDATE CONSTRAINT MEMBER_NN_EN NOT NULL 
);

-- 샘플 5개 정도 생성
INSERT INTO TB_MEMBER VALUES(200, 'USER01', 'PASS01', '최지원', 'M', NULL, NULL, DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES(201, 'USER02', 'PASS02', '최지투', 'F', NULL, NULL, DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES(202, 'USER03', 'PASS03', '최지삼', 'F', NULL, NULL, DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES(203, 'USER04', 'PASS04', '최지넷', 'M', NULL, NULL, DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES(204, 'USER05', 'PASS05', '최지오', 'F', NULL, NULL, DEFAULT, DEFAULT);

SELECT * FROM TB_MEMBER;



--------------------------------------------------------------------------------
-- 4. 어떤 회원이 어떤 도서를 대여했는지에 대한 대여목록 테이블(TB_RENT)
-- 컬럼 : RENT_NO (대여번호) - 기본키 (RENT_PK)
--        RENT_MEM_NO (대여회원번호) - 외래키 (RENT_FK_MEM), TB_MEMBER 참조
--                                     부모 데이터 삭제 시 자식데이터 값이 NULL이 되도록 지정
--        RENT_BOOK_NO (대여도서번호) - 외래키 (RENT_FK_BOOK), TB_BOOK 참조
--                                      부모 데이터 삭제 시 자식데이터 값이 NULL이 되도록 지정
--        RENT_DATE (대여일) - 기본값 SYSDATE
CREATE TABLE TB_RENT(
    RENT_NO      NUMBER CONSTRAINT RENT_PK PRIMARY KEY,
    RENT_MEM_NO  NUMBER CONSTRAINT RENT_FK_MEM REFERENCES TB_MEMBER(MEMBER_NO) ON DELETE SET NULL,
    RENT_BOOK_NO NUMBER CONSTRAINT RENT_FK_BOOK REFERENCES TB_BOOK(BK_NO) ON DELETE SET NULL,
    RENT_DATE    DATE DEFAULT SYSDATE
);

-- 샘플 3개 정도 생성

INSERT INTO TB_RENT VALUES(300, 200, 100, DEFAULT);
INSERT INTO TB_RENT VALUES(301, 201, 102, DEFAULT);
INSERT INTO TB_RENT VALUES(302, 200, 103, DEFAULT);

SELECT * FROM TB_RENT;
--------------------------------------------------------------------------------
-- 대여목록 조회
-- TB_RENT, TB_MEMBER, TB_BOOK 테이블을 JOIN하여 대여 정보를 조회
--SELECT 
--FROM

SELECT * 
FROM TB_RENT
JOIN TB_MEMBER ON(MEMBER_NO = RENT_MEM_NO)
JOIN TB_BOOK ON (BK_NO = RENT_BOOK_NO);








