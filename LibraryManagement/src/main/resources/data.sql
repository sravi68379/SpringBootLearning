-- Authors
INSERT INTO Author (name, bio) VALUES ('J.K. Rowling', 'British author, best known for the Harry Potter series.');
INSERT INTO Author (name, bio) VALUES ('George R.R. Martin', 'American novelist and short story writer in the fantasy, horror, and science fiction genres.');
INSERT INTO Author (name, bio) VALUES ('J.R.R. Tolkien', 'English writer, poet, philologist, and academic, best known as the author of The Hobbit and The Lord of the Rings.');
INSERT INTO Author (name, bio) VALUES ('Stephen King', 'American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels.');

-- Books
-- Note: Published dates are in 'YYYY-MM-DD' format
INSERT INTO Book (title, isbn, published_date) VALUES ('Harry Potter and the Philosopher''s Stone', '9780747532743', '1997-06-26');
INSERT INTO Book (title, isbn, published_date) VALUES ('A Game of Thrones', '9780553103540', '1996-08-01');
INSERT INTO Book (title, isbn, published_date) VALUES ('The Hobbit', '9780618260274', '1937-09-21');
INSERT INTO Book (title, isbn, published_date) VALUES ('The Shining', '9780385121675', '1977-01-28');
INSERT INTO Book (title, isbn, published_date) VALUES ('Harry Potter and the Chamber of Secrets', '9780747538486', '1998-07-02');
INSERT INTO Book (title, isbn, published_date) VALUES ('A Clash of Kings', '9780553108033', '1999-02-02');
INSERT INTO Book (title, isbn, published_date) VALUES ('The Fellowship of the Ring', '9780618260267', '1954-07-29');

-- Link Books to Authors (populate the book_author join table)
-- Book ID 1 (Harry Potter 1) with Author ID 1 (J.K. Rowling)
INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
-- Book ID 2 (A Game of Thrones) with Author ID 2 (George R.R. Martin)
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
-- Book ID 3 (The Hobbit) with Author ID 3 (J.R.R. Tolkien)
INSERT INTO book_author (book_id, author_id) VALUES (3, 3);
-- Book ID 4 (The Shining) with Author ID 4 (Stephen King)
INSERT INTO book_author (book_id, author_id) VALUES (4, 4);
-- Book ID 5 (Harry Potter 2) with Author ID 1 (J.K. Rowling)
INSERT INTO book_author (book_id, author_id) VALUES (5, 1);
-- Book ID 6 (A Clash of Kings) with Author ID 2 (George R.R. Martin)
INSERT INTO book_author (book_id, author_id) VALUES (6, 2);
-- Book ID 7 (The Fellowship of the Ring) with Author ID 3 (J.R.R. Tolkien)
INSERT INTO book_author (book_id, author_id) VALUES (7, 3);

-- Borrowers
INSERT INTO Borrower (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO Borrower (name, email) VALUES ('Jane Smith', 'jane.smith@example.com');
INSERT INTO Borrower (name, email) VALUES ('Alice Brown', 'alice.brown@example.com');

-- Optionally, pre-assign some books to borrowers
-- Update Book table to set borrower_id
-- John Doe (ID 1) borrows Harry Potter 1 (ID 1)
--UPDATE Book SET borrower_id = 1 WHERE id = 1;
-- Jane Smith (ID 2) borrows The Hobbit (ID 3)
--UPDATE Book SET borrower_id = 3 WHERE id = 3; -- Corrected: Jane Smith's ID is 2, The Hobbit's ID is 3
--UPDATE Book SET borrower_id = 2 WHERE id = 3; -- Corrected: Jane Smith (ID 2) borrows The Hobbit (ID 3)