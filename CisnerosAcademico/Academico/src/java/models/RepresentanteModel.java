/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Representante;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class RepresentanteModel extends ListDataModel<Representante> implements SelectableDataModel<Representante>{
    
    public RepresentanteModel() {
    }

    public RepresentanteModel(List<Representante> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Representante t) {
        return t.getIdRepresentante();
    }

    @Override
    public Representante getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Representante obj = Representante.get(aux);
       return obj;

    }
    
}
