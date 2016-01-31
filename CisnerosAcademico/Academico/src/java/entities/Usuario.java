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
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pabloromero
 */
public class Usuario implements Serializable {

    ObjectId idUsuario;
    ObjectId idDependencia;
    String nombre;
    String password;
    Integer tipo;
    //leyendas
    String leyendaTipo;
    String leyendaDependencia;
    
    String idDepenString;

    
    
    
    public Usuario(String nombre, String password, Integer tipo, String leyendaTipo) {

        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
        this.leyendaTipo = leyendaTipo;
       
    }

    public Usuario() {

        this.nombre = "";
        this.password = "";
        this.tipo = -1;
        this.leyendaTipo = "";
        this.leyendaDependencia = "";
        this.idDepenString = "";
    }

    public String getIdDepenString() {
        return idDepenString;
    }

    public void setIdDepenString(String idDepenString) {
        this.idDepenString = idDepenString;
    }

    public String getLeyendaDependencia() {
        return leyendaDependencia;
    }

    public void setLeyendaDependencia(String leyendaDependencia) {
        this.leyendaDependencia = leyendaDependencia;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLeyendaTipo() {
        return leyendaTipo;
    }

    public void setLeyendaTipo(String leyendaTipo) {
        this.leyendaTipo = leyendaTipo;
    }

    public void guardarUsuario() {
        MongoManager mongo = MongoManager.getInstance();

        
        
        
        DBCollection table = mongo.db.getCollection("users");

        BasicDBObject docSubObjetivo = new BasicDBObject("id_dependencia", this.idDependencia).append("user", this.nombre).
                append("password", this.password).append("tipo", this.tipo).append("leyenda", this.leyendaTipo);
        table.save(docSubObjetivo);
    }
    
    
    public void updateUsuario(){
        
        //obtener el usuario antes de modificar
        
        Usuario before=getUsuarioId(this.idUsuario);
        
        //actualizar usuario modificado
        this.idDependencia = new ObjectId(this.idDepenString);
        MongoManager mongo = MongoManager.getInstance(); //instacion BD Mongo
        DBCollection table = mongo.db.getCollection("users"); // select table        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("id_dependencia", this.idDependencia).append("user", this.nombre).append("password", this.password).append("tipo", this.tipo).append("leyenda", this.leyendaTipo));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", this.idUsuario);
        table.update(searchQuery, newDocument);
        
        
        
    }

    public static Usuario getUsuario(String userName) {
        Usuario user = new Usuario();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("users");
        BasicDBObject query = new BasicDBObject("user", userName);

        DBObject obj = table.findOne(query);
        if (obj != null) {
            user.idUsuario = (ObjectId) obj.get("_id");
            user.idDependencia = (ObjectId) obj.get("id_dependencia");
            user.nombre = obj.get("user").toString();
            user.password = obj.get("password").toString();
            user.tipo = Integer.parseInt(obj.get("tipo").toString());
            user.leyendaTipo = obj.get("leyenda").toString();
           
            
            
            DBCollection tableDep = mongo.db.getCollection("dependencia");
            BasicDBObject queryDep = new BasicDBObject("_id", user.idDependencia);

            BasicDBObject fields = new BasicDBObject();
            fields.put("nombre", 1);            
            Cursor cursorDep = tableDep.find(queryDep, fields);
            while (cursorDep.hasNext()) {
                DBObject objDep = cursorDep.next();               
                user.leyendaDependencia = objDep.get("nombre").toString();               
            }
            cursorDep.close();
            
        }
        return user;
    }

    public static Usuario getUsuarioId(ObjectId id) {
        Usuario user = new Usuario();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("users");
        BasicDBObject query = new BasicDBObject("_id", id);

        DBObject obj = table.findOne(query);
        if (obj != null) {
            user.idUsuario = (ObjectId) obj.get("_id");
            user.idDependencia = (ObjectId) obj.get("id_dependencia");
            user.nombre = obj.get("user").toString();
            user.password = obj.get("password").toString();
            user.tipo = Integer.parseInt(obj.get("tipo").toString());
            user.leyendaTipo = obj.get("leyenda").toString();
           
            
            DBCollection tableDep = mongo.db.getCollection("dependencia");
            BasicDBObject queryDep = new BasicDBObject("_id", user.idDependencia);

            BasicDBObject fields = new BasicDBObject();
            fields.put("nombre", 1);            
            Cursor cursorDep = tableDep.find(queryDep, fields);
            while (cursorDep.hasNext()) {
                DBObject objDep = cursorDep.next(); 
                user.idDepenString = objDep.get("_id").toString();
                user.leyendaDependencia = objDep.get("nombre").toString();               
            }
            cursorDep.close();
            
            
        }
        return user;
    }

    public static List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList();

        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("users");

        DBCursor cursor = table.find();

        while (cursor.hasNext()) {
            Usuario user = new Usuario();
            DBObject obj = cursor.next();
            user.idUsuario = (ObjectId) obj.get("_id");
            user.idDependencia = (ObjectId) obj.get("id_dependencia");
            user.nombre = obj.get("user").toString();
            user.password = obj.get("password").toString();
            user.tipo = Integer.parseInt(obj.get("tipo").toString());
            user.leyendaTipo = obj.get("leyenda").toString();
            user.idDepenString = obj.get("id_dependencia").toString();
          
            
            DBCollection tableDep = mongo.db.getCollection("dependencia");
            BasicDBObject queryDep = new BasicDBObject("_id", user.idDependencia);

            BasicDBObject fields = new BasicDBObject();
            fields.put("nombre", 1);            
            Cursor cursorDep = tableDep.find(queryDep, fields);
            while (cursorDep.hasNext()) {
                DBObject objDep = cursorDep.next();               
                user.leyendaDependencia = objDep.get("nombre").toString();               
            }
            cursorDep.close();
                                    
            usuarios.add(user);                                                
            
        }
        cursor.close();
        return usuarios;
    }
    
    //metodo para comprobar si el usuario pertenece a la dependencia que genera pagos finales de dinero
    public static Boolean chequearDependenciaPago(ObjectId id){
        Boolean res = false;
        ObjectId idDep = new ObjectId();
        MongoManager mongo = MongoManager.getInstance();

        DBCollection table = mongo.db.getCollection("users");
        BasicDBObject query = new BasicDBObject("_id", id);

        DBObject obj = table.findOne(query);
        if (obj != null) {
            
            idDep = (ObjectId) obj.get("id_dependencia");
            
            
            DBCollection tableDep = mongo.db.getCollection("dependencia");
            BasicDBObject queryDep = new BasicDBObject("_id", idDep);

            BasicDBObject fields = new BasicDBObject();
            fields.put("entidad_pago", 1);            
            Cursor cursorDep = tableDep.find(queryDep, fields);
            while (cursorDep.hasNext()) {
                DBObject objDep = cursorDep.next(); 
                if( (Integer)objDep.get("entidad_pago")==1){
                    res = true;
                }               
            }
            cursorDep.close();                        
        }
                
        return res;
    }
}
