# Springboot-Blog
## 1. 개발 환경
* Springboot
* MyBatis
* MySQL
* JSP
* Spring Security
* Lombok

## 2. 요구사항(기능)
* 회원가입
* 로그인
* 게시글 등록
* 게시글 수정
* 게시글 삭제
* 게시글 목록
* 게시글 상세보기
* 게시글 페이징
* 게시글 검색
* 게시글 권한관리
* 댓글 등록
* 댓글 삭제

## 3. 테이블

```sql
create database yndg;
create user yndg@'%' identified by '1234';
grant all privileges on yndg.* to 'yndg'@'%';
```

```sql
CREATE TABLE user(
	id int auto_increment primary key,
    username varchar(100) unique not null,
    password varchar(100) not null,
    email varchar(100),
    profile varchar(200),
    createDate timestamp
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE post(
	id int auto_increment primary key,
    title varchar(100) not null,
    content longtext,
    userId int,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE comment(
	id int auto_increment primary key,
    userId int,
    postId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (postId) references post (id) on delete cascade
) engine=InnoDB default charset=utf8;
```