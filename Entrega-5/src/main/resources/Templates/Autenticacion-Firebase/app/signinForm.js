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

        // me lleva al menu de inicio y me da la bienvenida
        window.location.href="menuDeInicio.html";
        showMessage("Bienvenido " + userCredentials.user.email);

        //TODO ver como se manejan los errores porque se ve que ahora firebase no devuelve los mismos errores
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