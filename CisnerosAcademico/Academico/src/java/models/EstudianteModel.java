/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import entities.lectivo.Estudiante;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author MINEDUC
 */
public class EstudianteModel extends ListDataModel<Estudiante> implements SelectableDataModel<Estudiante>{
    
    public EstudianteModel() {
    }

    public EstudianteModel(List<Estudiante> list) {
        super(list);
    }
    
     @Override
    public Object getRowKey(Estudiante t) {
        return t.getIdEstudiante();
    }

    @Override
    public Estudiante getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Estudiante obj = Estudiante.get(aux);
       return obj;

    }
    
}
