// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js"
// https://firebase.google.com/docs/web/setup#available-libraries


// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDJSh2JaBxGQDXxDk_dq4jd7xcs_LIl4XY",
    authDomain: "autenticacion-sso-tpa-dds-2023.firebaseapp.com",
    projectId: "autenticacion-sso-tpa-dds-2023",
    storageBucket: "autenticacion-sso-tpa-dds-2023.appspot.com",
    messagingSenderId: "164678096227",
    appId: "1:164678096227:web:da09b746396154470fef56"
};
// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth(app)
