/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Evaluacion;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.EvaluacionModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class EvaluacionController implements Serializable{
  
    Evaluacion actualEvaluacion;
    Evaluacion selectedEvaluacion;
    List<Evaluacion> evaluaciones;
    List<Evaluacion> filterEvaluacion;
    EvaluacionModel evaluacionModel;

    public EvaluacionController() {
        actualEvaluacion = new Evaluacion();
        evaluaciones = Evaluacion.getAll();
        evaluacionModel = new EvaluacionModel(evaluaciones);
    }

    public Evaluacion getActualEvaluacion() {
        return actualEvaluacion;
    }

    public void setActualEvaluacion(Evaluacion actualEvaluacion) {
        this.actualEvaluacion = actualEvaluacion;
    }

    public List<Evaluacion> getFilterEvaluacion() {
        return filterEvaluacion;
    }

    public void setFilterEvaluacion(List<Evaluacion> filterEvaluacion) {
        this.filterEvaluacion = filterEvaluacion;
    }
    
    public Evaluacion getSelectedEvaluacion() {
        return selectedEvaluacion;
    }

    public void setSelectedEvaluacion(Evaluacion selectedEvaluacion) {
        this.selectedEvaluacion = selectedEvaluacion;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public EvaluacionModel getEvaluacionModel() {
        return evaluacionModel;
    }

    public void setEvaluacionModel(EvaluacionModel evaluacionModel) {
        this.evaluacionModel = evaluacionModel;
    }
    
    public void guardarEvaluacion(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualEvaluacion.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar la Evaluación.";
        }

       

        try {

            if (check) {
                actualEvaluacion.save();
                cargarEvaluacions();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Evaluación Ingresada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarEvaluacions() {
        evaluaciones = Evaluacion.getAll();
        actualEvaluacion = new Evaluacion();        
        evaluacionModel = new EvaluacionModel(evaluaciones);        
    }

    public void updateEvaluacion() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedEvaluacion.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar la Evaluación.";
        }

        try {

            if (check) {
                selectedEvaluacion.update();
                cargarEvaluacions();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Evaluación Modificada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
}
