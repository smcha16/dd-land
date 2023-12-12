select * from tblreview;
select * from tbluserbook;
select * from tblticketbook;
select * from tblticket;
select * from tblbenefit;

select * from tblbookuser;
select * from tblattractionbook;
select * from tblattraction;

select * from vwUserBook;

select * from tblinquiry;

update tbluser set email='hwang@kakao.com' where user_seq =3;

select * from vwInquiry where email='hwang@kakao.com';

delete from tblInquiry where inquiry_seq = 3;

commit;

select * from tblUser where email = 'hwang@kakao.com';

UPDATE tblUser
    SET address = '역삼 수원'
    WHERE email = 'hwang@kakao.com';
