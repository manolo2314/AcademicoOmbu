/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administracion;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import db.MongoManager;
import entities.Dependencia;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.DependenciaModel;

/**
 *
 * @author Manolo
 */
@Named
@ViewScoped
public class DependenciaController implements Serializable {

    Dependencia dependenciaPago;
    Dependencia dependenciaProcesoInicial;
    
    String auxGuiaPago;
    String auxPaga;
    Dependencia dependenciaActual;
    Dependencia selectedDependencia;
    List<Dependencia> dependencias;
    List<Dependencia> filterDependencias;
    DependenciaModel dependenciaModel;

    String auxProcesoInicial;
    String auxMultiplesCodigos;
    
    

    public DependenciaController() {

        dependenciaActual = new Dependencia();
        dependencias = Dependencia.getAllDependencias();
        dependenciaModel = new DependenciaModel(dependencias);
        auxPaga = "";

        dependenciaProcesoInicial = Dependencia.getDependenciaProcesoInicial();
        dependenciaPago = Dependencia.getDependenciaPago();
        auxGuiaPago = "";

        auxProcesoInicial = "";
        auxMultiplesCodigos = "0";

    }

   

    

    public String getAuxProcesoInicial() {
        return auxProcesoInicial;
    }

    public void setAuxProcesoInicial(String auxProcesoInicial) {
        this.auxProcesoInicial = auxProcesoInicial;
    }

    public String getAuxMultiplesCodigos() {
        return auxMultiplesCodigos;
    }

    public void setAuxMultiplesCodigos(String auxMultiplesCodigos) {
        this.auxMultiplesCodigos = auxMultiplesCodigos;
    }

    public String getAuxGuiaPago() {
        return auxGuiaPago;
    }

    public void setAuxGuiaPago(String auxGuiaPago) {
        this.auxGuiaPago = auxGuiaPago;
    }

    public Dependencia getDependenciaActual() {
        return dependenciaActual;
    }

    public void setDependenciaActual(Dependencia dependenciaActual) {
        this.dependenciaActual = dependenciaActual;
    }

    public List<Dependencia> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }

    public List<Dependencia> getFilterDependencias() {
        return filterDependencias;
    }

    public void setFilterDependencias(List<Dependencia> filterDependencias) {
        this.filterDependencias = filterDependencias;
    }

    public String getAuxPaga() {
        return auxPaga;
    }

    public void setAuxPaga(String auxPaga) {
        this.auxPaga = auxPaga;
    }

    public Dependencia getDependenciaPago() {
        return dependenciaPago;
    }

    public void setDependenciaPago(Dependencia dependenciaPago) {
        this.dependenciaPago = dependenciaPago;
    }

    public Dependencia getSelectedDependencia() {
        return selectedDependencia;
    }

    public void setSelectedDependencia(Dependencia selectedDependencia) {
        this.selectedDependencia = selectedDependencia;
    }

    public DependenciaModel getDependenciaModel() {
        return dependenciaModel;
    }

    public void setDependenciaModel(DependenciaModel dependenciaModel) {
        this.dependenciaModel = dependenciaModel;
    }

    public Dependencia getDependenciaProcesoInicial() {
        return dependenciaProcesoInicial;
    }

    public void setDependenciaProcesoInicial(Dependencia dependenciaProcesoInicial) {
        this.dependenciaProcesoInicial = dependenciaProcesoInicial;
    }
    

    public void guardarDependencia(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (dependenciaActual.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar el nombre de la dependencia";
        }

       

        try {

            if (check) {
                

                dependenciaActual.guardarDependencia();
                cargarDependencias();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Dependencia Ingresada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }

    }

    public void cargarDependencias() {
        dependencias = Dependencia.getAllDependencias();
        dependenciaActual = new Dependencia();
        auxPaga = "";
        dependenciaProcesoInicial = Dependencia.getDependenciaProcesoInicial();
        dependenciaPago = Dependencia.getDependenciaPago();

        dependenciaModel = new DependenciaModel(dependencias);
        auxGuiaPago = "";

        auxProcesoInicial = "";
        auxMultiplesCodigos = "0";
    }

    public void updateDependencia() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedDependencia.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar el nombre de la dependencia";
        }

        try {

            if (check) {
                selectedDependencia.UpdateDependencia();
                cargarDependencias();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Dependencia Ingresada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }

}
