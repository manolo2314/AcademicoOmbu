/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.lectivo;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import db.MongoManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author MINEDUC
 */
public class Docente implements Serializable{
    
    ObjectId idDocente;
    String ci;
    String apellido;
    String nombre;
    String titulo;
    String telefono;
    String email;
    String direccion;
    
    String idDocenteString;

    public Docente(String ci, String apellido, String nombre, String titulo, String telefono, String email, String direccion, String idDocenteString) {
        
        this.ci= ci;
        this.apellido = apellido;
        this.nombre = nombre;
        this.titulo = titulo;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.idDocenteString = idDocenteString;
    }

    
    
    public Docente() {
        this.ci= "";
        this.apellido = "";
        this.nombre = "";
        this.titulo = "";
        this.telefono = "";
        this.email = "";
        this.direccion = "";
        this.idDocenteString = "";
    }

    public ObjectId getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(ObjectId idDocente) {
        this.idDocente = idDocente;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdDocenteString() {
        return idDocenteString;
    }

    public void setIdDocenteString(String idDocenteString) {
        this.idDocenteString = idDocenteString;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("docente");

        BasicDBObject obj = new BasicDBObject("ci", this.ci).append("apellido", this.apellido).append("nombre", this.nombre).append("titulo", this.titulo).append("telefono", this.telefono).append("email", this.email).append("direccion", this.direccion);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("docente"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("ci", this.ci).append("apellido", this.apellido).append("nombre", this.nombre).append("titulo", this.titulo).append("telefono", this.telefono).append("email", this.email).append("direccion", this.direccion));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idDocente);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Docente get(ObjectId id){
        Docente res = new Docente();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("docente");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("ci", 1);
        fields.put("apellido", 1);
        fields.put("nombre", 1);
        fields.put("titulo", 1);
        fields.put("telefono", 1);
        fields.put("email", 1);
        fields.put("direccion", 1);
       


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idDocente = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();
            res.titulo = obj.get("titulo").toString();
            res.telefono = obj.get("telefono").toString();
            res.email = obj.get("email").toString();
            res.direccion = obj.get("direccion").toString();
            
        }
        cursor.close();

        return res;
    }

    public static List<Docente> getAll() {
        List<Docente> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("docente");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Docente res = new Docente();
            DBObject obj = cursor.next();
            res.idDocente = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();
            res.titulo = obj.get("titulo").toString();
            res.telefono = obj.get("telefono").toString();
            res.email = obj.get("email").toString();
            res.direccion = obj.get("direccion").toString();
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("docente");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Docente res = new Docente();
            DBObject obj = cursor.next();
            res.idDocente = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();
            res.titulo = obj.get("titulo").toString();
            res.telefono = obj.get("telefono").toString();
            res.email = obj.get("email").toString();
            res.direccion = obj.get("direccion").toString();
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }
}
