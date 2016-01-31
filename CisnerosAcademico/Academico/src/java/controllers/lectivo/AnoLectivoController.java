/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Anolectivo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.AnolectivoModel;

/**
 *
 * @author MINEDUC
 */
@Named
@ViewScoped
public class AnoLectivoController implements Serializable{
    
    Anolectivo actualAnolectivo;
    Anolectivo selectedAnolectivo;
    List<Anolectivo> anolectivos;
    List<Anolectivo> filterAnolectivo;
    AnolectivoModel anolectivoModel;

    public AnoLectivoController() {
        actualAnolectivo = new Anolectivo();
        anolectivos = Anolectivo.getAll();
        anolectivoModel = new AnolectivoModel(anolectivos);
    }

    public Anolectivo getActualAnolectivo() {
        return actualAnolectivo;
    }

    public void setActualAnolectivo(Anolectivo actualAnolectivo) {
        this.actualAnolectivo = actualAnolectivo;
    }

    public Anolectivo getSelectedAnolectivo() {
        return selectedAnolectivo;
    }

    public void setSelectedAnolectivo(Anolectivo selectedAnolectivo) {
        this.selectedAnolectivo = selectedAnolectivo;
    }

    public List<Anolectivo> getAnolectivos() {
        return anolectivos;
    }

    public void setAnolectivos(List<Anolectivo> anolectivos) {
        this.anolectivos = anolectivos;
    }

    public List<Anolectivo> getFilterAnolectivo() {
        return filterAnolectivo;
    }

    public void setFilterAnolectivo(List<Anolectivo> filterAnolectivo) {
        this.filterAnolectivo = filterAnolectivo;
    }

    public AnolectivoModel getAnolectivoModel() {
        return anolectivoModel;
    }

    public void setAnolectivoModel(AnolectivoModel anolectivoModel) {
        this.anolectivoModel = anolectivoModel;
    }
    
    public void guardarAnolectivo(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualAnolectivo.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar el año lectivo";
        }

        try {

            if (check) {
                

                actualAnolectivo.save();
                cargarAnolectivos();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Año Lectivo Ingresado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarAnolectivos() {
        anolectivos = Anolectivo.getAll();
        actualAnolectivo = new Anolectivo();        
        anolectivoModel = new AnolectivoModel(anolectivos);        
    }

    public void updateAnolectivo() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedAnolectivo.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar el año lectivo";
        }

        try {

            if (check) {
                selectedAnolectivo.update();
                cargarAnolectivos();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Año Lectivo Modificado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
}
