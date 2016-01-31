/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Seccion;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.SeccionModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class SeccionController implements Serializable{
    
    Seccion actualSeccion;
    Seccion selectedSeccion;
    List<Seccion> secciones;
    List<Seccion> filterSeccion;
    SeccionModel seccionModel;

    public SeccionController() {
        actualSeccion = new Seccion();
        secciones = Seccion.getAll();
        seccionModel = new SeccionModel(secciones);
    }

    public Seccion getActualSeccion() {
        return actualSeccion;
    }

    public void setActualSeccion(Seccion actualSeccion) {
        this.actualSeccion = actualSeccion;
    }

    public Seccion getSelectedSeccion() {
        return selectedSeccion;
    }

    public void setSelectedSeccion(Seccion selectedSeccion) {
        this.selectedSeccion = selectedSeccion;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public List<Seccion> getFilterSeccion() {
        return filterSeccion;
    }

    public void setFilterSeccion(List<Seccion> filterSeccion) {
        this.filterSeccion = filterSeccion;
    }

    public SeccionModel getSeccionModel() {
        return seccionModel;
    }

    public void setSeccionModel(SeccionModel seccionModel) {
        this.seccionModel = seccionModel;
    }
    
    public void guardarSeccion(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualSeccion.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar la Sección.";
        }

       

        try {

            if (check) {
                actualSeccion.save();
                cargarSeccions();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Sección Ingresada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarSeccions() {
        secciones = Seccion.getAll();
        actualSeccion = new Seccion();        
        seccionModel = new SeccionModel(secciones);        
    }

    public void updateSeccion() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedSeccion.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar la Sección";
        }

        try {

            if (check) {
                selectedSeccion.update();
                cargarSeccions();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Seccion Modificada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    
    
}
