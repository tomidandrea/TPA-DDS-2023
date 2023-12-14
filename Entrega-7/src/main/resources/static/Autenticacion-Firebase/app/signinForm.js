import { signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js"
import { auth } from "./firebase.js";
import { showMessage } from "./showMessage.js";

const signInForm = document.querySelector("#login-form");

signInForm.addEventListener("submit",async (e) => {
    e.preventDefault();
    const email = signInForm["form2Example17"].value;
    const password = signInForm["form2Example27"].value;


    try {
        //me autentico con firebase
        const userCredentials = await signInWithEmailAndPassword(auth, email, password)
        console.log(userCredentials)
        // reset the form
        //signInForm.reset();

        const credentials = {
            email: email,
            password: password,
        };

        //Envio email y contraseña al backend y me devuelve el id de la sesion
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
                    //throw new Error('Error en la respuesta del servidor');
                    showMessage("Algo salió mal", "error")
                }
                return response.json();
            })
            .then(data => {
                // Manejar la respuesta del servidor
                console.log('Respuesta del servidor:', data);
                // guardo el id de la sesion el el local storage del navegador
                var id = data.idSesion
                localStorage.setItem("IDSESION", id);
                // me lleva al menu de inicio

                var rol = data.rol
                if (rol == "responsableEntidad") {
                    window.location.href = "menuDeInicioAdminEntidadOrganismo.html";
                } else if (rol == "AdminSistema") {
                    window.location.href = "/cargaCSV";
                } else if (rol == "miembro") {
                    window.location.href = "menuDeInicio.html";
                }
                //window.location.href = "menuDeInicio.html";
            }).catch(error => {
                showMessage("Error en el servidor", "error")
            });

       /* //mostrar la bienvenida cuando inicie sesion
        window.onload = function () {
                showMessage("Bienvenido " + userCredentials.user.email, "success")
        };
        */

    } catch (error) {
        console.log(error)
        if (error.code == 'auth/invalid-login-credentials') {
            showMessage("Correo y/o contraseña inválidos", "error")
        } else if (error.code == 'auth/invalid-email') {
            showMessage("Correo electrónico inválido", "error")
        } else if (error.code =='auth/missing-password') {
            showMessage("Debe ingresar una contraseña","error")
        } else {
            showMessage("Algo salió mal", "error")
        }
    }
});

