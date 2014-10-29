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
  movies_id int4 NOT NULL ,
  genres_id int4 NOT NULL,
  CONSTRAINT pk_movie_genre PRIMARY KEY (movies_id, genres_id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE movie
	DROP COLUMN genre;
