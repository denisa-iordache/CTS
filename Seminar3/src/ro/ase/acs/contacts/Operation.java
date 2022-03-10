package ro.ase.acs.contacts;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface Operation {
	void compute(Connection connection) throws SQLException;
	void compute(MongoCollection<Document> collection, MongoDatabase mongoDb, MongoClient mongoClient);
}
