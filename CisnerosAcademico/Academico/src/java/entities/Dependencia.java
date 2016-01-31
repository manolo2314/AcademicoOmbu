/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author Manolo
 */
public class Dependencia implements Serializable {

    ObjectId idDependencia;
    String nombre;
   
    public Dependencia() {

        this.nombre = "";
        
       
    }

    public Dependencia(ObjectId idDependencia, String nombre) {
        this.idDependencia = idDependencia;
        this.nombre = nombre;
        
    }

    

    
    
    public ObjectId getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(ObjectId idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public void guardarDependencia() {
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");

        BasicDBObject docObj = new BasicDBObject();

        docObj.put("nombre", this.nombre);
       
        
        table.save(docObj);

    }
    
    public  void UpdateDependencia(){
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("dependencia"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("nombre", this.nombre));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idDependencia);
        table.update(searchQuery, newDocument);
    }
    
    public static Dependencia getDependencia(ObjectId id){
        Dependencia dep = new Dependencia();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("nombre", 1);
        

        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            dep.idDependencia = (ObjectId)obj.get("_id");
            //dep.idDependenciaString = obj.get("_id").toString();
            dep.nombre = obj.get("nombre").toString();
            
        }
        cursor.close();

        return dep;
    }

    public static List<Dependencia> getAllDependencias() {
        List<Dependencia> dependencias = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Dependencia objDep = new Dependencia();
            DBObject obj = cursor.next();
            objDep.idDependencia = (ObjectId) obj.get("_id");
           
            objDep.nombre = obj.get("nombre").toString();
            
            dependencias.add(objDep);
        }
        cursor.close();
        return dependencias;
    }

    
    
    public static Map<String,String> getAllDependenciasMap() {
        Map<String,String> dependencias = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Dependencia objDep = new Dependencia();
            DBObject obj = cursor.next();
            objDep.idDependencia = (ObjectId) obj.get("_id");
           
            objDep.nombre = obj.get("nombre").toString();
           
            
        }
        cursor.close();
        return dependencias;
    }
    
    
    public static Dependencia getDependenciaPago() {
        Dependencia dep = new Dependencia();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");
        BasicDBObject query = new BasicDBObject("entidad_pago", 1);

        BasicDBObject fields = new BasicDBObject();
        fields.put("nombre", 1);
        fields.put("entidad_pago", 1);
        fields.put("guia_pago", 1);
        fields.put("proceso_inicial",1);
        fields.put("multiples_codigos",1);

        Cursor cursor = table.find(query, fields).sort(new BasicDBObject("_id", -1)).limit(1);

        boolean found = false;

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            dep.nombre = obj.get("nombre").toString();
           
            
        }

        return dep;
    }

    
    
    public static Dependencia getDependenciaProcesoInicial() {
        Dependencia dep = new Dependencia();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("dependencia");
        BasicDBObject query = new BasicDBObject("proceso_inicial", 1);

        BasicDBObject fields = new BasicDBObject();
        fields.put("nombre", 1);
       

        Cursor cursor = table.find(query, fields).sort(new BasicDBObject("_id", -1)).limit(1);

        boolean found = false;

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            dep.nombre = obj.get("nombre").toString();
            
            
        }

        return dep;
    }
    
}
