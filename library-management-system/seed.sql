INSERT INTO public.patrons (name, email, membership_type)
VALUES 
  ('John Doe', 'johndoe@example.com', 'Premium'),
  ('Jane Smith', 'janesmith@example.com', 'Regular'),
  ('Michael Johnson', 'michaelj@example.com', 'Regular'),
  ('Emily Brown', 'emilyb@example.com', 'Premium'),
  ('David Lee', 'davidl@example.com', 'Regular'),
  ('Sarah Wilson', 'sarahw@example.com', 'Premium'),
  ('Chris Evans', 'chrise@example.com', 'Regular'),
  ('Sophia Clark', 'sophiac@example.com', 'Premium'),
  ('Robert Lewis', 'robertl@example.com', 'Regular'),
  ('Olivia Martinez', 'oliviam@example.com', 'Premium');

INSERT INTO public.books (title, author, isbn, quantity, available_copies)
VALUES 
  ('To Kill a Mockingbird', 'Harper Lee', '9780060935467', 5, 5),
  ('1984', 'George Orwell', '9780451524935', 8, 8),
  ('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 4, 4),
  ('Pride and Prejudice', 'Jane Austen', '9780141199078', 6, 6),
  ('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 7, 7),
  ('Moby Dick', 'Herman Melville', '9781503280786', 3, 3),
  ('The Hobbit', 'J.R.R. Tolkien', '9780261103283', 10, 10),
  ('Fahrenheit 451', 'Ray Bradbury', '9781451673319', 5, 5),
  ('Brave New World', 'Aldous Huxley', '9780060850524', 9, 9),
  ('Catch-22', 'Joseph Heller', '9781451626659', 4, 4);