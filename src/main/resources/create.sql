CREATE TABLE movie
(
  id serial NOT NULL,
  releae_date date,
  title character varying(255),
  director character varying(255),
  genre character varying(15),
  minutes integer,
  description text(600),
  CONSTRAINT pk_movie PRIMARY KEY (id),
  CONSTRAINT uq_movie UNIQUE (title)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE comment
(
  id serial NOT NULL,
  body text(600),
  creation_date date,
  rating integer check(rating > 0 and rating < 6),
  movie_id integer references movie (id),
  user_id integer references user (id),
  CONSTRAINT pk_comment PRIMARY KEY (id),
  CONSTRAINT uq_comment UNIQUE (movie_id, user_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE user_table
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  secret_question character varying(255),
  secret_answer character varying(255),
  birth_date date NOT NULL,
  CONSTRAINT pk_movie PRIMARY KEY (id),
  CONSTRAINT uq_movie UNIQUE (email)
)
WITH (
  OIDS=FALSE
);


-- psql -U usuario -d paw7 -a -f create.sql

