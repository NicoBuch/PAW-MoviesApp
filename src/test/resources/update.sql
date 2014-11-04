ALTER TABLE user_table
  RENAME TO users;

-- ALTER TABLE comment
-- 	ALTER COLUMN id TYPE int4;

-- ALTER TABLE users
-- 	ALTER COLUMN id TYPE int4;

-- ALTER TABLE movie
-- 	ALTER COLUMN id TYPE int4;

-- ALTER TABLE comment
-- 	ALTER COLUMN movie_id TYPE int4;

-- ALTER TABLE comment
-- 	ALTER COLUMN user_id TYPE int4;

ALTER TABLE comment
	RENAME COLUMN creation_date TO creationdate;

ALTER TABLE movie
	RENAME COLUMN creation_date TO creationdate;

ALTER TABLE movie
	RENAME COLUMN release_date TO releasedate;

ALTER TABLE movie
  ADD COLUMN picture bytea;

ALTER TABLE users
	RENAME COLUMN birth_date TO birthdate;

ALTER TABLE users
	RENAME COLUMN first_name TO firstname;

ALTER TABLE users
	RENAME COLUMN last_name TO lastname;

ALTER TABLE users
	RENAME COLUMN secret_answer TO secretanswer;

ALTER TABLE users
	RENAME COLUMN secret_question TO secretquestion;

ALTER TABLE users
	ADD COLUMN admin boolean DEFAULT FALSE;

CREATE TABLE genre
(
  id serial NOT NULL ,
  name character varying(255) NOT NULL,
  CONSTRAINT pk_genre PRIMARY KEY (id),
  CONSTRAINT uq_genre UNIQUE (name)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE movie_genre
(
  movies_id int4 NOT NULL references movie (id),
  genres_id int4 NOT NULL references genre (id),
  CONSTRAINT pk_movie_genre PRIMARY KEY (movies_id, genres_id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE movie
	DROP COLUMN genre;

CREATE TABLE commentrating
(
  id serial NOT NULL,
  comment_id int4 NOT NULL references comment (id),
  user_id int4 NOT NULL references users (id),
  rating int,
  CONSTRAINT pk_commentRating PRIMARY KEY (id),
  CONSTRAINT uq_commentRating UNIQUE (comment_id, user_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE prize
(
  id serial NOT NULL,
  movie_id int4 NOT NULL references movie (id),
  name character varying(255) NOT NULL,
  prize boolean NOT NULL,
  CONSTRAINT pk_prize PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE report
(
  id serial NOT NULL,
  comment_id int4 NOT NULL references comment (id),
  user_id int4 NOT NULL references users (id),
  CONSTRAINT pk_report PRIMARY KEY (id),
  CONSTRAINT uq_report UNIQUE (comment_id, user_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE users_of_interest
(
  user_id int4 NOT NULL references users (id),
  user_of_interest_id int4 NOT NULL references users (id),
  CONSTRAINT pk_user_of_interest PRIMARY KEY (user_id, user_of_interest_id)
)
WITH (
  OIDS=FALSE
);
