/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Seccion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class SeccionModel extends ListDataModel<Seccion> implements SelectableDataModel<Seccion>{
    public SeccionModel() {
    }

    public SeccionModel(List<Seccion> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Seccion t) {
        return t.getIdSeccion();
    }

    @Override
    public Seccion getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Seccion obj = Seccion.get(aux);
       return obj;

    }
    
}
