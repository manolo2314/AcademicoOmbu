/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import entities.Dependencia;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Manolo
 */
public class DependenciaModel extends ListDataModel<Dependencia> implements SelectableDataModel<Dependencia> {

    public DependenciaModel() {
    }

    public DependenciaModel(List<Dependencia> list) {
        super(list);

    }

    @Override
    public Object getRowKey(Dependencia t) {
        return t.getIdDependencia();
    }

    @Override
    public Dependencia getRowData(String id) {
        ObjectId aux = new ObjectId(id);
       Dependencia obj = Dependencia.getDependencia(aux);
       return obj;

    }

}
