INSERT INTO users(
                       firstname, lastname, email, password, secretquestion, secretanswer, birthdate, vip, admin)
VALUES ( 'Admin', 'Admin', 'admin@admin.com','admin', '¿Que soy?', 'admin', '1914-4-15', true, true);

INSERT INTO genre(name)
VALUES ('action');

INSERT INTO genre(name)
VALUES ('terror');

INSERT INTO genre(name)
VALUES ('thriller');

INSERT INTO genre(name)
VALUES ('drama');

INSERT INTO genre(name)
VALUES ('porn');

INSERT INTO genre(name)
VALUES ('comedy');

INSERT INTO genre(name)
VALUES ('animation');

INSERT INTO genre(name)
VALUES ('fantasy');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('1', '8');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('2', '8');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('3', '8');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('4', '1');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('5', '4');

INSERT INTO movie_genre(movies_id, genres_id)
VALUES ('6', '7');
