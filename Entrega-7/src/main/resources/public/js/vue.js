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
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="./../../static/menuDeInicio.html">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="./../cargaCSV">Carga entidades</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./../../static/administracionUsuarios.html">Administración usuarios</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Incidentes
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="./../../static/aperturaIncidentes.html">Apertura nuevo incidente</a></li>
                <li><a class="dropdown-item" href="./../incidentes">Cierre incidente</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="./../../static/consultaIncidentes.html">Consulta incidentes</a></li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Rankings
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="../../../static/vistaRankingCI.html">Mayor cantidad incidentes (CI)</a></li>
                <li><a class="dropdown-item" href="./../../static/vistaRankingTC.html">Mayor tiempo promedio cierre (TC)</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="./../../static/consultaRankings.html">Consulta rankings</a></li>
                </ul>
            </li>
             <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Comunidades
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="#">Crear Comunidad</a></li>
                <li><a class="dropdown-item" href="#">Unirse a Comunidad</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="#">Mis Comunidades</a></li>
                </ul>
            </li>
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
    el: '#app-nav'

})

