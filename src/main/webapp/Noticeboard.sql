drop table notice;


create table notice(
	notice_no number(5) not null primary key,
	title varchar2(200) not null,
	name varchar2(100) not null,
	content varchar2(2000) not null
);

insert into notice values
(1, '안녕하세요', '게시판', '안녕하세요. 반갑습니다, 저희 게시판에 오신 것을 환영합니다.');
insert into notice values
(3, '점심 추천좀', '배고픈', '오늘 점심 뭐 먹을지 추천좀.');
insert into notice values
(4, 'Zzz', '졸린', '잠이 많은 아이.');
insert into notice values 
((select NVL(max(notice_no), 0) + 1 from notice), '이런', '저런', '이런저런이런저런');
/* NVL(null value) : null이 아니라면 max(notice_no)를 반환
 * null이라면 0을 반환 */

select *
from notice
order by notice_no;

delete from notice 
where notice_no = 5;

update notice 
set name = '안녕', title = '안녕하세요', content = '안녕하세요 반갑습니다.'
where notice_no = 10;
