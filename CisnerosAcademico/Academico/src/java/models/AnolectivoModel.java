/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Anolectivo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class AnolectivoModel extends ListDataModel<Anolectivo> implements SelectableDataModel<Anolectivo>{

    public AnolectivoModel() {
    }

    public AnolectivoModel(List<Anolectivo> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Anolectivo t) {
        return t.getIdAnolectivo();
    }

    @Override
    public Anolectivo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Anolectivo obj = Anolectivo.get(aux);
       return obj;

    }
    
}
