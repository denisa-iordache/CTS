package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ro.ase.acs.sql.Orchestrator;
import eu.ase.acs.contacts.Operation;

public class Main {

	public static void main(String[] args) throws SQLException, Exception{
		Operation operation = (Operation) Class.forName("ro.ase.acs.sql.ReadOperation").getDeclaredConstructor()
				.newInstance();
		Orchestrator orchestrator = new Orchestrator(operation);
		orchestrator.execute();
	}
}
