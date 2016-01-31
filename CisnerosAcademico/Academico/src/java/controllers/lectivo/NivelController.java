/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Nivel;
import entities.lectivo.Seccion;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.NivelModel;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class NivelController implements Serializable{
    
    Nivel actualNivel;
    Nivel selectedNivel;
    List<Nivel> niveles;
    List<Nivel> filterNivel;
    NivelModel nivelModel;
    
    List<Seccion> secciones;
    Seccion selectedSeccion;
    ObjectId auxId;

    public NivelController() {
        actualNivel = new Nivel();
        niveles = Nivel.getAll();
        nivelModel = new NivelModel(niveles);
        
        secciones = Seccion.getAll();
        
        
        
    }

    public ObjectId getAuxId() {
        return auxId;
    }

    public void setAuxId(ObjectId auxId) {
        this.auxId = auxId;
    }

    
    
    public Nivel getActualNivel() {
        return actualNivel;
    }

    public void setActualNivel(Nivel actualNivel) {
        this.actualNivel = actualNivel;
    }

    public Nivel getSelectedNivel() {
        return selectedNivel;
    }

    public void setSelectedNivel(Nivel selectedNivel) {
        this.selectedNivel = selectedNivel;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Nivel> getFilterNivel() {
        return filterNivel;
    }

    public void setFilterNivel(List<Nivel> filterNivel) {
        this.filterNivel = filterNivel;
    }

    public NivelModel getNivelModel() {
        return nivelModel;
    }

    public void setNivelModel(NivelModel nivelModel) {
        this.nivelModel = nivelModel;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public Seccion getSelectedSeccion() {
        return selectedSeccion;
    }

    public void setSelectedSeccion(Seccion selectedSeccion) {
        this.selectedSeccion = selectedSeccion;
    }    

    public void guardarNivel(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualNivel.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar el nivel.";
        }
        if (actualNivel.getParalelo().equals("")) {
            check = false;
            msm += " -- Ingresar el paralelo.";
        }
        
        if(selectedSeccion== null){
            check = false;
            msm += " -- Escoger la sección.";
        }
       

        try {

            if (check) {
                actualNivel.setIdSeccion(selectedSeccion.getIdSeccion());
                actualNivel.save();
                cargarNivels();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Nivel Ingresado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarNivels() {
        niveles = Nivel.getAll();
        actualNivel = new Nivel();        
        nivelModel = new NivelModel(niveles);      
        
        selectedSeccion = new Seccion();
    }

    public void updateNivel() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedNivel.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar el nivel";
        }
        if (selectedNivel.getParalelo().equals("")) {
            check = false;
            msm += " -- Debe ingresar el paralelo";
        }
        
        if (selectedNivel.getIdSeccion()==null) {
            check = false;
            msm += " -- Debe escoger la sección";
        }

        try {

            if (check) {
                selectedNivel.setIdSeccion(this.selectedSeccion.getIdSeccion());
                selectedNivel.update();
                cargarNivels();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Nivel Modificado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    public void cargaSeccionModificar(){
        this.selectedSeccion = Seccion.get(selectedNivel.getIdSeccion());
    }
    
}
