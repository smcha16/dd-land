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
    SET ing = 'Y'
    WHERE email = 'hwang@kakao.com';
    
INSERT INTO tblReview (review_seq, subject, content, readcount, user_book_seq)
VALUES (seqtblReview.NEXTVAL, #{subject}, #{content}, 0, #{selectedReview});

select * from tbluser;

commit;

INSERT INTO tblReview (review_seq, subject, content, readcount, user_book_seq)
VALUES (seqtblReview.NEXTVAL, '좋은 서비스', '서비스가 매우 만족스러웠습니다.', 10, 1);

INSERT INTO tblReview (review_seq, subject, content, regdate , readcount, user_book_seq)
		VALUES (seqtblReview.NEXTVAL, '테스트', '테스트', default , 0 , 7);
        
select * from tblreview;

delete from tblReview where review_seq = 5;