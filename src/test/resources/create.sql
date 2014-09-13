﻿CREATE TABLE movie
(
  id serial NOT NULL,
  title character varying(255),
  release_date date,
  director character varying(255),
  genre character varying(15),
  minutes integer,
  description text,
  CONSTRAINT pk_movie PRIMARY KEY (id),
  CONSTRAINT uq_movie UNIQUE (title)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE user_table
(
  id serial NOT NULL ,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  secret_question character varying(255),
  secret_answer character varying(255),
  birth_date date NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT uq_user UNIQUE (email)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE comment
(
  id serial NOT NULL,
  body text,
  creation_date date,
  rating integer check(rating > 0 and rating < 6),
  movie_id integer references movie (id),
  user_id integer references user_table (id),
  CONSTRAINT pk_comment PRIMARY KEY (id),
  CONSTRAINT uq_comment UNIQUE (movie_id, user_id)
)
WITH (
  OIDS=FALSE
);


-- psql -U usuario -d paw7 -a -f create.sql

