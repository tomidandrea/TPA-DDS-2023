import { signOut } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js";
import { auth } from "./firebase.js";
import { showMessage } from "./showMessage.js";

export async function logout() {

        try {
            await signOut(auth)
            console.log('el usuario cerró sesión')
            window.location.href="./LogIn.hbs"
            showMessage("Se ha cerrado la sesión ")

        } catch (error) {
            console.log(error)
        }
}