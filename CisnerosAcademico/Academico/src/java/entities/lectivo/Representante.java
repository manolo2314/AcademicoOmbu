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
public class Representante implements Serializable{
    
    ObjectId idRepresentante;
    String ci;
    String apellido;
    String nombre;
    String telefono;
    String direccion;
    String email;
    String parentesco;

    public Representante(String ci, String apellido, String nombre, String telefono, String direccion, String email, String parentesco) {
        this.ci = ci;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.parentesco = parentesco;
    }

    public Representante() {
        this.ci = "";
        this.apellido = "";
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.email = "";
        this.parentesco="";
    }

    public ObjectId getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(ObjectId idRepresentante) {
        this.idRepresentante = idRepresentante;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }    
    
    public ObjectId save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("representante");

        BasicDBObject obj = new BasicDBObject("ci", this.ci).append("apellido", this.apellido).append("nombre", this.nombre).append("telefono", this.telefono).append("direccion", this.direccion).append("email", this.email).append("parentesco", this.parentesco);
        table.save(obj);
        
        return obj.getObjectId("_id");
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("representante"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("ci", this.ci).append("apellido", this.apellido).append("nombre", this.nombre).append("telefono", this.telefono).append("direccion", this.direccion).append("email", this.email).append("parentesco", this.parentesco));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idRepresentante);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Representante get(ObjectId id){
        Representante res = new Representante();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("representante");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("ci", 1);
        fields.put("apellido", 1);
        fields.put("nombre", 1);        
        fields.put("telefono", 1);
        fields.put("direccion", 1);
        fields.put("email", 1);
        fields.put("parentesco", 1);
       


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idRepresentante = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();      
            res.telefono = obj.get("telefono").toString();
            res.direccion = obj.get("direccion").toString();
            res.email = obj.get("email").toString();
            res.parentesco= obj.get("parentesco").toString();
 
        }
        cursor.close();

        return res;
    }

    public static List<Representante> getAll() {
        List<Representante> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("representante");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Representante res = new Representante();
            DBObject obj = cursor.next();
            res.idRepresentante = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();      
            res.telefono = obj.get("telefono").toString();
            res.direccion = obj.get("direccion").toString();
            res.email = obj.get("email").toString();
            res.parentesco= obj.get("parentesco").toString();
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("representante");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Representante res = new Representante();
            DBObject obj = cursor.next();
            res.idRepresentante = (ObjectId)obj.get("_id");
            res.ci = obj.get("ci").toString();
            res.apellido = obj.get("apellido").toString();
            res.nombre = obj.get("nombre").toString();      
            res.telefono = obj.get("telefono").toString();
            res.direccion = obj.get("direccion").toString();
            res.email = obj.get("email").toString();
            res.parentesco= obj.get("parentesco").toString();
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepresentante != null ? idRepresentante.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Representante)) {
            return false;
        }
        Representante other = (Representante) object;
        if ((this.idRepresentante == null && other.idRepresentante != null) || (this.idRepresentante != null && !this.idRepresentante.equals(other.idRepresentante))) {
            return false;
        }
        return true;
    }
    
}
