package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class CreateOperation implements Operation {

	@Override
	public void query(Connection connection, String string) throws SQLException {
		String[] create = string.split(",");

		String sqlDrop = "DROP TABLE IF EXISTS " + create[0];
		String sqlCreate = "CREATE TABLE " + create[0] + "(id INTEGER PRIMARY KEY," + create[1] + " TEXT, " + create[2]
				+ " TEXT, " + create[3] + " REAL)";

		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlDrop);
		statement.executeUpdate(sqlCreate);
		statement.close();
		connection.commit();

		System.out.println("Table created!");
	}

	@Override
	public void query(MongoCollection<Document> collection, MongoDatabase mongoDb) {
		if (mongoDb.getCollection("employees") != null) {
			mongoDb.getCollection("employees").drop();
		}

		mongoDb.createCollection("employees");

		System.out.println("Table created!");
	}

}
