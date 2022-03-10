package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class ReadOperation implements Operation{

	@Override
	public void compute(Connection connection) throws SQLException {
		String sqlSelect = "SELECT * FROM employees";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlSelect);
		while(rs.next()) {
			int id = rs.getInt("id");
			System.out.println("id: " + id);
			String name = rs.getString(2);
			System.out.println("name: " + name);
			String address = rs.getString("address");
			System.out.println("address: " + address);
			double salary = rs.getDouble("salary");
			System.out.println("salary: " + salary);
		}
		rs.close();
		statement.close();
		
	}

	@Override
	public void compute(MongoCollection<Document> collection, MongoDatabase mongoDb, MongoClient mongoClient) {
		FindIterable<Document> result = collection.find();
		for(Document doc : result) {
			System.out.println(doc);
		}
		
	}

}
