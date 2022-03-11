package ro.ase.acs.contacts;

import java.sql.Connection;
import java.sql.SQLException;

import com.mongodb.MongoClient;

public interface ConnectionState {
	Connection setSql(Connection connection) throws SQLException, ClassNotFoundException;

	MongoClient setNoSql(MongoClient mongoClient);
}
