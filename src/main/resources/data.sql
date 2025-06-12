INSERT INTO TipoObjeto(id, nombre) VALUES (1, 'Arma');
INSERT INTO TipoObjeto(id, nombre) VALUES (2, 'Armadura');
INSERT INTO TipoObjeto(id, nombre) VALUES (3, 'Accesorio');
INSERT INTO TipoObjeto(id, nombre) VALUES (4, 'Consumible');
INSERT INTO TipoObjeto(id, nombre) VALUES (5, 'Basura');

INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (1,1,5,'Mazmorra', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (2,3,8,'Bosque', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (3,5,10,'Castillo', FALSE);

INSERT INTO Enemigo(id, nombre, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (1,'Slime', 75, 75, 5, 5,'Slime.jpg');
INSERT INTO Enemigo(id, nombre, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (2,'Esqueleto', 115, 115, 7, 5,'esqueleto.png');
INSERT INTO Enemigo(id, nombre, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (3,'Bandido', 120, 120, 8, 6,'bandido.png');

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (1, 'Pocion de Curacion Menor', 'Recupera <strong>25%</strong> vida', 4, 1, 500, 'potion-1.jpg', TRUE, 0.25);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (2, 'Pocion de Curacion', 'Recupera <strong>60%</strong> vida', 4, 2, 2500, 'potion-2.jpg', TRUE, 0.6);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (3, 'Pocion de Curacion Mayor', 'Recupera <strong>100%</strong> vida', 4, 3, 5000, 'potion-3.jpg', TRUE, 1);

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, equipable, ataque) VALUES (4, 'Daga', 'Ataque: 2', 1, 1, 1000, 'potion-2.jpg', TRUE, 2);

INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.1-ALPHA', 'Anuncio inicial mockeado');
INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.2-ALPHA', 'Segundo update mockeado, mostrar arriba de todo');


INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (1,1,1,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (2,1,4,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (3,2,3,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (4,2,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (5,3,4,3);