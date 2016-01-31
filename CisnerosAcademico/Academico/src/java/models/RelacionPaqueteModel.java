/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.RelacionPaquete;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class RelacionPaqueteModel extends ListDataModel<RelacionPaquete> implements SelectableDataModel<RelacionPaquete>{

    public RelacionPaqueteModel() {
    }

    public RelacionPaqueteModel(List<RelacionPaquete> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(RelacionPaquete t) {
        return t.getIdRelacionPaquete();
    }

    @Override
    public RelacionPaquete getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       RelacionPaquete obj = RelacionPaquete.get(aux);
       return obj;

    }
    
}
