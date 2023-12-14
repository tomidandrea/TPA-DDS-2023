function filtrar(){
    const template = document.getElementById('administracionUsuarios.mustache').innerHTML;
    const rendered = Mustache.render(template, data);
    const selectedTipo = this.value;
    console.log(selectedTipo)
    if (selectedTipo === 'todos') {
        // Si se selecciona "todos", restaurar la lista original de usuarios
        data.usuarios = usuariosOriginales;
    } else {
        // Filtrar por el tipo seleccionado
        const filteredUsuarios = usuariosOriginales.filter(usuario => usuario.tipo === selectedTipo.toLowerCase());
        data.usuarios = filteredUsuarios;
    }
    // renderSelect(data);
}