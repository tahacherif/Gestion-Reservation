package DAO;
import java.sql.*;

public class SConnection {
	private static Connection cnx;
	private static String url = "jdbc:mysql://localhost:3306/db_reservation";
	private static String user ="root";
	private static String motPasse ="";

	public static Connection getInstance()
	{   
		try {
			if(cnx==null || cnx.isClosed()) {
				//V�rifier le chargement en m�moire du pilote JDBC
				Class.forName("com.mysql.jdbc.Driver");
				//Etablir la connexion

				cnx=DriverManager.getConnection(url,user,motPasse);
				System.out.println("Connexion � la base de donn�es ");
			}
		}
		catch (ClassNotFoundException e) { 
			System.out.println("Classe Driver introuvable");
		}
		catch (SQLException e) {
			System.out.println("L'�tablissement de la connexion a �chou�.");
		}
		return cnx;
	}

	static void close()  {
		try {
			if(cnx!=null && !cnx.isClosed())
				cnx.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
