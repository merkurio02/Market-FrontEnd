function rellenarFormularioEditar(id){
        $('#nombreEdit').val($('#nombre'+id).text());
        $('#contactoEdit').val($('#contacto'+id).text());
        $('#cargoEdit').val($('#cargo'+id).text());
        $('#direccionEdit').val($('#direccion'+id).text());
        $('#telefonoEdit').val($('#telefono'+id).text());
        $('#idEdit').val(id) ;
    }

function confirmaEliminarProveedor(){
	 return confirm("Al eliminar este proveedor eliminar todos los productos relacionados a el. Esta seguro?");
}

