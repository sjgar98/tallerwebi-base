INSERT INTO TipoObjeto(id, nombre) VALUES
    (1, 'Arma'),
    (2, 'Armadura'),
    (3, 'Accesorio'),
    (4, 'Consumible'),
    (5, 'Basura');

INSERT INTO Objeto(id, nombre, descripcion, tipo_id, rango, maxPorSlot, valor, comprable, imagenSrc, equipable, vidaMaxima, ataque, defensa, consumible, recuperacionVida) VALUES
    (1, 'Pocion de Curacion Menor', 'Recupera <strong>25%</strong> vida', 4, 1, 3, 100, TRUE, 'potion-1.jpg', FALSE, 0, 0, 0, TRUE, 0.25),
    (2, 'Pocion de Curacion', 'Recupera <strong>60%</strong> vida', 4, 2, 3, 200, TRUE, 'potion-2.jpg', FALSE, 0, 0, 0, TRUE, 0.6),
    (3, 'Pocion de Curacion Mayor', 'Recupera <strong>100%</strong> vida', 4, 3, 3, 500, TRUE, 'potion-3.jpg', FALSE, 0, 0, 0, TRUE, 1),
    (4, 'Daga', 'Ataque: <strong>2</strong>', 1, 1, 1, 500, FALSE, 'daga.jpg', TRUE, 0, 2, 0, FALSE, 0),
    (5, 'Espada', 'Ataque: <strong>6</strong>', 1, 2, 1, 1000, TRUE, 'espada.jpg', TRUE, 0, 6, 0, FALSE, 0),
    (6, 'Casco', 'Defensa: <strong>2</strong>', 2, 1, 1, 500, TRUE, 'casco-1.jpg', TRUE, 0, 0, 2, FALSE, 0);

INSERT INTO TipoHabilidad(id, nombre) VALUES
    (1,'Contundente'),
    (2,'Arcana'),
    (3,'Hielo'),
    (4,'Veneno'),
    (5,'Corte');

INSERT INTO Habilidad(id, nombre, tipo_id, consumoMana, danio, nivel) VALUES
    (1, 'Golpe Fuerte', 1, 10, 15, 2),
    (2, 'Puñalada Trapera', 5, 20, 20, 1),
    (3, 'Daga Venenosa', 4, 25, 20, 1),
    (4, 'Placaje', 1, 10, 20, 1),
    (5, 'Mordida', 5, 10, 20, 4);

INSERT INTO Efecto(id, nombre, duracionTotal,duracionActual, danioPorTurno) VALUES
    (1, 'Veneno', 4, 0,10),
    (2, 'Sangrado', 4,0, 15),
     (3, 'Congelado',1, 0,0),
      (4, 'Inmovilizado',1, 0,0),
       (5, 'Cancelado',1, 0,0),
    (10, 'Vacio',0, 0,0);

INSERT INTO Nivel(id, nombre,nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado,oro) VALUES
    (1,'Mazmorra',1,3,'Una mazmorra oscura y tenebrosa, plagada de slimes y otras criaturas. Ecos lejanos resuenan en los pasillos húmedos. Varios entraron, pocos lograron contar la historia... ', FALSE,500),
    (2,'Bosque',1,5,'Un bosque espeso donde la luz apenas logra filtrarse entre los árboles. Las sombras se mueven incluso cuando no hay viento. Se dice que los viajeros pierden la noción del tiempo y del camino.', FALSE,600),
    (3,'Castillo',1,7,'Un castillo antiguo, dominado por el silencio y el eco de pasos que ya no deberían oírse. Sus pasillos guardan secretos, trampas y guardianes que no aceptan visitas. Nadie sabe quién habita allí... o si aún está vivo.', FALSE,750),
    (4,'Pueblo Maldito',1,9,'Una aldea olvidada por los dioses, donde el sol nunca brilla del todo. Sus habitantes caminan con miradas vacías, repitiendo rutinas que parecen ensayadas. Algo podrido habita en el aire, algo que nunca se fue.', FALSE,1500),
    (5,'Catacumbas',1,12,'Un laberinto de huesos y piedra bajo la tierra. Las catacumbas susurran nombres de los muertos, y algunos aseguran oír pasos detrás de ellos sin que nadie los siga. Entrar es fácil, salir... no tanto.', FALSE,1000),
    (6,'Mina Abandonada',2,14,'La mina fue cerrada hace décadas tras un derrumbe. Desde entonces, extraños ruidos emanan de su interior. Algunos mineros juraron ver figuras moverse en la oscuridad, cavando aún, contra toda lógica.', FALSE,1000),
    (7,'Fuerte de Bandidos',2,16,'Un refugio escondido entre ruinas, donde el oro manda y la ley es una ilusión. El fuerte huele a humo, sangre y traición. Todo forastero es una amenaza... o una presa.', FALSE,1000),
    (8,'Montaña Helada',16,18,'Una montaña cubierta por un manto eterno de hielo y nieve. Las ventiscas rugen como bestias, y los cuerpos se congelan antes de llegar a la cima. Los pocos refugios están ocupados… por algo que no duerme.', FALSE,1000),
    (9,'Desierto',18,20,'Infinito y abrasador, el desierto oculta ruinas milenarias bajo su arena. El calor distorsiona la realidad, y las alucinaciones no siempre son sólo espejismos. Algunos oasis esconden más que agua.', FALSE,1000),
    (10,'Fortaleza',20,25,'Una estructura imponente, construida para resistir el paso del tiempo y las guerras. Ahora, sus torres están vacías… o eso parece. Algo observa desde las almenas, esperando que te acerques un poco más.', FALSE,1000);

INSERT INTO Enemigo(
  id, nombre, nivel, vidaActual, vidaMaxima, ataque, defensa, imagenSrc,
  probabilidadAplicarEfecto, probabilidadUsarHabilidad, cantidadDeVecesParaUsarHabilidad, efecto_id)
VALUES
 (1,'Slime', 1, 75, 75, 5, 5,'Slime.jpg', 1, 0.4, 0, NULL),
 (2,'Esqueleto', 2, 105, 105, 6, 5,'esqueleto.png', 0.5, 0.5, 0, 2),
 (3,'Bandido',3, 100, 100, 7, 6,'bandido.png', 0.6, 1, 3, 2),
 (4,'Arquero',4, 90, 90, 9, 4,'arquero.png', 0.6, 0.4, 0, NULL),
 (5,'Vampiro',10, 300, 300, 18, 12,'vampiro.png', 0.4, 0.2, 3, 2),
 (6,'Asesina',5, 110, 110, 12, 6,'arquera.png', 0.5, 0.2, 0, 4),
 (7,'Lider Bandido',6, 150, 150, 13, 10,'bandidofuerte.png', 0.5 ,0.6, 0, NULL),
 (8,'Monstruo del Vacio',5, 100, 100, 16, 6,'monstruo-vacio.png', 0.6, 0.7, 3, 4),
 (9,'Monstruo Helado',5, 125, 125, 10, 10,'monstruo-hielo.png', 0.6, 0.5, 0, 2),
 (10,'Monstruo de Piedra',5, 110, 110, 12, 9,'monstruo-piedra.png', 0.6, 0.3, 3, 2);


INSERT INTO Anuncio(id, title, content) VALUES
    (1, 'Release 0.0.1-ALPHA', 'Anuncio inicial mockeado'),
    (2, 'Release 0.0.2-ALPHA', 'Segundo update mockeado, mostrar arriba de todo');

    INSERT INTO nivel_enemigo(nivel_id, enemigo_id) VALUES
     (1, 1),
     (1, 1),
     (2, 3),
     (2, 3),
     (2, 4),
     (3, 5),
     (4, 2),
     (4, 2),
     (4, 2),
     (5, 8),
     (5, 2),
     (5, 2),
     (6, 8),
     (6, 8),
     (7, 7),
     (7, 3),
     (7, 3),
     (8, 9),
     (8, 9),
     (9, 10),
     (9, 10),
     (10, 5);

    --objetos
    INSERT INTO nivel_objeto(nivel_id, objeto_id) VALUES
    (1, 1),
    (1, 1),
    (1, 4),
    (2, 3),
    (2, 1),
    (3, 4),
    (3, 1),
    (4, 4),
    (5, 3),
    (5, 6),
    (6, 1),
    (6, 1),
    (6, 2),
    (7, 1),
    (8, 1),
    (9, 1),
    (10, 1);


    --Efectos
    INSERT INTO habilidad_efecto (habilidad_id, efecto_id) VALUES
    (1,10),
    (4,10),
    (2,2),
    (3,1);

    INSERT INTO enemigo_habilidad(enemigo_id, habilidad_id) VALUES
    (7,4),
    (5,4),
    (3,2);
