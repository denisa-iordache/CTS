package ro.ase.acs.modified;

import java.sql.SQLException;

import ro.ase.acs.contacts.ConnectionState;
import ro.ase.acs.contacts.Operation;
import ro.ase.acs.contacts.Reader;

public class Main {

	public static void main(String[] args) throws SQLException, Exception {
		Reader reader = (Reader) Class.forName("ro.ase.acs.modified.ConsoleReader").getDeclaredConstructor()
				.newInstance();

		Operation operation = (Operation) Class.forName("ro.ase.acs.modified.CreateOperation").getDeclaredConstructor()
				.newInstance();
//		Operation operation = (Operation) Class.forName("ro.ase.acs.modified.InsertOperation").getDeclaredConstructor()
//				.newInstance();
//		Operation operation = (Operation) Class.forName("ro.ase.acs.modified.ReadOperation").getDeclaredConstructor()
//				.newInstance();

		ConnectionState connectionState = (ConnectionState) Class.forName("ro.ase.acs.modified.CloseConnectionState")
				.getDeclaredConstructor().newInstance();

		Orchestrator orchestrator = new Orchestrator(operation, reader, connectionState);
		// orchestrator.executeNOSQL();
		orchestrator.executeSQL();
	}
}
