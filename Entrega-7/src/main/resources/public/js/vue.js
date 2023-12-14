import {logout} from "../../static/Autenticacion-Firebase/app/logout.js"
Vue.component('navbar', {
    props:{
        rol:{
            type: String,
            required: true
        }
    },
    template: ` 
    <nav class="navbar navbar-expand-lg pb-3">
        <div class="container-fluid">
        <a class="navbar-brand" href="/static/menuDeInicio.html">
            <img src="/public/images/logo.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            <li class="nav-item">
                <a v-if="rol == 'miembro'" class="nav-link" aria-current="page" href="/static/menuDeInicio.html">Inicio</a>
                <a v-if="rol == 'responsableEntidad'" class="nav-link" aria-current="page" href="/static/menuDeInicioAdminEntidadOrganismo.html">Inicio</a>
            </li>
            <li class="nav-item" v-if="rol == 'miembro'">
                <a class="nav-link" href="/comunidades">Administración comunidad</a>
            </li>
            <li class="nav-item dropdown" v-if="rol == 'miembro'">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Incidentes
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="/static/aperturaIncidentes.html">Apertura nuevo incidente</a></li>
                <li><a class="dropdown-item" href="/incidentes">Cierre incidente</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="/static/consultaIncidentes.html">Consulta incidentes</a></li>
                </ul>
            </li>
            <li class="nav-item" v-if="rol == 'responsableEntidad'">
                <a class="nav-link" href="/static/aperturaIncidentes.html">Apertura nuevo incidente</a>
            </li>
             <li class="nav-item dropdown" v-if="rol == 'miembro'">
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
            <li class="nav-item" v-if="rol == 'AdminSistema'">
                <a class="nav-link" href="/cargaCSV">Carga CSV entidades</a>
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
    el: '#app-nav',
    data:{
        rol:{}
    },
    created() {
        this.fetchData()
    },
    methods:{
        async fetchData() {
            try {
                let idSesion = localStorage.getItem("IDSESION")
                const response = await fetch(`/api/${idSesion}/rol/`);
                const data = await response.json();
                this.rol = data; // Asignar los datos a tu propiedad de datos
                console.log(data)
            } catch (error) {
                console.error('Error al obtener datos:', error);
            }
        }
    }

})

