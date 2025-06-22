INSERT INTO TipoObjeto(id, nombre) VALUES (1, 'Arma');
INSERT INTO TipoObjeto(id, nombre) VALUES (2, 'Armadura');
INSERT INTO TipoObjeto(id, nombre) VALUES (3, 'Accesorio');
INSERT INTO TipoObjeto(id, nombre) VALUES (4, 'Consumible');
INSERT INTO TipoObjeto(id, nombre) VALUES (5, 'Basura');

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, consumible, recuperacionVida) VALUES (1, 'Pocion de Curacion Menor', 'Recupera <strong>25%</strong> vida', 4, 1, 3, 100, TRUE, 'potion-1.jpg', TRUE, 0.25);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, consumible, recuperacionVida) VALUES (2, 'Pocion de Curacion', 'Recupera <strong>60%</strong> vida', 4, 2, 3, 200, TRUE, 'potion-2.jpg', TRUE, 0.6);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, consumible, recuperacionVida) VALUES (3, 'Pocion de Curacion Mayor', 'Recupera <strong>100%</strong> vida', 4, 3, 3, 500, TRUE, 'potion-3.jpg', TRUE, 1);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, equipable, ataque) VALUES (4, 'Daga', 'Ataque: <strong>2</strong>', 1, 1, 1, 1000, TRUE, 'daga.jpg', TRUE, 2);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, equipable, ataque) VALUES (5, 'Espada', 'Ataque: <strong>6</strong>', 1, 1, 1, 4000, FALSE, 'espada.jpg', TRUE, 6);

INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (1,1,3,'Mazmorra', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (2,2,5,'Bosque', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (3,4,7,'Castillo', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (4,6,9,'Pueblo Maldito', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (5,8,12,'Catacumbas', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (6,10,14,'Mina Abandonada', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (7,13,16,'Fuerte de Bandidos', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (8,16,18,'Montaña Helada', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (9,18,20,'Desierto', FALSE);
INSERT INTO Nivel(id,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (10,20,25,'Fortaleza', FALSE);

INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (1,'Slime', 1, 75, 75, 5, 5,'Slime.jpg');
INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (2,'Esqueleto', 2, 115, 115, 7, 5,'esqueleto.png');
INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc) VALUES (3,'Bandido',3, 120, 120, 8, 6,'bandido.png');

INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.1-ALPHA', 'Anuncio inicial mockeado');
INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.2-ALPHA', 'Segundo update mockeado, mostrar arriba de todo');


INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (1,1,1,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (2,1,4,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (3,2,3,1);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (4,2,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (5,3,4,3);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (6,3,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (7,4,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (8,5,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (9,6,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (10,7,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (11,8,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (12,9,1,2);
INSERT INTO nivel_intermedio(id,nivel_id, objeto_id, enemigo_id) VALUES (13,10,1,2);