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
public class Anolectivo implements Serializable{
    ObjectId idAnolectivo;
    String nombre;

    String idAnolectivoString;

    public Anolectivo(String nombre) {
        this.nombre = nombre;
    }

    public Anolectivo() {
         this.nombre = "";
    }

    public ObjectId getIdAnolectivo() {
        return idAnolectivo;
    }

    public void setIdAnolectivo(ObjectId idAnolectivo) {
        this.idAnolectivo = idAnolectivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdAnolectivoString() {
        return idAnolectivoString;
    }

    public void setIdAnolectivoString(String idAnolectivoString) {
        this.idAnolectivoString = idAnolectivoString;
    }

    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("anolectivo");

        BasicDBObject obj = new BasicDBObject("nombre", this.nombre);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("anolectivo"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("nombre", this.nombre));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idAnolectivo);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Anolectivo get(ObjectId id){
        Anolectivo res = new Anolectivo();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("anolectivo");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("nombre", 1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idAnolectivo = (ObjectId)obj.get("_id");
            res.nombre = obj.get("nombre").toString();
          
            
        }
        cursor.close();

        return res;
    }

    public static List<Anolectivo> getAll() {
        List<Anolectivo> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("anolectivo");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Anolectivo res = new Anolectivo();
            DBObject obj = cursor.next();
            res.idAnolectivo = (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("anolectivo");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Anolectivo res = new Anolectivo();
            DBObject obj = cursor.next();
            res.idAnolectivo= (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
           
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnolectivo != null ? idAnolectivo.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anolectivo)) {
            return false;
        }
        Anolectivo other = (Anolectivo) object;
        if ((this.idAnolectivo == null && other.idAnolectivo != null) || (this.idAnolectivo != null && !this.idAnolectivo.equals(other.idAnolectivo))) {
            return false;
        }
        return true;
    }
}
