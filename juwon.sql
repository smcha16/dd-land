select * from tblticket;
select * from vwUserBook;
select * from tblticketbook;
CREATE OR REPLACE VIEW vwUserBook as
SELECT
    TB.ticket_book_seq,
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