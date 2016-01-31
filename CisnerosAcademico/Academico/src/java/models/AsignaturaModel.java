/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Asignatura;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class AsignaturaModel extends ListDataModel<Asignatura> implements SelectableDataModel<Asignatura>{
    
    
    public AsignaturaModel() {
    }

    public AsignaturaModel(List<Asignatura> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Asignatura t) {
        return t.getIdAsignatura();
    }

    @Override
    public Asignatura getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Asignatura obj = Asignatura.get(aux);
       return obj;

    }
}
