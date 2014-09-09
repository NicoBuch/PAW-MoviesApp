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

-- psql -U usuario -d paw7 -a -f create.sql

