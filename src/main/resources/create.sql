CREATE TABLE movie
(
  id serial NOT NULL,
  releasedate date,
  moviename character varying(255),
  directorname character varying(255),
  genre character varying(15),
  minutes integer,
  description text(600),
  CONSTRAINT pk_movie PRIMARY KEY (id),
  CONSTRAINT uq_movie UNIQUE (name)
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

