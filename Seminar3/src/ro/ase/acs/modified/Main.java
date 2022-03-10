package ro.ase.acs.modified;

import java.sql.SQLException;

import ro.ase.acs.contacts.Operation;

public class Main {

	public static void main(String[] args) throws SQLException, Exception{
		Operation operation = (Operation) Class.forName("ro.ase.acs.modified.ReadOperation").getDeclaredConstructor()
				.newInstance();
		Orchestrator orchestrator = new Orchestrator(operation);
		orchestrator.executeNOSQL(); //executeSQL()
		//orchestrator.executeSQL();
	}
}
