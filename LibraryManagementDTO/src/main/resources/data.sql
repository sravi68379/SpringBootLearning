
INSERT INTO AUTHOR (name, bio) VALUES ('J.R.R. Tolkien', 'Author of The Lord of the Rings.'); /* ID will be 1 */
INSERT INTO AUTHOR (name, bio) VALUES ('George R.R. Martin', 'Author of A Song of Ice and Fire.'); /* ID will be 2 */
INSERT INTO AUTHOR (name, bio) VALUES ('Neil Gaiman', 'Author of Sandman and American Gods.'); /* ID will be 3 */


INSERT INTO BORROWER (name, email) VALUES ('Alice Smith', 'alice.smith@example.com'); /* ID will be 1 */
INSERT INTO BORROWER (name, email) VALUES ('Bob Johnson', 'bob.johnson@example.com'); /* ID will be 2 */


INSERT INTO BOOK (title, isbn, published_date, borrower_id) VALUES ('The Hobbit', '978-0547928227', '1937-09-21', NULL); /* ID will be 1 */
INSERT INTO BOOK (title, isbn, published_date, borrower_id) VALUES ('A Game of Thrones', '978-0553103540', '1996-08-06', NULL); /* ID will be 2 */
INSERT INTO BOOK (title, isbn, published_date, borrower_id) VALUES ('Good Omens', '978-0575048003', '1990-05-01', NULL); /* ID will be 3 */


INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (1, 1);

INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (2, 2);

INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (3, 1);
INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (3, 3);


UPDATE BOOK SET borrower_id = 1 WHERE id = 3;


UPDATE BOOK SET borrower_id = 2 WHERE id = 1;
UPDATE BOOK SET borrower_id = 2 WHERE id = 2;
