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
 * @author VICTOR OQUENDO
 */
public class Asignatura implements Serializable{
    
    ObjectId idAsignatura;
    String nombre;
    
    String idAsignaturaString;

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }
    
    public Asignatura() {
        this.nombre = "";
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

    public String getIdAsignaturaString() {
        return idAsignaturaString;
    }

    public void setIdAsignaturaString(String idAsignaturaString) {
        this.idAsignaturaString = idAsignaturaString;
    }
    
    
    
    public ObjectId save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("asignatura");

        BasicDBObject obj = new BasicDBObject("nombre", this.nombre);
        table.save(obj);
        
        return obj.getObjectId("_id");
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("asignatura"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("nombre", this.nombre));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idAsignatura);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Asignatura get(ObjectId id){
        Asignatura res = new Asignatura();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("asignatura");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("nombre", 1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idAsignatura = (ObjectId)obj.get("_id");
            res.nombre = obj.get("nombre").toString();
          
            
        }
        cursor.close();

        return res;
    }

    public static List<Asignatura> getAll() {
        List<Asignatura> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("asignatura");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Asignatura res = new Asignatura();
            DBObject obj = cursor.next();
            res.idAsignatura = (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("asignatura");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Asignatura res = new Asignatura();
            DBObject obj = cursor.next();
            res.idAsignatura= (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }
    
}
