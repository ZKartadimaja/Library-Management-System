CREATE TABLE public.books (
	id serial4 NOT NULL,
	title varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	isbn varchar(17) NOT NULL,
	quantity int4 NOT NULL,
	available_copies int4 NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT books_pk PRIMARY KEY (id),
	CONSTRAINT books_unique UNIQUE (isbn)
);

CREATE TABLE public.patrons (
	id serial4 NOT NULL,
	"name" varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	membership_type varchar NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT patrons_pk PRIMARY KEY (id),
	CONSTRAINT patrons_unique UNIQUE (email)
);

CREATE TABLE public.transactions (
	id serial4 NOT NULL,
	patron_id int4 NOT NULL,
	book_id int4 NOT NULL,
	borrowed_date date NULL,
	due_date date NULL,
	returned_date date NULL,
	fine numeric NULL,
	CONSTRAINT transactions_pk PRIMARY KEY (id),
	CONSTRAINT transactions_books_fk FOREIGN KEY (book_id) REFERENCES public.books(id),
	CONSTRAINT transactions_patrons_fk FOREIGN KEY (patron_id) REFERENCES public.patrons(id)
);