/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administracion;

import entities.Dependencia;
import entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.UsuarioModel;
import org.bson.types.ObjectId;

/**
 *
 * @author victor oquendo
 */
@Named
@ViewScoped
public class UsuariosController implements Serializable {

    String auxIdDep;
    Usuario usuarioActual;
    List<Usuario> usuarios;
    List<Usuario> filterUsuarios;
    String auxTipo;
    Usuario selectedUsuario;
    UsuarioModel usuariosModel;

    List<Dependencia> listaDependencias;

    public UsuariosController() {

        usuarioActual = new Usuario();
        usuarios = Usuario.getAllUsuarios();
        usuariosModel = new UsuarioModel(usuarios);
        auxTipo = "";
        auxIdDep = "";
        listaDependencias = Dependencia.getAllDependencias();

        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();
        Usuario loginBean = (Usuario) sMap.get("auth_user");

        selectedUsuario = loginBean;

    }

    public String getAuxIdDep() {
        return auxIdDep;
    }

    public void setAuxIdDep(String auxIdDep) {
        this.auxIdDep = auxIdDep;
    }

    public List<Dependencia> getListaDependencias() {
        return listaDependencias;
    }

    public void setListaDependencias(List<Dependencia> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public UsuarioModel getUsuariosModel() {
        return usuariosModel;
    }

    public void setUsuariosModel(UsuarioModel usuariosModel) {
        this.usuariosModel = usuariosModel;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getFilterUsuarios() {
        return filterUsuarios;
    }

    public void setFilterUsuarios(List<Usuario> filterUsuarios) {
        this.filterUsuarios = filterUsuarios;
    }

    public String getAuxTipo() {
        return auxTipo;
    }

    public void setAuxTipo(String auxTipo) {
        this.auxTipo = auxTipo;
    }

    public void guardarUsuario(ActionEvent actionEvent) {
        if (!auxTipo.equals("") || (auxIdDep.equals(""))) {

            try {
                usuarioActual.setTipo(Integer.parseInt(auxTipo));

                switch (usuarioActual.getTipo()) {
                    case 0:
                        usuarioActual.setLeyendaTipo("SuperAdministradorConfiguracion");
                        break;
                    case 1:
                        usuarioActual.setLeyendaTipo("SuperAdministradorLectivo");
                        break;
                    case 2:
                        usuarioActual.setLeyendaTipo("AdministradorCalificaciones");
                        break;
                    case 3:
                        usuarioActual.setLeyendaTipo("UsuarioLectura");
                        break;
                    
                }

                usuarioActual.setIdDependencia(new ObjectId(auxIdDep));
                usuarioActual.guardarUsuario();
                cargar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Usuario Ingresado"));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getLocalizedMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionarse un tipo de usuario y la dependencia"));
        }
    }

    public void updateUsuario() {

        switch (selectedUsuario.getTipo()) {
            case 0:
                selectedUsuario.setLeyendaTipo("SuperAdministradorConfiguracion");
                break;
            case 1:
                selectedUsuario.setLeyendaTipo("SuperAdministradorLectivo");
                break;
            case 2:
                selectedUsuario.setLeyendaTipo("AdministradorCalificaciones");
                break;
            case 3:
                selectedUsuario.setLeyendaTipo("UsuarioLectura");
                break;
        }

        selectedUsuario.updateUsuario();
        cargar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Usuario Modificado"));
    }

    public void cargar() {

        usuarioActual = new Usuario();
        usuarios = Usuario.getAllUsuarios();
        usuariosModel = new UsuarioModel(usuarios);
        auxTipo = "";
        auxIdDep = "";

    }
}
