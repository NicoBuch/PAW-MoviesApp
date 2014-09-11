INSERT INTO movie(
            release_date, title, director, genre, minutes, description)
    VALUES ( '2002-2-28', 'El senor de los anillos: La comunidad del anillo', 'Peter Malevolo Jackson','FANTASY', '180', 'The best movie ever');
    
INSERT INTO movie(
            release_date, title, director, genre, minutes, description)
    VALUES ( '2004-2-28', 'El senor de los anillos: Las dos torres', 'Peter Malevolo Jackson','FANTASY', '190', 'The best movie ever2');
INSERT INTO movie(
            release_date, title, director, genre, minutes, description)
    VALUES ( '2008-2-28', 'El senor de los anillos: El retorno del rey', 'Peter Malevolo Jackson','FANTASY', '200', 'The best movie ever3');    
    

    
INSERT INTO comment(
            body, creation_date, rating, movie_id, user_id)
    VALUES ('Buenisima la peli me encanto!', '2002-3-10', '5', '1', '1');
    
INSERT INTO comment(
            body, creation_date, rating, movie_id, user_id)
	VALUES ('No tan buena como la primera...', '2004-3-10', '3', '1', '1');
    
﻿INSERT INTO comment(
            body, creation_date, rating, movie_id, user_id)
	VALUES ('Pesima, una lastima, habian empezado muy bien....', '2008-3-10', '1', '1', '1');