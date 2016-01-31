/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.lectivo;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Victor Oquendo
 */
@ViewScoped
@Named
public class MenuLectivoController {
    String index;
    
    String anoLectivo;
    String asignatura;
    String docente;
    String estudiante;
    String evaluacion;
    String nivel;
    String relacionMatricula;
    String relacionPaquete;
    String representante;
    String subasignatura;
    String seccion;

    public MenuLectivoController() {
        this.index = "index";
        
        this.anoLectivo = "crearAnolectivo";
        this.asignatura = "crearAsignatura";
        this.docente = "crearDocente";
        this.estudiante = "crearEstudiante";
        this.evaluacion = "crearEvaluacion";
        this.nivel = "crearNivel";
        this.relacionMatricula = "crearRelacionMatricula";
        this.relacionPaquete = "crearRelacionPaquete";
        this.representante = "crearRepresentante";
        this.subasignatura = "crearSubasignatura";
        this.seccion = "crearSeccion";
    }
    
    public void reloadSession(){
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.AnoLectivoController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.AsignaturaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.DocenteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.EstudianteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.EvaluacionController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.NivelController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.RelacionMatriculaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.RelacionPaqueteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.RepresentanteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.SubasignaturaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.lectivo.SeccionController-null-false");
        
    }
    
    public String goToIndex(){
        reloadSession();
        return index;
    }
    
    public String goToAnoLectivo() {
        reloadSession();
        return anoLectivo;
    }
    
    public String goToAsignatura() {
        reloadSession();
        return asignatura;
    }
    
    public String goToDocente() {
        reloadSession();
        return docente;
    }
    
    public String goToEstudiante() {
        reloadSession();
        return estudiante;
    }
    
    public String goToEvaluacion() {
        reloadSession();
        return evaluacion;
    }
    
    public String goToNivel() {
        reloadSession();
        return nivel;
    }
    
    public String goToRelacionMatricula() {
        reloadSession();
        return relacionMatricula;
    }
    
    public String goToRelacionPaquete() {
        reloadSession();
        return relacionPaquete;
    }
    
    public String goToRepresentante() {
        reloadSession();
        return representante;
    }
    
    public String goToSubasignatura() {
        reloadSession();
        return subasignatura;
    }
    
    public String goToSeccion() {
        reloadSession();
        return seccion;
    }
    
}
