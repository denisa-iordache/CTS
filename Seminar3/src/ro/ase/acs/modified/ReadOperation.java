package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class ReadOperation implements Operation {

	@Override
	public void query(Connection connection, String string) throws SQLException {
		String[] create = string.split(",");

		System.out.println("Table data:");

		String sqlSelect = "SELECT * FROM " + create[0];
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlSelect);
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			int id = rs.getInt("id");
			System.out.println("id: " + id);
			String name = rs.getString(2);
			System.out.println(rsmd.getColumnName(2) + ": " + name);
			String address = rs.getString(3);
			System.out.println(rsmd.getColumnName(3) + ": " + address);
			double salary = rs.getDouble(4);
			System.out.println(rsmd.getColumnName(4) + ": " + salary);
		}
		rs.close();
		statement.close();

	}

	@Override
	public void query(MongoCollection<Document> collection, MongoDatabase mongoDb) {
		System.out.println("Table data:");

		FindIterable<Document> result = collection.find();
		for (Document doc : result) {
			System.out.println(doc);
		}

	}

}
