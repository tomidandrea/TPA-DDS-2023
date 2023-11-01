import { onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js"
import { auth } from "./app/firebase.js";
import './app/signupForm.js'
import './app/signinForm.js'
import './app/googleLogin.js'

onAuthStateChanged(auth, async (user) => {
   if(user) {
      console.log(user)
   } else {
      console.log('No hay nadie logueado')
   }
});