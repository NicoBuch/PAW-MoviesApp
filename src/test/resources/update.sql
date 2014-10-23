ALTER TABLE user_table
  RENAME TO users;

ALTER TABLE comment
	ALTER COLUMN id TYPE int4;

ALTER TABLE users
	ALTER COLUMN id TYPE int4;

ALTER TABLE movie
	ALTER COLUMN id TYPE int4;

ALTER TABLE comment
	ALTER COLUMN movie_id TYPE int4;

ALTER TABLE comment
	ALTER COLUMN user_id TYPE int4;

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
