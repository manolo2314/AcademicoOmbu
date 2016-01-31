/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administracion;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author victor oquendo
 */
@ViewScoped
@Named
public class MenuGeneralAdminController {

    String usuarios;
    String dependencias;
    String indexUsuarios;

   
    public MenuGeneralAdminController() {

        

        this.usuarios = "crearUsuarios";
        this.dependencias = "crearDependencias";
        this.indexUsuarios = "indexUsuarios";

        
    }

    public void reloadSession() {
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.administracion.UsuariosController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.administracion.DependenciaController-null-false");

    }

    public String goToIndexUsuarios(){
        reloadSession();
        return indexUsuarios;
    }
    
    public String goToUsuarios() {
        reloadSession();
        return usuarios;
    }

    public String goToDependencias() {
        reloadSession();
        return dependencias;
    }

    
}
