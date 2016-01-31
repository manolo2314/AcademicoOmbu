/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Asignatura;
import entities.lectivo.Subasignatura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.AsignaturaModel;
import models.SubasignaturaModel;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class AsignaturaController implements Serializable{
    
    Asignatura actualAsignatura;
    Asignatura selectedAsignatura;
    List<Asignatura> asignaturas;
    List<Asignatura> filterAsignatura;
    AsignaturaModel asignaturaModel;
    
    
    
    String derivada;
    List<Subasignatura> listaSubasignaturas;
    List<Subasignatura> filterSubasignatura;
    SubasignaturaModel subasignaturaModel;
    Subasignatura actualSubasignatura;
    Subasignatura selectedSubasignatura;
    Boolean renderSubasignatura;
    

    public AsignaturaController() {
        actualAsignatura = new Asignatura();
        asignaturas = Asignatura.getAll();
        asignaturaModel = new AsignaturaModel(asignaturas);
        
        
        this.derivada = "";        
        renderSubasignatura = false;
        listaSubasignaturas= new ArrayList<>();        
        actualSubasignatura = new Subasignatura();
        
    }

    public Asignatura getActualAsignatura() {
        return actualAsignatura;
    }

    public void setActualAsignatura(Asignatura actualAsignatura) {
        this.actualAsignatura = actualAsignatura;
    }

    public List<Subasignatura> getListaSubasignaturas() {
        return listaSubasignaturas;
    }

    public void setListaSubasignaturas(List<Subasignatura> listaSubasignaturas) {
        this.listaSubasignaturas = listaSubasignaturas;
    }

    public Subasignatura getActualSubasignatura() {
        return actualSubasignatura;
    }

    public void setActualSubasignatura(Subasignatura actualSubasignatura) {
        this.actualSubasignatura = actualSubasignatura;
    }

    public Subasignatura getSelectedSubasignatura() {
        return selectedSubasignatura;
    }

    public void setSelectedSubasignatura(Subasignatura selectedSubasignatura) {
        this.selectedSubasignatura = selectedSubasignatura;
    }

    public Boolean getRenderSubasignatura() {
        return renderSubasignatura;
    }

    public void setRenderSubasignatura(Boolean renderSubasignatura) {
        this.renderSubasignatura = renderSubasignatura;
    }

    public Asignatura getSelectedAsignatura() {
        return selectedAsignatura;
    }

    public void setSelectedAsignatura(Asignatura selectedAsignatura) {
        this.selectedAsignatura = selectedAsignatura;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public List<Asignatura> getFilterAsignatura() {
        return filterAsignatura;
    }

    public void setFilterAsignatura(List<Asignatura> filterAsignatura) {
        this.filterAsignatura = filterAsignatura;
    }

    public AsignaturaModel getAsignaturaModel() {
        return asignaturaModel;
    }

    public void setAsignaturaModel(AsignaturaModel asignaturaModel) {
        this.asignaturaModel = asignaturaModel;
    }

    public String getDerivada() {
        return derivada;
    }

    public void setDerivada(String derivada) {
        this.derivada = derivada;
    }    

    public List<Subasignatura> getFilterSubasignatura() {
        return filterSubasignatura;
    }

    public void setFilterSubasignatura(List<Subasignatura> filterSubasignatura) {
        this.filterSubasignatura = filterSubasignatura;
    }

    public SubasignaturaModel getSubasignaturaModel() {
        return subasignaturaModel;
    }

    public void setSubasignaturaModel(SubasignaturaModel subasignaturaModel) {
        this.subasignaturaModel = subasignaturaModel;
    }
        
    public void guardarAsignatura(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualAsignatura.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar la asignatura.";
        }

        try {

            if (check) {
                ObjectId auxId = actualAsignatura.save();
                if(listaSubasignaturas.size()>0){
                    for(int i =0; i<listaSubasignaturas.size(); i++){
                        listaSubasignaturas.get(i).setIdAsignatura(auxId);
                        listaSubasignaturas.get(i).save();
                    }
                }else{
                    Subasignatura auxsub = new Subasignatura(auxId, actualAsignatura.getNombre());
                    auxsub.save();
                }
                
                
                
                cargarAsignaturas();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Asignatura Ingresada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarAsignaturas() {
        asignaturas = Asignatura.getAll();
        actualAsignatura = new Asignatura();        
        asignaturaModel = new AsignaturaModel(asignaturas);     
        
        this.derivada="";
        renderSubasignatura = false;
        listaSubasignaturas= new ArrayList<>();        
        actualSubasignatura = new Subasignatura();
        
    }

    public void updateAsignatura() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedAsignatura.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar la asignatura";
        }

        try {

            if (check) {
                selectedAsignatura.update();
                cargarAsignaturas();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Asignatura Modificada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    
    public void agregarSubAsignatura(ActionEvent actionEvent){
        listaSubasignaturas.add(actualSubasignatura);
        actualSubasignatura = new Subasignatura();
    }
    
    public void activarSubasignaturas(){
       
        
        if(this.derivada.equals("1")){
            
            this.renderSubasignatura = true;
    
        }else{
            listaSubasignaturas= new ArrayList<>();        
            actualSubasignatura = new Subasignatura();
            this.renderSubasignatura = false;
        }
       
    }
    
    public void eliminarSubasignatura(){
        
        int i=0;
        
        while (i< listaSubasignaturas.size()){
            if(listaSubasignaturas.get(i) == selectedSubasignatura){
                listaSubasignaturas.remove(i);
                i= listaSubasignaturas.size();
            }
            i++;
        }
    }
    
    
    public void cargaSubasignaturas(){
        listaSubasignaturas = Subasignatura.getAllbyAsignatura(selectedAsignatura.getIdAsignatura());
        subasignaturaModel = new SubasignaturaModel(listaSubasignaturas);
        
    }
    
    
    public void updateSubAsignatura() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedSubasignatura.getNombre().equals("")) {
            check = false;
            msm += " -- Debe ingresar la Subasignatura";
        }

        try {

            if (check) {
                selectedSubasignatura.update();
                cargaSubasignaturas();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "SubAsignatura Modificada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }

}
