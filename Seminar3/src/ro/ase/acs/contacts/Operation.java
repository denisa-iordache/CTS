package ro.ase.acs.contacts;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface Operation {
	void query(Connection conn, String string) throws SQLException;

	void query(MongoCollection<Document> collection, MongoDatabase mongoDb);
}
