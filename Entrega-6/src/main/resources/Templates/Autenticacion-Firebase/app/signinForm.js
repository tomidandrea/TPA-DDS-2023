import { signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js"
import { auth } from "./firebase.js";
import { showMessage } from "./showMessage.js";

const signInForm = document.querySelector("#login-form");

signInForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const email = signInForm["form2Example17"].value;
    const password = signInForm["form2Example27"].value;

    try {
        const userCredentials = await signInWithEmailAndPassword(auth, email, password)
        console.log(userCredentials)

        // reset the form
        //signInForm.reset();

        //creo sesion en el backend
        const credentials = {
            email: email,
            password: password,
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // Puedes agregar más encabezados según sea necesario, como tokens de autorización, etc.
            },
            body: JSON.stringify(credentials),
        };

        fetch("http://localhost:4567/api/login", requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(data => {
                // Manejar la respuesta del servidor
                console.log('Respuesta del servidor:', data);
                // Puedes almacenar tokens de acceso u otra información relevante aquí
                var id = data.idSesion
                localStorage.setItem("IDSESION", id);
            })
            .catch(error => {
                // Manejar errores de red o del servidor
                console.error('Error al realizar la solicitud:', error);
            });


        // me lleva al menu de inicio y me da la bienvenida
        window.location.href="menuDeInicio.html";
        showMessage("Bienvenido " + userCredentials.user.email);

    } catch (error) {
        console.log(error)
        if (error.code === 'auth/wrong-password') {
            showMessage("Contraseña incorrecta", "error")
        } else if (error.code === 'auth/user-not-found') {
            showMessage("Usuario no encontrado", "error")
        } else {
            showMessage("Algo salió mal", "error")
        }
    }
});