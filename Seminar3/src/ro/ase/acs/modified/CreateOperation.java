package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class CreateOperation implements Operation{

	@Override
	public void compute(Connection connection) throws SQLException {
		String sqlDrop = "DROP TABLE IF EXISTS employees";
		String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY,"
				+ "name TEXT, address TEXT, salary REAL)";
		
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlDrop);
		statement.executeUpdate(sqlCreate);
		statement.close();
		connection.commit();
	}
	
	@Override
	public void compute(MongoCollection<Document> collection, MongoDatabase mongoDb, MongoClient mongoClient) {
		if(mongoDb.getCollection("employees") != null) {
			mongoDb.getCollection("employees").drop();
		}
		
		mongoDb.createCollection("employees");
	}

}
