package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class InsertOperation implements Operation {

	@Override
	public void query(Connection connection, String string) throws SQLException {
		String[] create = string.split(",");

		String sqlInsertWithParams = "INSERT INTO " + create[0] + " VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertWithParams);
		preparedStatement.setInt(1, Integer.parseInt(create[1]));
		preparedStatement.setString(2, create[2]);
		preparedStatement.setString(3, create[3]);
		preparedStatement.setDouble(4, Double.parseDouble(create[4]));
		preparedStatement.executeUpdate();
		preparedStatement.close();

		connection.commit();

		System.out.println("Rows inserted!");

	}

	@Override
	public void query(MongoCollection<Document> collection, MongoDatabase mongoDb) {
		Document employee1 = new Document().append("name", "Popescu Ion").append("address", "Bucharest")
				.append("salary", 4000);

		collection.insertOne(employee1);

		Document employee2 = new Document().append("name", "Ionescu Vasile").append("salary", 4500);

		collection.insertOne(employee2);

		System.out.println("Rows inserted!");

	}

}
