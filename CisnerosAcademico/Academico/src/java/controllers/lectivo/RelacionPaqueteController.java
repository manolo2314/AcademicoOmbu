/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;



import entities.lectivo.Anolectivo;
import entities.lectivo.Asignatura;
import entities.lectivo.Docente;
import entities.lectivo.Evaluacion;
import entities.lectivo.Nivel;
import entities.lectivo.RelacionPaquete;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.AnolectivoModel;
import models.AsignaturaModel;
import models.DocenteModel;
import models.EvaluacionModel;
import models.NivelModel;
import models.RelacionPaqueteModel;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;


/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped

public class RelacionPaqueteController implements Serializable{
    
    RelacionPaquete actualRelacionPaquete;
    RelacionPaquete selectedRelacionPaquete;
    List<RelacionPaquete> relacionesPaquetes;
    List<RelacionPaquete> filterRelacionPaquete;
    RelacionPaqueteModel relacionPaqueteModel;
    
    
    
    Anolectivo selectedAnolectivo;
    List<Anolectivo> anolectivos;
    List<Anolectivo> filterAnolectivo;
    AnolectivoModel anolectivoModel;
    
    
    
    DualListModel<Asignatura> asignaturas;
    Asignatura selectedAsignatura;    
    List<Asignatura> filterAsignatura;
    AsignaturaModel asignaturaModel;

    DualListModel<Evaluacion> evaluaciones;
    Evaluacion selectedEvaluacion;
    List<Evaluacion> filterEvaluacion;
    EvaluacionModel evaluacionModel;
    
    Nivel selectedNivel;
    List<Nivel> niveles;
    List<Nivel> filterNivel;
    NivelModel nivelModel;
    

    public RelacionPaqueteController() {
        actualRelacionPaquete = new RelacionPaquete();
        relacionesPaquetes = RelacionPaquete.getAll();
        relacionPaqueteModel = new RelacionPaqueteModel(relacionesPaquetes);
        
        selectedAnolectivo = new Anolectivo();
        anolectivos = Anolectivo.getAll();
        anolectivoModel = new AnolectivoModel(anolectivos);
       
  
        selectedAsignatura = new Asignatura();               
            
        selectedEvaluacion = new Evaluacion();           
        
        
        selectedNivel = new Nivel();
        niveles = Nivel.getAll();
        nivelModel = new NivelModel(niveles);
        
    }
    
    
    @PostConstruct
    public void init() {
        List<Asignatura> asignaturaSource = new ArrayList<>();
        List<Asignatura> asignaturaTarget = new ArrayList<>();
        asignaturaSource =  Asignatura.getAll();
        asignaturas = new DualListModel<>(asignaturaSource, asignaturaTarget);
        
        
        
        List<Evaluacion> evaluacionSource = new ArrayList<>();
        List<Evaluacion> evaluacionTarget = new ArrayList<>();
        evaluacionSource = Evaluacion.getAll();    
        evaluaciones = new DualListModel<>(evaluacionSource, evaluacionTarget);           
    }

    public RelacionPaquete getActualRelacionPaquete() {
        return actualRelacionPaquete;
    }

    public void setActualRelacionPaquete(RelacionPaquete actualRelacionPaquete) {
        this.actualRelacionPaquete = actualRelacionPaquete;
    }

    public RelacionPaquete getSelectedRelacionPaquete() {
        return selectedRelacionPaquete;
    }

    public void setSelectedRelacionPaquete(RelacionPaquete selectedRelacionPaquete) {
        this.selectedRelacionPaquete = selectedRelacionPaquete;
    }

    public List<RelacionPaquete> getRelacionesPaquetes() {
        return relacionesPaquetes;
    }

    public void setRelacionesPaquetes(List<RelacionPaquete> relacionesPaquetes) {
        this.relacionesPaquetes = relacionesPaquetes;
    }

    public List<RelacionPaquete> getFilterRelacionPaquete() {
        return filterRelacionPaquete;
    }

    public void setFilterRelacionPaquete(List<RelacionPaquete> filterRelacionPaquete) {
        this.filterRelacionPaquete = filterRelacionPaquete;
    }

    public RelacionPaqueteModel getRelacionPaqueteModel() {
        return relacionPaqueteModel;
    }

    public void setRelacionPaqueteModel(RelacionPaqueteModel relacionPaqueteModel) {
        this.relacionPaqueteModel = relacionPaqueteModel;
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

    public Asignatura getSelectedAsignatura() {
        return selectedAsignatura;
    }

    public void setSelectedAsignatura(Asignatura selectedAsignatura) {
        this.selectedAsignatura = selectedAsignatura;
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

   

    public Evaluacion getSelectedEvaluacion() {
        return selectedEvaluacion;
    }

    public void setSelectedEvaluacion(Evaluacion selectedEvaluacion) {
        this.selectedEvaluacion = selectedEvaluacion;
    }

    public List<Evaluacion> getFilterEvaluacion() {
        return filterEvaluacion;
    }

    public void setFilterEvaluacion(List<Evaluacion> filterEvaluacion) {
        this.filterEvaluacion = filterEvaluacion;
    }

    public EvaluacionModel getEvaluacionModel() {
        return evaluacionModel;
    }

    public void setEvaluacionModel(EvaluacionModel evaluacionModel) {
        this.evaluacionModel = evaluacionModel;
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

    public DualListModel<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(DualListModel<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public DualListModel<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(DualListModel<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void guardarRelacionPaquete(ActionEvent actionEvent) {
    
        FacesContext context = FacesContext.getCurrentInstance();
        String msm = "";
        Boolean check = true;

        if (selectedAnolectivo == null) {
            check = false;
            msm += " -- Escoger el año lectivo";
        }
                      
        if (selectedNivel == null) {
            check = false;
            msm += " -- Escoger el Grado o Curso";
        }

        if(evaluaciones.getTarget().isEmpty()){
            check = false;
            msm += " -- Escoger las Evaluaciones";
        }
        if(asignaturas.getTarget().isEmpty()){
            check = false;
            msm += " -- Escoger las Asingnaturas";
        }
        
        
        try {

            if (check) {
                
                actualRelacionPaquete.setIdAnoLectivo(selectedAnolectivo.getIdAnolectivo());
                actualRelacionPaquete.setIdNivel(selectedNivel.getIdNivel());
                
                for(int i = 0; i<asignaturas.getTarget().size(); i++){
                    actualRelacionPaquete.setIdAsignatura(asignaturas.getTarget().get(i).getIdAsignatura());
                    
                    for(int j=0; j<evaluaciones.getTarget().size(); j++){
                        actualRelacionPaquete.setIdEvaluacion(evaluaciones.getTarget().get(i).getIdEvaluacion());                        
                        actualRelacionPaquete.save();
                    }
                }
                //actualRelacionPaquete.save();
                cargarRelacionPaquete();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Dstos Ingresados"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
        }
     }
     
     public void cargarRelacionPaquete() {
        actualRelacionPaquete = new RelacionPaquete();
        relacionesPaquetes = RelacionPaquete.getAll();
        relacionPaqueteModel = new RelacionPaqueteModel(relacionesPaquetes); 
        
        
    }

    public void updateAnolectivo() {

        String msm = "";
        Boolean check = true;
        FacesContext context = FacesContext.getCurrentInstance();

       

        if (selectedRelacionPaquete.getIdAnoLectivo() == null) {
            check = false;
            msm += " -- Escoger el año lectivo";
        }
        if (selectedRelacionPaquete.getIdAsignatura() == null) {
            check = false;
            msm += " -- Escoger la Asignatura";
        }
        
        if (selectedRelacionPaquete.getIdEvaluacion() == null) {
            check = false;
            msm += " -- Escoger la evaluación";
        }
        if (selectedRelacionPaquete.getIdNivel() == null) {
            check = false;
            msm += " -- Escoger el Grado o Curso";
        }

        try {

            if (check) {
                selectedRelacionPaquete.update();
                cargarRelacionPaquete();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Datos Modificados"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", msm));
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualización Datos!", ex.getLocalizedMessage()));
        }

    }
    
    
    
    
   public void onTransfer(TransferEvent event) {
        filterAsignatura = asignaturas.getTarget();
    } 
   
   public void pasarAsign(){     
       //filterAsignatura = new ArrayList<Asignatura>();

       filterAsignatura = asignaturas.getTarget();

       
       
       FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
   }
    
   
   
    
}
