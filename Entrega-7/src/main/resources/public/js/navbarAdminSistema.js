import {logout} from "../../static/Autenticacion-Firebase/app/logout.js"
Vue.component('navbar', {
    template: `
    <nav class="navbar navbar-expand-lg pb-3">
        <div class="container-fluid">
        <a class="navbar-brand" href="#">
        <img src="/public/images/logo.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            </ul>
            <div class="ms-auto cerrar-sesion">
            <button type="button" class="btn btn-dark text-white" @click ="cerrarSesion">
                Cerrar Sesión
            </button>
            </div>
        </div>
        </div>
    </nav>
    `,
    methods: {
        cerrarSesion() {
            console.log("voy a cerrar sesión")
            logout()
        }
    }

})



var app = new Vue({
    el: '#app-nav-admin-sistema'

})