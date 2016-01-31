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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author MINEDUC
 */
public class RelacionMatricula implements Serializable{
    ObjectId idRelacionMatricula;
    ObjectId idEstudiante;
   
    ObjectId idPaquete;
    BigDecimal calificacion;

    public RelacionMatricula(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public RelacionMatricula() {
        this.calificacion = new BigDecimal(0);
    }

    public ObjectId getIdRelacionMatricula() {
        return idRelacionMatricula;
    }

    public void setIdRelacionMatricula(ObjectId idRelacionMatricula) {
        this.idRelacionMatricula = idRelacionMatricula;
    }

    public ObjectId getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(ObjectId idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

   

    public ObjectId getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(ObjectId idPaquete) {
        this.idPaquete = idPaquete;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }
    
    public void save(){
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionmatricula");

        BasicDBObject obj = new BasicDBObject("idestudiante", this.idEstudiante).append("idpaquete", this.idPaquete).append("calificacion", this.calificacion);
        table.save(obj);
    }
    
    public void update(){
        
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("relacionmatricula"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("idestudiante", this.idEstudiante).append("idpaquete", this.idPaquete).append("calificacion", this.calificacion));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idRelacionMatricula);
        table.update(searchQuery, newDocument);

    }
    
    
    public static RelacionMatricula get(ObjectId id){
        RelacionMatricula res = new RelacionMatricula();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionmatricula");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id",1);
        fields.put("idestudiante", 1);
       
        fields.put("idpaquete", 1);
        fields.put("calificacion", 1);
       


        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idRelacionMatricula = (ObjectId) obj.get("_id");
            res.idEstudiante = (ObjectId) obj.get("idestudiante");
           
            res.idPaquete = (ObjectId) obj.get("idpaquete");
            res.calificacion = new BigDecimal(obj.get("calificacion").toString());
          
            
        }
        cursor.close();

        return res;
    }

    public static List<RelacionMatricula> getAll() {
        List<RelacionMatricula> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionmatricula");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            RelacionMatricula res = new RelacionMatricula();
            DBObject obj = cursor.next();
            res.idRelacionMatricula = (ObjectId) obj.get("_id");
            res.idEstudiante = (ObjectId) obj.get("idestudiante");
           
            res.idPaquete = (ObjectId) obj.get("idpaquete");
            res.calificacion = new BigDecimal(obj.get("calificacion").toString());
           
            
            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    
    public static Map<String,String> getAllMap() {
        Map<String,String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("relacionmatricula");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            RelacionMatricula res = new RelacionMatricula();
            DBObject obj = cursor.next();
            res.idRelacionMatricula = (ObjectId) obj.get("_id");
            res.idEstudiante = (ObjectId) obj.get("idestudiante");
           
            res.idPaquete = (ObjectId) obj.get("idpaquete");
            res.calificacion = new BigDecimal(obj.get("calificacion").toString());             
        }
        cursor.close();
        return objetos;
    }
    
    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacionMatricula != null ? idRelacionMatricula.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionMatricula)) {
            return false;
        }
        RelacionMatricula other = (RelacionMatricula) object;
        if ((this.idRelacionMatricula == null && other.idRelacionMatricula != null) || (this.idRelacionMatricula != null && !this.idRelacionMatricula.equals(other.idRelacionMatricula))) {
            return false;
        }
        return true;
    }
}
