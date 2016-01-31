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
public class Nivel implements Serializable {

    ObjectId idNivel;
    ObjectId idSeccion;
    String nombre;
    String paralelo;

    String idNivelString;
    String nombreSeccion;

    public Nivel(ObjectId idSeccion, String nombre, String paralelo) {

        this.nombre = nombre;
        this.paralelo = paralelo;
    }

    public Nivel() {

        this.nombre = "";
        this.paralelo = "";
    }

    public ObjectId getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(ObjectId idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getIdNivelString() {
        return idNivelString;
    }

    public void setIdNivelString(String idNivelString) {
        this.idNivelString = idNivelString;
    }

    public ObjectId getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(ObjectId idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public void save() {
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("nivel");

        BasicDBObject obj = new BasicDBObject("idseccion", this.idSeccion).append("nombre", this.nombre).append("paralelo", this.paralelo);
        table.save(obj);
    }

    public void update() {

        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("nivel"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("idseccion", this.idSeccion).append("nombre", this.nombre).append("paralelo", this.paralelo));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idNivel);
        table.update(searchQuery, newDocument);

    }

    public static Nivel get(ObjectId id) {
        Nivel res = new Nivel();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("nivel");
        BasicDBObject query = new BasicDBObject("_id", id);

        BasicDBObject fields = new BasicDBObject();
        fields.put("_id", 1);
        fields.put("idseccion", 1);
        fields.put("nombre", 1);
        fields.put("paralelo", 1);

        Cursor cursor = table.find(query, fields);

        while (cursor.hasNext()) {

            DBObject obj = cursor.next();
            res.idNivel = (ObjectId) obj.get("_id");

            res.nombre = obj.get("nombre").toString();
            res.paralelo = obj.get("paralelo").toString();
            res.idSeccion = (ObjectId) obj.get("idseccion");

            DBCollection tableSec = mongo.db.getCollection("seccion");
            BasicDBObject querySec = new BasicDBObject("_id", res.idSeccion);

            BasicDBObject fieldsSec = new BasicDBObject();

            fieldsSec.put("nombre", 1);
            Cursor cursorSec = tableSec.find(querySec, fieldsSec);
            while (cursorSec.hasNext()) {

                DBObject objSec = cursorSec.next();
                res.nombreSeccion =  objSec.get("nombre").toString();
            }

        }
        cursor.close();

        return res;
    }

    public static List<Nivel> getAll() {
        List<Nivel> listatotal = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("nivel");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Nivel res = new Nivel();
            DBObject obj = cursor.next();
            res.idNivel = (ObjectId) obj.get("_id");
            res.idSeccion = (ObjectId) obj.get("idseccion");
            res.nombre = obj.get("nombre").toString();
            res.paralelo = obj.get("paralelo").toString();
            
            DBCollection tableSec = mongo.db.getCollection("seccion");
            BasicDBObject querySec = new BasicDBObject("_id", res.idSeccion);

            BasicDBObject fieldsSec = new BasicDBObject();

            fieldsSec.put("nombre", 1);
            Cursor cursorSec = tableSec.find(querySec, fieldsSec);
            while (cursorSec.hasNext()) {

                DBObject objSec = cursorSec.next();
                res.nombreSeccion =  objSec.get("nombre").toString();
            }

            listatotal.add(res);
        }
        cursor.close();
        return listatotal;
    }

    public static Map<String, String> getAllMap() {
        Map<String, String> objetos = new HashMap<>();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("nivel");

        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            Nivel res = new Nivel();
            DBObject obj = cursor.next();
            res.idNivel = (ObjectId) obj.get("_id");
            res.idSeccion = (ObjectId) obj.get("idseccion");
            res.nombre = obj.get("nombre").toString();
            res.paralelo = obj.get("paralelo").toString();
            
            DBCollection tableSec = mongo.db.getCollection("seccion");
            BasicDBObject querySec = new BasicDBObject("_id", res.idSeccion);

            BasicDBObject fieldsSec = new BasicDBObject();

            fieldsSec.put("nombre", 1);
            Cursor cursorSec = tableSec.find(querySec, fieldsSec);
            while (cursorSec.hasNext()) {

                DBObject objSec = cursorSec.next();
                res.nombreSeccion =  objSec.get("nombre").toString();
            }
        }
        cursor.close();
        return objetos;
    }

    //METHODS TO USE GenericConverter ON SELECT LIST
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }
}
