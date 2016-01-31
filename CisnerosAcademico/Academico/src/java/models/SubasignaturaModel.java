/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Subasignatura;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class SubasignaturaModel extends ListDataModel<Subasignatura> implements SelectableDataModel<Subasignatura>{

    public SubasignaturaModel() {
    }

    public SubasignaturaModel(List<Subasignatura> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Subasignatura t) {
        return t.getIdSubasigantura();
    }

    @Override
    public Subasignatura getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Subasignatura obj = Subasignatura.get(aux);
       return obj;

    }
    
}
