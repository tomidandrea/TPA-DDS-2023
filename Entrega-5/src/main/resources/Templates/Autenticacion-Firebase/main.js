import { onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js"
import { auth } from "./app/firebase.js";
import './app/signupForm.js'
import './app/signinForm.js'
import './app/googleLogin.js'
//TODO logout cuando ya tengamos un menu de inicio

// list for auth state changes
onAuthStateChanged(auth, async (user) => {
   console.log(user)
});