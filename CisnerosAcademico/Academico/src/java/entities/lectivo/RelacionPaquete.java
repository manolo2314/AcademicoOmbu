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
public class RelacionPaquete implements Serializable{
    ObjectId idRelacionPaquete;
    
    ObjectId idNivel;
    ObjectId idAsignatura;
    ObjectId idEvaluacion;  
    ObjectId idAnoLectivo;

    public RelacionPaquete() {
    }

    public ObjectId getIdRelacionPaquete() {
        return idRelacionPaquete;
    }

    public void setIdRelacionPaquete(ObjectId idRelacionPaquete) {
        this.idRelacionPaquete = idRelacionPaquete;
    }

    

    public ObjectId getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(ObjectId idNivel) {
        this.idNivel = idNivel;
    }

    public ObjectId getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(ObjectId idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public ObjectId getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(ObjectId idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public ObjectId getIdAnoLectivo() {
        return idAnoLectivo;
    }

    public void setIdAnoLectivo(ObjectId idAnoLectivo) {
        this.idAnoLectivo = idAnoLectivo;
    }
    
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionpaquete");

        BasicDBObject obj = new BasicDBObject("idnivel", this.idNivel).append("idasignatura", this.idAsignatura).append("idevaluacion", this.idEvaluacion).append("idanolectivo", this.idAnoLectivo);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("relacionpaquete"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("idnivel", this.idNivel).append("idasignatura", this.idAsignatura).append("idevaluacion", this.idEvaluacion).append("idanolectivo", this.idAnoLectivo));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idRelacionPaquete);
        table.update(searchQuery, newDocument);

    }
    
    
    public static RelacionPaquete get(ObjectId id){
        RelacionPaquete res = new RelacionPaquete();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionpaquete");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        
        fields.put("idnivel", 1);
        fields.put("idasignatura", 1);
        fields.put("idevaluacion", 1);
        fields.put("idanolectivo", 1);


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idRelacionPaquete = (ObjectId) obj.get("_id");
           
            res.idNivel = (ObjectId) obj.get("idnivel");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.idEvaluacion = (ObjectId) obj.get("idevaluacion");
            res.idAnoLectivo = (ObjectId) obj.get("idanolectivo");
          
            
        }
        cursor.close();

        return res;
    }

    public static List<RelacionPaquete> getAll() {
        List<RelacionPaquete> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionpaquete");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            RelacionPaquete res = new RelacionPaquete();
            DBObject obj = cursor.next();
            res.idRelacionPaquete = (ObjectId) obj.get("_id");
           
            res.idNivel = (ObjectId) obj.get("idnivel");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.idEvaluacion = (ObjectId) obj.get("idevaluacion");
            res.idAnoLectivo = (ObjectId) obj.get("idanolectivo");
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionpaquete");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            RelacionPaquete res = new RelacionPaquete();
            DBObject obj = cursor.next();
            res.idRelacionPaquete = (ObjectId) obj.get("_id");
           
            res.idNivel = (ObjectId) obj.get("idnivel");
            res.idAsignatura = (ObjectId) obj.get("idasignatura");
            res.idEvaluacion = (ObjectId) obj.get("idevaluacion");
            res.idAnoLectivo = (ObjectId) obj.get("idanolectivo");
           
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacionPaquete != null ? idRelacionPaquete.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionPaquete)) {
            return false;
        }
        RelacionPaquete other = (RelacionPaquete) object;
        if ((this.idRelacionPaquete == null && other.idRelacionPaquete != null) || (this.idRelacionPaquete != null && !this.idRelacionPaquete.equals(other.idRelacionPaquete))) {
            return false;
        }
        return true;
    }
}
