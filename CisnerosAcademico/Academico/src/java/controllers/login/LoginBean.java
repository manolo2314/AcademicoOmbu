/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.login;


import entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pabloromero
 */
@Named
@RequestScoped
public class LoginBean implements Serializable {

    private final static String USER_KEY = "auth_user";
    private static final long serialVersionUID = -2152389656664659476L;
    private String nombre;
    private String clave;
    private Integer tipo;
    private boolean logeado = false;
    


    public LoginBean() {
        this.nombre = "";
        this.clave = "";
        this.tipo = -1;
       
        
        
    }


    
    
    
    public static String getUSER_KEY() {
        return USER_KEY;
    }

    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        Usuario usuarioData = Usuario.getUsuario(nombre);

        if (nombre != null && nombre.equals(usuarioData.getNombre()) && clave != null
                && clave.equals(usuarioData.getPassword())) {
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            context.addCallbackParam("view", "index.xhtml");
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }

    /**
     * ******************************************************************************************************
     */
    public void doLogin(ActionEvent e) throws IOException {

        
        
        
        FacesMessage msg = null;

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = "";
        Usuario usuarioData = Usuario.getUsuario(nombre);

        tipo = usuarioData.getTipo();

        if (isSuperAdmin(usuarioData)) {
            url = extContext.encodeActionURL(
                    context.getApplication().getViewHandler().getActionURL(context, "/superadministrador/indexUsuarios.xhtml"));
            extContext.getSessionMap().put(USER_KEY, usuarioData);

            extContext.redirect(url);
            return;
        }else{
            if (isAdminLectivo(usuarioData)) {
                url = extContext.encodeActionURL(
                        context.getApplication().getViewHandler().getActionURL(context, "/administradorLectivo/index.xhtml"));
                extContext.getSessionMap().put(USER_KEY, usuarioData);

                extContext.redirect(url);
                return;
            }else{



           }
   
        }
        
        
        

        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                "Credenciales no válidas");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doGuest(ActionEvent e) throws IOException {
        Usuario usuarioData = new Usuario("invitado", "invitado", 3, "invitado");
        String url;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();

        url = extContext.encodeActionURL(
                context.getApplication().getViewHandler().getActionURL(context, "/guest/index_guest.xhtml"));
        extContext.getSessionMap().put(USER_KEY, usuarioData);

        extContext.redirect(url);
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isSuperAdmin(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 0;
    }
    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isAdminLectivo(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 1;
    }
    
    
    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isAdminCalificaciones(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 2;
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isLectura(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 3;
    }
    /**
     *
     * @param user
     * @param password
     * @return
     */
    
    /**
     *
     * @return
     */
    public String getLoggedUserName() {
        return ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY)).toString();
    }
    
    public String getLoggedUserNombre() {
        Usuario var= ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY));
        return var.getNombre();
    }

    /**
     *
     * @throws IOException
     */
    public void doLogout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
        String url = extContext.encodeActionURL(
                context.getApplication().getViewHandler().getActionURL(context, "/login.xhtml"));
        extContext.redirect(url);
    }
    
    
    
    
}
