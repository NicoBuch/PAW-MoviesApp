ALTER TABLE movie
  ADD COLUMN visits integer DEFAULT 0;
ALTER TABLE users
  ADD COLUMN blocked boolean DEFAULT FALSE;
