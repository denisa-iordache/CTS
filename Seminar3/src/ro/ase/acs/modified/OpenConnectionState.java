package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.ConnectionState;

public class OpenConnectionState implements ConnectionState {

	@Override
	public Connection setSql(Connection c) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		connection.setAutoCommit(false);
		System.out.println("Conexiune deschisa");
		return connection;
	}

	@Override
	public MongoClient setNoSql(MongoClient mc) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Conexiune deschisa");
		return mongoClient;
	}

}
