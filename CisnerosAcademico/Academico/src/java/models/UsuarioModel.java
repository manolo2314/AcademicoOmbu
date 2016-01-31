/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.Usuario;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Manolo
 */
public class UsuarioModel extends ListDataModel<Usuario> implements SelectableDataModel<Usuario>{

    public UsuarioModel() {
    }

    public UsuarioModel(List<Usuario> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Usuario t) {
       return t.getIdUsuario();
    }

    @Override
    public Usuario getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Usuario obj = Usuario.getUsuarioId(aux);
       return obj;
    }
    
    
    
}
