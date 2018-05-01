package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pessoa;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.sun.javafx.collections.MappingChange.Map;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;


public class PessoaDao {

    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList();
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
        db = mongoClient.getDB("controlemongodb");
            DBCollection colecao;
            colecao = db.getCollection("pessoas");
            DBCursor cursor = colecao.find();
            while (cursor.hasNext()) {
                Pessoa cliente = new Pessoa();
                BasicDBObject clienteDB = (BasicDBObject) cursor.next();
                cliente.setIdPessoa(clienteDB.getInt("_id"));
                cliente.setNome(clienteDB.getString("nome"));
                cliente.setEndereco(clienteDB.getString("endereco"));
                cliente.setEmail(clienteDB.getString("email"));
                pessoas.add(cliente);
            }
            mongoClient.close();
            return pessoas;
        

    }

    public Integer retornaIdPessoa() {
        Integer idPessoa = 0;
        Integer atual = 0;
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
        db = mongoClient.getDB("controlemongodb");
            DBCollection colecao;
            colecao = db.getCollection("contPessoa");
            DBCursor cursor = colecao.find();
            while (cursor.hasNext()) {
                BasicDBObject contadorDb = (BasicDBObject) cursor.next();
                atual = contadorDb.getInt("cont");
            }
            idPessoa = atual + 1;

            BasicDBObject document = new BasicDBObject();
            document.put("cont", idPessoa);
            BasicDBObject searchQuery = new BasicDBObject().append("cont", atual);
            colecao.update(searchQuery, document);
            mongoClient.close();
            return idPessoa;
       

    }

    public void gravar(Pessoa obj) {
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
            db = mongoClient.getDB("controlemongodb");
            DBCollection colecao;
            colecao = db.getCollection("pessoas");
            BasicDBObject document = new BasicDBObject();
            document.put("_id", retornaIdPessoa());
            document.put("nome", obj.getNome());
            document.put("email", obj.getEmail());
            document.put("endereco", obj.getEndereco());
            colecao.insert(document);
            mongoClient.close();
        

        
    }

    public Pessoa consultar(Integer codigo) {
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
        db = mongoClient.getDB("controlemongodb");
        DBCollection colecao;
            colecao = db.getCollection("pessoas");
            Pessoa cliente = new Pessoa();
            DBCursor cursor = colecao.find();
            while (cursor.hasNext()) {
                BasicDBObject clienteDb = (BasicDBObject) cursor.next();
                if (clienteDb.getInt("_id") == codigo) {
                    cliente.setIdPessoa(clienteDb.getInt("_id"));
                    cliente.setNome(clienteDb.getString("nome"));
                    cliente.setEndereco(clienteDb.getString("endereco"));
                    cliente.setEmail(clienteDb.getString("email"));
                }
            }
            mongoClient.close();
            return cliente;
        

    }

    public boolean alterar(Pessoa obj) {
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
        db = mongoClient.getDB("controlemongodb");
            DBCollection colecao;
            colecao = db.getCollection("pessoas");
            BasicDBObject document = new BasicDBObject();
            document.put("nome", obj.getNome());
            document.put("endereco", obj.getEndereco());
            document.put("email", obj.getEmail());
            BasicDBObject searchQuery = new BasicDBObject().append("_id", obj.getIdPessoa());
            colecao.update(searchQuery, document);
            mongoClient.close();
            return true;
       

    }

    public boolean excluir(Pessoa obj) {
        DB db;
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://projetomongodb23:OoQ22YaPGsyxCQGYbw08OxojGQLuvL11PUYw3cjwvUfwEMODxchNlDQOuENGI9uiYi3cUv7T20lrBmhH48vWeQ==@projetomongodb23.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
        
        db = mongoClient.getDB("controlemongodb");
            DBCollection colecao;
            colecao = db.getCollection("pessoas");
            BasicDBObject document = new BasicDBObject();
            document.put("_id", obj.getIdPessoa());
            colecao.remove(document);
            mongoClient.close();
            return true;
       
    }
}
