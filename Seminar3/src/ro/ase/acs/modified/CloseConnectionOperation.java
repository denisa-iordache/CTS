package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class CloseConnectionOperation implements Operation{

	@Override
	public void compute(Connection connection) throws SQLException {
		connection.close();
		
	}

	@Override
	public void compute(MongoCollection<Document> collection, MongoDatabase mongoDb, MongoClient mongoClient) {
		mongoClient.close();
		
	}

}
