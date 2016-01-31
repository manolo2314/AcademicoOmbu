/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Docente;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class DocenteModel extends ListDataModel<Docente> implements SelectableDataModel<Docente>{
    
    public DocenteModel() {
    }

    public DocenteModel(List<Docente> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Docente t) {
        return t.getIdDocente();
    }

    @Override
    public Docente getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Docente obj = Docente.get(aux);
       return obj;

    }
    
}
