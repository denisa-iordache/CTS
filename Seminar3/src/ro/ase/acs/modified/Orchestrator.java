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
	private ConnectionState connectionOpen;

	public Orchestrator(Operation operation, Reader reader, ConnectionState connectionState, ConnectionState connectionOpen) throws SQLException {
		this.operation = operation;
		this.reader = reader;
		this.connectionState = connectionState;
		this.connectionOpen=connectionOpen;
	}

	public void executeSQL() throws ClassNotFoundException, SQLException {
		System.out.print(
				"Sintaxa create: numeTabela, camp1, camp2, camp3 \n(!!!Sunt acceptate doar 3 campuri cu urmatoarele tipuri de date: camp1 = TEXT, camp2 = TEXT, camp3 = REAL!!!) \nSintaxa insert: numeTabela, id, camp1, camp2, camp3 \nSintaxa read: numeTabela\n");
		
		Connection c = this.connectionOpen.setSql(null);

		String string = reader.read();

		this.operation.query(c, string);

		this.connectionState.setSql(c);
	}

	public void executeNOSQL() throws ClassNotFoundException, SQLException {
		
		MongoClient mc = this.connectionOpen.setNoSql(null);
		
		MongoDatabase mongoDb = mc.getDatabase("test");
		MongoCollection<Document> collection = mongoDb.getCollection("employees");

		this.operation.query(collection, mongoDb);
		this.connectionState.setNoSql(mc);
	}
}
