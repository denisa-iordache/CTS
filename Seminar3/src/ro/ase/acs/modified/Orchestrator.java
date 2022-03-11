package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.ConnectionState;
import ro.ase.acs.contacts.Operation;
import ro.ase.acs.contacts.Reader;

public class Orchestrator {
	private Operation operation;
	private Reader reader;
	private ConnectionState connectionState;

	public Orchestrator(Operation operation, Reader reader, ConnectionState connectionState) throws SQLException {
		this.operation = operation;
		this.reader = reader;
		this.connectionState = connectionState;
	}

	public void executeSQL() throws ClassNotFoundException, SQLException {
		System.out.print(
				"Sintaxa create: numeTabela, camp1, camp2, camp3 \n(!!!Sunt acceptate doar 3 campuri cu urmatoarele tipuri de date: camp1 = TEXT, camp2 = TEXT, camp3 = REAL!!!) \nSintaxa insert: numeTabela, id, camp1, camp2, camp3 \nSintaxa read: numeTabela");
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		connection.setAutoCommit(false);

		String string = reader.read();

		this.operation.query(connection, string);

		this.connectionState.close(connection);
	}

	public void executeNOSQL() throws ClassNotFoundException, SQLException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("test");

		MongoCollection<Document> collection = mongoDb.getCollection("employees");

		this.operation.query(collection, mongoDb);
		this.connectionState.close(mongoClient);
	}
}
