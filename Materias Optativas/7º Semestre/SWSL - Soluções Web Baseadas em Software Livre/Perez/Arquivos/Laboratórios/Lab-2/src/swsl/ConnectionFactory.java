package swsl;
import java.sql.*;

public class ConnectionFactory {

	public Connection getConnection() {

		String url = "jdbc:postgresql://143.107.58.177:5432/dbgrupo05";
		String usr = "grupo05";
		String senha = "c93z4";
		
		//banco local de teste
		/*
		String urlLocal = "jdbc:postgresql://localhost:5432/postgres";
		String usrLocal = "postgres";
		String senhaLocal = "1234";
		*/
		
		

		try {

			return DriverManager.getConnection(url, usr, senha);
			//return DriverManager.getConnection(urlLocal, usrLocal, senhaLocal);

		} catch (SQLException e) {
			
			throw new RuntimeException(e);

		}
	}
}
