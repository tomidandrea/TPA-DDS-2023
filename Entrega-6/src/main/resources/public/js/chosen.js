$(document).ready(function(){
    $('.chosen-select').chosen({
        placeholder_text_multiple: 'Seleccione servicios asociados...'
    });
    $(".chosen-select").chosen().change(function(e, params){
        values = $(".chosen-select").chosen().val();
        console.log(values);
        //values is an array containing all the results.
    });
  });
