package eu.ase.acs.contacts;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation {
	void compute(Connection connection) throws SQLException;
}
