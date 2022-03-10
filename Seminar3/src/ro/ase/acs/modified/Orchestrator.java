package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class Orchestrator {
	private Operation operation;
	
	public Orchestrator(Operation operation) throws SQLException{
		this.operation = operation;
	}
	
	public void executeSQL() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		connection.setAutoCommit(false);

		this.operation.compute(connection);
	}
	
	public void executeNOSQL() throws ClassNotFoundException, SQLException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = mongoDb.getCollection("employees");
		this.operation.compute(collection, mongoDb, mongoClient);
	}
}
