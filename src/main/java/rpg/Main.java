package rpg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DatabaseConnection;

public class Main {
	public static void main(String[] args) throws SQLException {
		Connection connection = DatabaseConnection.conectar();
	}
}