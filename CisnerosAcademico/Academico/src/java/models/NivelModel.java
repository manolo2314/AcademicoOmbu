/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Nivel;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author  VICTOR OQUENDO
 */
public class NivelModel extends ListDataModel<Nivel> implements SelectableDataModel<Nivel>{
    public NivelModel() {
    }

    public NivelModel(List<Nivel> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Nivel t) {
        return t.getIdNivel();
    }

    @Override
    public Nivel getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Nivel obj = Nivel.get(aux);
       return obj;

    }
}
