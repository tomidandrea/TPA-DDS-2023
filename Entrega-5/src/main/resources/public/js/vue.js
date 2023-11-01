import {logout} from "../../Templates/Autenticacion-Firebase/app/logout.js"
Vue.component('navbar', {
    template: ` 
    <nav class="navbar navbar-expand-lg pb-5">
        <div class="container-fluid">
        <a class="navbar-brand" href="#">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="./menuDeInicio.html">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="./cargaCSV.html">Carga entidades</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./administracionUsuarios.html">Administración usuarios</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Incidentes
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="./aperturaIncidentes.html">Apertura nuevo incidente</a></li>
                <li><a class="dropdown-item" href="./cierreIncidentes.html">Cierre incidente</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="./consultaIncidentes.html">Consulta incidentes</a></li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Rankings
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="./vistasRankings/vistaRankingCI.html">Mayor cantidad incidentes (CI)</a></li>
                <li><a class="dropdown-item" href="./vistasRankings/vistaRankingTC.html">Mayor tiempo promedio cierre (TC)</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="./consultaRankings.html">Consulta rankings</a></li>
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