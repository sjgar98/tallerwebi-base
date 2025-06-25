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

INSERT INTO Efecto(id, nombre, duracionTotal,duracionActual, danioPorTurno) VALUES(1, 'Veneno', 3, 0,10);
INSERT INTO Efecto(id, nombre, duracionTotal,duracionActual, danioPorTurno) VALUES(2, 'Sangrado', 3,0, 15);

INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (1,'Mazmorra',1,3,'Una mazmorra oscura y tenebrosa, plagada de slimes y otras criaturas. Ecos lejanos resuenan en los pasillos húmedos. Varios entraron, pocos lograron contar la historia... ', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (2,'Bosque',2,5,'Un bosque espeso donde la luz apenas logra filtrarse entre los árboles. Las sombras se mueven incluso cuando no hay viento. Se dice que los viajeros pierden la noción del tiempo y del camino.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (3,'Castillo',4,7,'Un castillo antiguo, dominado por el silencio y el eco de pasos que ya no deberían oírse. Sus pasillos guardan secretos, trampas y guardianes que no aceptan visitas. Nadie sabe quién habita allí... o si aún está vivo.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (4,'Pueblo Maldito',6,9,'Una aldea olvidada por los dioses, donde el sol nunca brilla del todo. Sus habitantes caminan con miradas vacías, repitiendo rutinas que parecen ensayadas. Algo podrido habita en el aire, algo que nunca se fue.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (5,'Catacumbas',8,12,'Un laberinto de huesos y piedra bajo la tierra. Las catacumbas susurran nombres de los muertos, y algunos aseguran oír pasos detrás de ellos sin que nadie los siga. Entrar es fácil, salir... no tanto.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (6,'Mina Abandonada',10,14,'La mina fue cerrada hace décadas tras un derrumbe. Desde entonces, extraños ruidos emanan de su interior. Algunos mineros juraron ver figuras moverse en la oscuridad, cavando aún, contra toda lógica.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (7,'Fuerte de Bandidos',13,16,'Un refugio escondido entre ruinas, donde el oro manda y la ley es una ilusión. El fuerte huele a humo, sangre y traición. Todo forastero es una amenaza... o una presa.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (8,'Montaña Helada',16,18,'Una montaña cubierta por un manto eterno de hielo y nieve. Las ventiscas rugen como bestias, y los cuerpos se congelan antes de llegar a la cima. Los pocos refugios están ocupados… por algo que no duerme.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (9,'Desierto',18,20,'Infinito y abrasador, el desierto oculta ruinas milenarias bajo su arena. El calor distorsiona la realidad, y las alucinaciones no siempre son sólo espejismos. Algunos oasis esconden más que agua.', FALSE);
INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado) VALUES (10,'Fortaleza',20,25,'Una estructura imponente, construida para resistir el paso del tiempo y las guerras. Ahora, sus torres están vacías… o eso parece. Algo observa desde las almenas, esperando que te acerques un poco más.', FALSE);

INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc, idEfecto) VALUES (1,'Slime', 1, 75, 75, 5, 5,'Slime.jpg',1);
INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc, idEfecto) VALUES (2,'Esqueleto', 2, 115, 115, 7, 5,'esqueleto.png',0);
INSERT INTO Enemigo(id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa,imagenSrc, idEfecto) VALUES (3,'Bandido',3, 120, 120, 8, 6,'bandido.png',2);

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