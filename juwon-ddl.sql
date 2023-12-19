-- 마이페이지 티켓 예매내역
CREATE OR REPLACE VIEW vwUserBook as
SELECT
    U.email,
    UB.user_book_seq,
    T.ticket_type,
    T.person_type,
    T.age,
    TO_CHAR(TB.book_date,'YYYY-MM-DD') as book_date,
    TO_CHAR(TB.visit_date,'YYYY-MM-DD') as visit_date,
    TB.ea,
    TB.price as total_price
FROM tblUserBook UB
JOIN tblTicketBook TB ON UB.ticket_book_seq = TB.ticket_book_seq
JOIN tblUser U ON UB.user_seq = U.user_seq
join tblBenefit B on TB.benefit_seq = B.benefit_seq
join tblTicket T on TB.ticket_seq = T.ticket_seq;

-- 마이페이지 어트랙션 예약내역
CREATE OR REPLACE VIEW vwBookUser as
SELECT
    U.email,
    A.name,
    ab.book_time,
    To_char(bu.regdate, 'YYYY-MM-DD') as regdate,
    bu.capacity,
    bu.book_user_seq
FROM tblBookUser BU
JOIN tblAttractionBook AB ON AB.attraction_book_seq = BU.attraction_book_seq
JOIN tblAttraction A on a.attraction_seq = BU.attraction_seq
join tblUser U on u.user_seq = BU.user_seq;

-- 마이페이지 리뷰 내역
CREATE OR REPLACE VIEW vwreview as
SELECT
    U.email,
    R.review_seq,
    R.subject,
    R.content,
    R.regdate,
    R.readcount,
    UB.user_book_seq
FROM tblUserBook UB
JOIN tblUser U ON UB.user_seq = U.user_seq
join tblReview R on UB.user_book_seq = R.user_book_seq;

-- 마이페이지 구매 내역
CREATE OR REPLACE VIEW vwUserBuy as
SELECT
    U.email,
    S.name as shopName,
    I.name as itemName,
    B.ea,
    I.price,
    B.buy_seq,
    To_char(B.buy_date, 'yyyy-mm-dd') as buy_date,
    UB.user_buy_seq
from tblUserBuy UB
join tblUser U on U.user_seq = UB.user_seq
join tblBuy B on B.buy_seq = UB.buy_seq
join tblItem I on B.item_seq = I.item_seq
join tblShop S on I.shop_seq = S.shop_seq;

-- 마이페이지 이용문의 내역
CREATE OR REPLACE VIEW vwInquiry as
SELECT
    U.email,
    i.inquiry_seq,
    i.type, 
    i.subject,
    i.answer,
    i.content,
    i.attach,
    To_char(i.regdate, 'yyyy-mm-dd') as regdate
from tblUser U
join tblInquiry I on U.user_seq = I.user_seq;

-- 마이페이지 칭찬/불편/건의 내역
CREATE OR REPLACE VIEW vwVOC as
SELECT
    U.email,
    v.voc_seq,
    v.type, 
    v.service_type,
    v.subject,
    v.content,
    To_char(v.regdate, 'yyyy-mm-dd') as regdate,
    v.attach,
    To_char(v.visit_date, 'yyyy-mm-dd') as visit_date,
    v.answer
from tblUser U
join tblVOC v on U.user_seq = v.user_seq;