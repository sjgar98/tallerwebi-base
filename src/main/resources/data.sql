INSERT INTO TipoObjeto(id, nombre) VALUES (1, 'Arma');
INSERT INTO TipoObjeto(id, nombre) VALUES (2, 'Armadura');
INSERT INTO TipoObjeto(id, nombre) VALUES (3, 'Accesorio');
INSERT INTO TipoObjeto(id, nombre) VALUES (4, 'Consumible');
INSERT INTO TipoObjeto(id, nombre) VALUES (5, 'Basura');

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (1, 'Pocion de Curacion Menor', 'Recupera <strong>25%</strong> vida', 4, 1, 500, 'potion-1.jpg', TRUE, 0.25);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (2, 'Pocion de Curacion', 'Recupera <strong>60%</strong> vida', 4, 2, 2500, 'potion-2.jpg', TRUE, 0.6);
INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, consumible, recuperacionVida) VALUES (3, 'Pocion de Curacion Mayor', 'Recupera <strong>100%</strong> vida', 4, 3, 5000, 'potion-3.jpg', TRUE, 1);

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, valor, imagenSrc, equipable, ataque) VALUES (4, 'Daga', 'Ataque: 2', 1, 1, 1000, 'potion-2.jpg', TRUE, 2);

INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.1-ALPHA', 'Anuncio inicial mockeado');
INSERT INTO Anuncio(title, content) VALUES ('Release 0.0.2-ALPHA', 'Segundo update mockeado, mostrar arriba de todo');
