/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author pabloromero
 */
public class MongoManager {
    private static MongoManager mongoManager;
    MongoClient mongoClient;
    public DB db;
    public boolean couldConnect;
    
    private MongoManager()
    {
        try
        {
            mongoClient = new MongoClient("localhost" , 27017);
            db = mongoClient.getDB("academico");
            couldConnect = true;
        }catch(UnknownHostException ex)
        {
            couldConnect = false;
        }
    }
    
    public static MongoManager getInstance()
    {
        if(mongoManager==null)
        {
            mongoManager = new MongoManager();
        }
        
        return mongoManager;
    }
    
}
