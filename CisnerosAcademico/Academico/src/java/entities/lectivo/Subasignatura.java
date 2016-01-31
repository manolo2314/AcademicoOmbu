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
public class Subasignatura implements Serializable{
    
    ObjectId idSubasigantura;
    ObjectId idAsignatura;
    String nombre;
    
    String idSubasignaturaString;

    public Subasignatura(ObjectId idAsignatura, String nombre) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
    }
    
    public Subasignatura() {
 
        this.nombre = "";
    }

    public ObjectId getIdSubasigantura() {
        return idSubasigantura;
    }

    public void setIdSubasigantura(ObjectId idSubasigantura) {
        this.idSubasigantura = idSubasigantura;
    }

    public ObjectId getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(ObjectId idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdSubasignaturaString() {
        return idSubasignaturaString;
    }

    public void setIdSubasignaturaString(String idSubasignaturaString) {
        this.idSubasignaturaString = idSubasignaturaString;
    }
    
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("subasignatura");

        BasicDBObject obj = new BasicDBObject("idasignatura", this.idAsignatura).append("nombre", this.nombre);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("subasignatura"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("idasignatura", this.idAsignatura).append("nombre", this.nombre));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idSubasigantura);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Subasignatura get(ObjectId id){
        Subasignatura res = new Subasignatura();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("subasignatura");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("idasignatura", 1);
        fields.put("nombre", 1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idSubasigantura = (ObjectId)obj.get("_id");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.nombre = obj.get("nombre").toString();
          
            
        }
        cursor.close();

        return res;
    }

    public static List<Subasignatura> getAll() {
        List<Subasignatura> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("subasignatura");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Subasignatura res = new Subasignatura();
            DBObject obj = cursor.next();
            res.idSubasigantura = (ObjectId) obj.get("_id");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.nombre = obj.get("nombre").toString();
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("subasignatura");
        

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Subasignatura res = new Subasignatura();
            DBObject obj = cursor.next();
            res.idSubasigantura = (ObjectId) obj.get("_id");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.nombre = obj.get("nombre").toString();
           
        }
        cursor.close();
        return objetos;
    }
    
    public static List<Subasignatura> getAllbyAsignatura(ObjectId idAsig) {
        List<Subasignatura> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("subasignatura");
        BasicDBObject query = new BasicDBObject("idasignatura", idAsig);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("idasignatura", 1);
        fields.put("nombre", 1);


        Cursor cursor = table.find(query, fields);
        
        //DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Subasignatura res = new Subasignatura();
            DBObject obj = cursor.next();
            res.idSubasigantura = (ObjectId) obj.get("_id");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.nombre = obj.get("nombre").toString();

            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubasigantura != null ? idSubasigantura.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subasignatura)) {
            return false;
        }
        Subasignatura other = (Subasignatura) object;
        if ((this.idSubasigantura == null && other.idSubasigantura != null) || (this.idSubasigantura != null && !this.idSubasigantura.equals(other.idSubasigantura))) {
            return false;
        }
        return true;
    }
    
}
