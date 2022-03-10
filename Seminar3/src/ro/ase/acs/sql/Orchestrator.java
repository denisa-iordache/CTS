package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import eu.ase.acs.contacts.Operation;

public class Orchestrator {
	private Operation operation;
	
	public Orchestrator(Operation operation) throws SQLException{
		this.operation = operation;
	}
	
	public void execute() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		connection.setAutoCommit(false);

		this.operation.compute(connection);
	}
}
