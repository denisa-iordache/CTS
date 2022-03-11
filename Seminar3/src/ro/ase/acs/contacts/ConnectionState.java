package ro.ase.acs.contacts;

import java.sql.Connection;
import java.sql.SQLException;

import com.mongodb.MongoClient;

public interface ConnectionState {
	void close(Connection connection) throws SQLException;

	void close(MongoClient mongoClient);
}
