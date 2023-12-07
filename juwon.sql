-- 티켓예매내역
CREATE OR REPLACE VIEW vwUserBook as
SELECT
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

-- 어트랙션 예약 내역
CREATE OR REPLACE VIEW vwBookUser as
SELECT
    A.name,
    ab.book_time,
    To_char(bu.regdate, 'YYYY-MM-DD') as regdate,
    bu.capacity,
    bu.book_user_seq
FROM tblBookUser BU
JOIN tblAttractionBook AB ON AB.attraction_book_seq = BU.attraction_book_seq
JOIN tblAttraction A on a.attraction_seq = BU.attraction_seq
join tblUser U on u.user_seq = BU.user_seq;

select * from tbluserbook;

select