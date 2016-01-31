/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import entities.lectivo.Estudiante;
import entities.lectivo.Representante;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.EstudianteModel;
import models.RepresentanteModel;

/**
 *
 * @author MINEDUC
 */
@Named
@ViewScoped
public class EstudianteController implements Serializable{
    
    Estudiante actualEstudiante;
    Estudiante selectedEstudiante;
    List<Estudiante> estudiantes;
    List<Estudiante> fiterEstudiante;
    EstudianteModel estudianteModel;
    Date birthday;
    
    
    Representante actualRepresentante;
    Representante selectedRepresentante;
    List<Representante> representantes;
    List<Representante> filterRepresentante;
    RepresentanteModel representanteModel;
    
    Boolean renderRepresentante;
    String nuevoRepresentante;

    public EstudianteController() {
        actualEstudiante = new Estudiante();
        estudiantes = Estudiante.getAll();
        estudianteModel = new EstudianteModel(estudiantes);
        
        actualRepresentante = new Representante();
        representantes = Representante.getAll();
        representanteModel = new RepresentanteModel(representantes);
        
        this.nuevoRepresentante = "";
        renderRepresentante = false;
    }

    public Estudiante getActualEstudiante() {
        return actualEstudiante;
    }

    public void setActualEstudiante(Estudiante actualEstudiante) {
        this.actualEstudiante = actualEstudiante;
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Estudiante> getFiterEstudiante() {
        return fiterEstudiante;
    }

    public void setFiterEstudiante(List<Estudiante> fiterEstudiante) {
        this.fiterEstudiante = fiterEstudiante;
    }

    public EstudianteModel getEstudianteModel() {
        return estudianteModel;
    }

    public void setEstudianteModel(EstudianteModel estudianteModel) {
        this.estudianteModel = estudianteModel;
    }

    public Representante getActualRepresentante() {
        return actualRepresentante;
    }

    public void setActualRepresentante(Representante actualRepresentante) {
        this.actualRepresentante = actualRepresentante;
    }

    public Representante getSelectedRepresentante() {
        return selectedRepresentante;
    }

    public void setSelectedRepresentante(Representante selectedRepresentante) {
        this.selectedRepresentante = selectedRepresentante;
    }

    public List<Representante> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(List<Representante> representantes) {
        this.representantes = representantes;
    }

    public List<Representante> getFilterRepresentante() {
        return filterRepresentante;
    }

    public void setFilterRepresentante(List<Representante> filterRepresentante) {
        this.filterRepresentante = filterRepresentante;
    }

    public RepresentanteModel getRepresentanteModel() {
        return representanteModel;
    }

    public void setRepresentanteModel(RepresentanteModel representanteModel) {
        this.representanteModel = representanteModel;
    }

    public Boolean getRenderRepresentante() {
        return renderRepresentante;
    }

    public void setRenderRepresentante(Boolean renderRepresentante) {
        this.renderRepresentante = renderRepresentante;
    }

    public String getNuevoRepresentante() {
        return nuevoRepresentante;
    }

    public void setNuevoRepresentante(String nuevoRepresentante) {
        this.nuevoRepresentante = nuevoRepresentante;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }    
    
    public void guardarEstudiante(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (actualEstudiante.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad Estudiante.";
        }
        if (actualEstudiante.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos Estudiante.";
        }
        if (actualEstudiante.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres Estudiante.";
        }

        if (actualRepresentante.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad de Representante.";
        }
        if (actualRepresentante.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos Representante.";
        }
        if (actualRepresentante.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres Representante.";
        }
        
        
        try {

            if (check) {
                /*if(!this.birthday.toString().equals("")){
                    actualEstudiante.setFechaNacimiento(this.birthday.toString());
                } */
                
                if((actualRepresentante.getIdRepresentante()!=null)&&(actualRepresentante.getCi().equals(selectedRepresentante.getCi()))){
                    actualEstudiante.setIdRepresentante(selectedRepresentante.getIdRepresentante()); 
                   
                }else{
                     actualEstudiante.setIdRepresentante( actualRepresentante.save()); 
                }
                    
                
                actualEstudiante.save();
                cargarEstudiantes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Estudiante Ingresado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarEstudiantes() {
        estudiantes = Estudiante.getAll();
        actualEstudiante = new Estudiante();        
        estudianteModel = new EstudianteModel(estudiantes);   
        
        representantes = Representante.getAll();
        actualRepresentante = new Representante();
        representanteModel = new RepresentanteModel(representantes);
        selectedRepresentante= new Representante();
        
        this.birthday = null;
    }

    public void updateEstudiante() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();
       
       if (selectedEstudiante.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad Estudiante.";
        }
        if (selectedEstudiante.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos Estudiante.";
        }
        if (selectedEstudiante.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres Estudiante.";
        }

        if (selectedRepresentante.getCi().equals("")) {
            check = false;
            msm += " -- Ingresar Cédula de Identidad de Representante.";
        }
        if (selectedRepresentante.getApellido().equals("")) {
            check = false;
            msm += " -- Ingresar apellidos Representante.";
        }
        if (selectedRepresentante.getNombre().equals("")) {
            check = false;
            msm += " -- Ingresar nombres Representante.";
        }

        try {

            if (check) {
                
                /*if(!this.birthday.toString().equals("")){
                    selectedEstudiante.setFechaNacimiento(this.birthday.toString());
                }*/
                
                selectedEstudiante.update();
                
                selectedRepresentante.update();
                
                cargarEstudiantes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Estudiante Modificado"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    public void selectRepresentanteExistente(){
        actualRepresentante = selectedRepresentante;
    }
    
    public void loadRepresentanteEstudiante(){
        //this.birthday = (Date) selectedEstudiante.getFechaNacimiento();
        selectedRepresentante = Representante.get(selectedEstudiante.getIdRepresentante());
    }
    
}
