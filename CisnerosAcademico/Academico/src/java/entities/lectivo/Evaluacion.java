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
public class Evaluacion implements Serializable{
    
    ObjectId idEvaluacion;
    String codigo;
    String nombre;
    
    String idEvaluacionString;

    public Evaluacion(String nombre, String codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Evaluacion() {
        this.codigo = "";
        this.nombre = "";
    }

    public ObjectId getIdEvaluacion() {
        return idEvaluacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIdEvaluacion(ObjectId idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEvaluacionString() {
        return idEvaluacionString;
    }

    public void setIdEvaluacionString(String idEvaluacionString) {
        this.idEvaluacionString = idEvaluacionString;
    }
    
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("evaluacion");

        BasicDBObject obj = new BasicDBObject("nombre", this.nombre).append("codigo", this.codigo);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("evaluacion"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("nombre", this.nombre).append("codigo", this.codigo));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idEvaluacion);
        table.update(searchQuery, newDocument);

    }
    
    
    public static Evaluacion get(ObjectId id){
        Evaluacion res = new Evaluacion();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("evaluacion");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("nombre", 1);
        fields.put("codigo",1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idEvaluacion = (ObjectId)obj.get("_id");
            res.nombre = obj.get("nombre").toString();
            res.codigo = obj.get("codigo").toString();
          
            
        }
        cursor.close();

        return res;
    }

    public static List<Evaluacion> getAll() {
        List<Evaluacion> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("evaluacion");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Evaluacion res = new Evaluacion();
            DBObject obj = cursor.next();
            res.idEvaluacion = (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
            res.codigo = obj.get("codigo").toString();
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("evaluacion");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Evaluacion res = new Evaluacion();
            DBObject obj = cursor.next();
            res.idEvaluacion= (ObjectId) obj.get("_id");
            res.nombre = obj.get("nombre").toString();
            res.codigo = obj.get("codigo").toString();
           
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }
}
