package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.SQLException;

import eu.ase.acs.contacts.Operation;

public class CloseOperation implements Operation{

	@Override
	public void compute(Connection connection) throws SQLException {
		connection.close();
		
	}

}
