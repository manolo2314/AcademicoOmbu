/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Evaluacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class EvaluacionModel extends ListDataModel<Evaluacion> implements SelectableDataModel<Evaluacion>{
    public EvaluacionModel() {
    }

    public EvaluacionModel(List<Evaluacion> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Evaluacion t) {
        return t.getIdEvaluacion();
    }

    @Override
    public Evaluacion getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Evaluacion obj = Evaluacion.get(aux);
       return obj;

    }
}
