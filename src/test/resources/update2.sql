ALTER TABLE movie
  ADD COLUMN visits integer DEFAULT 0;
ALTER TABLE users
  ADD COLUMN blocked boolean DEFAULT FALSE;
ALTER TABLE report
  ADD COLUMN explanation text;

CREATE TABLE report_history
(
  id serial NOT NULL,
  movie_id int4 NOT NULL references movie (id),
  reporter_id int4 NOT NULL references users (id),
  commentbody text,
  explanation text,
  resolution character varying(25),
  resolutiondate timestamp,
  CONSTRAINT pk_report_history PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
