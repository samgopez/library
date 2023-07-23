INSERT INTO student(first_name, last_name)
VALUES
    ('Emily', 'Johnson'),
    ('Sophia', 'Lee'),
    ('Noah', 'Rodriguez'),
    ('Benjamin', 'Kim'),
    ('Mason', 'Nguyen');

INSERT INTO book(name, total_copies)
VALUES
    ('Mathematics', 1),
    ('Science', 1),
    ('English', 1),
    ('Geography', 1),
    ('Biology', 1),
    ('Physics', 1),
    ('History', 1),
    ('Music', 1);

INSERT INTO author (first_name, last_name)
VALUES
    ('Jackson', 'Reed'),
    ('Amelia', 'Turner'),
    ('Ethan', 'Parker'),
    ('Isabella', 'Hayes'),
    ('Caleb', 'Mitchell');

INSERT INTO book_authors(book_id, author_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 2),
    (4, 3),
    (4, 4),
    (5, 5),
    (5, 1);

INSERT INTO book_rental(book_id, student_id)
VALUES
    (1, 5),
    (4, 4);
