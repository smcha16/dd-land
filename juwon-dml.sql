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
        
select * from tblreview;

delete from tblReview where review_seq = 5;

select subject,content from tblreview where review_seq = 1;

select * from tblticketbook;
select * from tblreview;
select * from tblreviewimg;

select max(review_seq) as review_seq from tblReview;

select * from tblbuy;
select * from tblitem;