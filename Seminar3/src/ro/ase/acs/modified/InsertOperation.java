package ro.ase.acs.modified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.acs.contacts.Operation;

public class InsertOperation implements Operation{

	@Override
	public void compute(Connection connection) throws SQLException {
		String sqlInsert = "INSERT INTO employees VALUES(1, 'Popescu Ion', 'Bucharest', 4000)";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlInsert);
		statement.close();
		
		String sqlInsertWithParams = "INSERT INTO employees VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = 
				connection.prepareStatement(sqlInsertWithParams);
		preparedStatement.setInt(1, 2);
		preparedStatement.setString(2, "Ionescu Vasile");
		preparedStatement.setString(3, "Brasov");
		preparedStatement.setDouble(4, 4500);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
		connection.commit();
		
	}

	@Override
	public void compute(MongoCollection<Document> collection, MongoDatabase mongoDb, MongoClient mongoClient) {
		Document employee1 = new Document().append("name", "Popescu Ion").
				append("address", "Bucharest").append("salary", 4000);
		
		//collection = mongoDb.getCollection("employees");
		collection.insertOne(employee1);
		
		Document employee2 = new Document().append("name", "Ionescu Vasile").
				append("salary", 4500);
		collection.insertOne(employee2);
		
	}

}
