package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.SQLException;

import com.mongodb.MongoClient;

import ro.ase.acs.contacts.ConnectionState;

public class CloseConnectionState implements ConnectionState {

	@Override
	public void close(Connection connection) throws SQLException {
		connection.close();
		System.out.println("Conexiune inchisa!");

	}

	@Override
	public void close(MongoClient mongoClient) {
		mongoClient.close();
		System.out.println("Conexiune inchisa!");

	}

}
