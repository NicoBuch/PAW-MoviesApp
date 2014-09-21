INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2002-2-28', 'El senor de los anillos: La comunidad del anillo', 'Peter Malevolo Jackson','FANTASY', '180', 'The best movie ever', '2006-3-20' );

INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2004-2-28', 'El senor de los anillos: Las dos torres', 'Peter Malevolo Jackson','FANTASY', '190', 'The best movie ever2', '2008-4-5');
INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2008-2-28', 'El senor de los anillos: El retorno del rey', 'Peter Malevolo Jackson','FANTASY', '200', 'The best movie ever3', '2011-8-21');

INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2015-4-3', 'Rapido y Furioso 7', 'James Wan','ACTION', '195', 'En Fast & Furious 6 Han (Sung Kang) había comentado que iría a Tokio a sentar cabeza con Gisele antes de que muriera, pero también porque tenía unos asuntos que atender en Tokio, y así llega a su ciudad de origen como lo había querido (The Fast and the Furious: Tokyo Drift), mientras que Dominic Toretto (Vin Diesel), Brian OConner (Paul Walker) y el resto del equipo deciden quedarse en Los Ángeles a retomar las vidas que tuvieron años atrás. Sin embargo, cuando Dom recibe una llamada anónima proveniente de Tokio, descubre que Han fue asesinado por Ian Shaw (Jason Statham), el hermano mayor de Owen Shaw (Luke Evans), que vengó la muerte de su hermano matando a Han. Tras esto, Dom y Brian reúnen al resto del equipo incluyendo al policía Luke Hobbs (Dwayne Johnson), su compañera Elena Neves (Elsa Pataky), su viejo amigo del pasado, Héctor (Noel Guglielmi) y a un conocido de Hobbs, el Agente Neil (Dominic Purcell), para viajar a Tokio y saldar cuentas con el responsable de la muerte de Han.', '2014-10-20' );

INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2014-9-18', 'Relatos Salvajes', 'Damián Szifrón','DRAMA', '122', 'La desigualdad, la injusticia y la exigencia del mundo en que vivimos producen que muchas personas se estresen o se depriman. Algunas explotan. Esta es la película sobre ellos. Vulnerables ante una realidad que súbitamente se altera y se torna impredecible, los protagonistas de Relatos Salvajes cruzan la delgada frontera que divide a la civilización de la barbarie. Una traición amorosa, el retorno de un pasado reprimido, o la violencia contenida en un detalle cotidiano, se presentan para impulsarlos al vértigo de perder los estribos, al innegable placer de perder el control. ', '2014-7-6');
INSERT INTO movie(
                  release_date, title, director, genre, minutes, description, creation_date)
VALUES ( '2013-11-27', 'Frozen: Una Aventura Congelada', 'Peter Malevolo Jackson','ANIMATION', '100', 'La intrépida optimista Anna emprende un viaje épico, junto al alpinista extremo Kristoff y su leal reno Sven, para hallar a su hermana Elsa, cuyos fríos poderes han atrapado al reino Arendelle en un invierno eterno. En una carrera por salvar el reino de la destrucción, Anna y Kristoff se toparán con místicos trols, un divertido muñeco de nieve llamado Olaf, temperaturas extremas y magia en cada rincón', '2014-8-21');

INSERT INTO user_table(
                       first_name, last_name, email, password, secret_question, secret_answer, birth_date)
VALUES ( 'Leo', 'Badi', 'lbadi@bla.com','123', '¿Eu?', 'que', '1991-1-21');
INSERT INTO user_table(
                       first_name, last_name, email, password, secret_question, secret_answer, birth_date)
VALUES ( 'Nicolás', 'Buchhalter', 'nbuchhal@itba.edu.ar','password', '¿Como se llama mi mama?', 'Silvia', '1993-3-10');
INSERT INTO user_table(
                       first_name, last_name, email, password, secret_question, secret_answer, birth_date)
VALUES ( 'Max', 'Power', 'mpower@itba.edu.ar','max', '¿Que juego en mi tiempo libre?', 'DOTA2', '1990-9-19');
INSERT INTO user_table(
                       first_name, last_name, email, password, secret_question, secret_answer, birth_date, vip)
VALUES ( 'Andres', 'Gregoire', 'agregoire@itba.edu.ar','elAndy', '¿Que otra materia enseño a parte de Paw?', 'EDA', '1986-4-15', true);

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('Buenisima la peli me encanto!', '2002-3-10', '5', '1', '1');

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('No tan buena como la primera...', '2004-3-10', '3', '2', '1');

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('Pesima, una lastima, habian empezado muy bien...', '2008-3-10', '1', '3', '1');

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('LLena de emociones e intrigas', '2014-3-10', '5', '5', '2');

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('Espectacular, no puedo dejar de pensar en la peli', '2014-5-15', '5', '5', '3');

INSERT INTO comment(
                    body, creation_date, rating, movie_id, user_id)
VALUES ('Digna de 11 oscares!', '2012-3-14', '4', '3', '3');
