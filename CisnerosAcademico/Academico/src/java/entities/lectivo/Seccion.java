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
public class Seccion implements Serializable{
    ObjectId idSeccion;
    String nombre;
    //String idSeccionString;
    
   

    public Seccion(String nombre) {
        this.nombre = nombre;
    }

    public Seccion() {
        this.nombre ="";
    }

    public ObjectId getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(ObjectId idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

   
    
    
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("seccion");

        BasicDBObject obj = new BasicDBObject("nombre", this.nombre);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("seccion"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("nombre", this.nombre));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idSeccion);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Seccion get(ObjectId id){
        Seccion res = new Seccion();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("seccion");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("nombre", 1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idSeccion = (ObjectId)obj.get("_id");
            res.nombre = obj.get("nombre").toString();
          
            
        }
        cursor.close();

        return res;
    }

    public static List<Seccion> getAll() {
        List<Seccion> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("seccion");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Seccion res = new Seccion();
            DBObject obj = cursor.next();
            res.idSeccion = (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("seccion");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Seccion res = new Seccion();
            DBObject obj = cursor.next();
            res.idSeccion= (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
        }
        cursor.close();
        return objetos;
    }
    
    
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }
    
}
