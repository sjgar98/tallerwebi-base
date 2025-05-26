function crearJugador() {
    fetch('/spring/api/juego/jugador')
        .then(res => res.json())
        .then(personaje => {
            console.log("Datos recibidos del Personaje:", personaje); // <-- Asegura que llega bien

            if (!personaje || !personaje.nombre) {
                document.getElementById("infoPersonaje").innerHTML = "Personaje no disponible.";
                return;
            }

            document.getElementById("infoPersonaje").innerHTML =
                `<strong>${personaje.nombre}</strong><br>
              Vida: ${personaje.vida}<br>
              Ataque: ${personaje.ataque} <br>`;
        });
}
function crearEnemigo() {
    fetch('/spring/api/juego/enemigo')
        .then(res => res.json())
        .then(personaje => {
            console.log("Datos recibidos del Enemigo:", personaje); // <-- Asegura que llega bien

            if (!personaje || !personaje.nombre) {
                document.getElementById("infoEnemigo").innerHTML = "Enemigo no disponible.";
                return;
            }

            document.getElementById("infoEnemigo").innerHTML =
                `<strong>${personaje.nombre}</strong><br>
              Vida: ${personaje.vida}<br>
              Ataque: ${personaje.ataque} <br>`;
        });
}

function actualizarJugador() {
    fetch('/spring/api/juego/actualizarPersonaje')
        .then(res => res.json())
        .then(personaje => {
            console.log("Datos recibidos del Personaje:", personaje); // <-- Asegura que llega bien

            if (!personaje || !personaje.nombre) {
                document.getElementById("infoPersonaje").innerHTML = "Personaje no disponible.";
                return;
            }

            document.getElementById("infoPersonaje").innerHTML =
                `<strong>${personaje.nombre}</strong><br>
              Vida: ${personaje.vida}<br>
              Ataque: ${personaje.ataque} <br>`;
        });
}

function actualizarEnemigo() {
    fetch('/spring/api/juego/actualizarEnemigo')
        .then(res => res.json())
        .then(personaje => {
            console.log("Datos recibidos del Enemigo:", personaje); // <-- Asegura que llega bien

            if (!personaje || !personaje.nombre) {
                document.getElementById("infoEnemigo").innerHTML = "Enemigo no disponible.";
                return;
            }

            document.getElementById("infoEnemigo").innerHTML =
                `<strong>${personaje.nombre}</strong><br>
              Vida: ${personaje.vida}<br>
              Ataque: ${personaje.ataque} <br>`;
        });
}

function ataqueEnemigo() {
    fetch('/spring/api/juego/ataqueEnemigo', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({})
    })
    crearJugador();
    crearEnemigo();
    document.getElementById("resultado").innerHTML += "Enemigo Realizo Ataque <br>";
    document.getElementById("botonAtaque").disabled = false;
}

function atacar() {
    fetch('/spring/api/juego/atacar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({})
    })
    crearJugador();
    crearEnemigo();
    document.getElementById("resultado").innerHTML += "Jugador Realizo Ataque <br>";
    document.getElementById("botonAtaque").disabled = true;

    // Agregar una pausa visual antes del contraataque
    setTimeout(() => {
        ataqueEnemigo();
    }, 1500); // 1.5 segundos

}
function usarObjeto(objeto) {
    fetch('/spring/api/juego/usarObjeto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(objeto)
    })


    setTimeout(() => {
        crearJugador();
        crearEnemigo();
        cargarObjetos();
    }, 1500);
}

const menuToggle = document.getElementById('botonObjetos');
const menu = document.getElementById('Objetos');
menu.style.display = 'none';

function cargarObjetos() {
    fetch('/spring/api/juego/cargarObjetos')
        .then(res => res.json())
        .then(objetos => {
            console.log(objetos); // deberías ver un array
            const contenedor = document.getElementById('Objetos');
            contenedor.innerHTML = "";
            const nuevosObjetos = document.createElement("div");
            nuevosObjetos.classList.add("mt-3");
            contenedor.appendChild(nuevosObjetos);

            objetos.forEach(o => {

                const boton = document.createElement('button');
                boton.textContent = o.nombre + " x" + o.cantidad;
                boton.classList.add("btn", "btn-success");

                boton.addEventListener('click', () => {
                    console.log("Objeto clickeado:", o.nombre);
                    usarObjeto(o); // llamar a una función pasando el objeto
                       const menu = document.getElementById('Objetos');
                       document.getElementById("resultado").innerHTML += "Se utilizo  "+ o.nombre + " <br>";
                       menu.style.display = 'none';
                });
                nuevosObjetos.appendChild(boton);
            });
        });

}


menuToggle.addEventListener('click', () => {
    // Alternar entre mostrar y ocultar
    if (menu.style.display === 'none' || menu.style.display === '') {
        menu.style.display = 'block';
        cargarObjetos();
    } else {
        menu.style.display = 'none';
    }
});

crearEnemigo();
crearJugador();

