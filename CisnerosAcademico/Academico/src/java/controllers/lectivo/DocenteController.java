/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Docente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.DocenteModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class DocenteController implements Serializable{
    
    Docente actualDocente;
    Docente selectedDocente;
    List<Docente> docentes;
    List<Docente> filterDocente;
    DocenteModel docenteModel;

    public DocenteController() {
        actualDocente = new Docente();
        docentes= Docente.getAll();
        docenteModel = new DocenteModel(docentes);
    }

    public Docente getActualDocente() {
        return actualDocente;
    }

    public void setActualDocente(Docente actualDocente) {
        this.actualDocente = actualDocente;
    }

    public Docente getSelectedDocente() {
        return selectedDocente;
    }

    public void setSelectedDocente(Docente selectedDocente) {
        this.selectedDocente = selectedDocente;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Docente> getFilterDocente() {
        return filterDocente;
    }

    public void setFilterDocente(List<Docente> filterDocente) {
        this.filterDocente = filterDocente;
    }

    public DocenteModel getDocenteModel() {
        return docenteModel;
    }

    public void setDocenteModel(DocenteModel docenteModel) {
        this.docenteModel = docenteModel;
    }
    
    public void guardarDocente(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualDocente.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad.";
        }
        if (actualDocente.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos.";
        }
        if (actualDocente.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres.";
        }

        try {

            if (check) {
                actualDocente.save();
                cargarDocentes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Docente Ingresado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarDocentes() {
        docentes = Docente.getAll();
        actualDocente = new Docente();        
        docenteModel = new DocenteModel(docentes);        
    }

    public void updateDocente() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();
       
        if (selectedDocente.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad.";
        }
        if (selectedDocente.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos.";
        }
        if (selectedDocente.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres.";
        }

        try {

            if (check) {
                selectedDocente.update();
                cargarDocentes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Docente Modificado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    
    
}
