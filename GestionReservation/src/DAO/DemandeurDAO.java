package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;
public class DemandeurDAO {

	public static void CREATE(Demandeur d) {
		try{

			if(findByCode(d.getCode())==null) {
			String query = "INSERT INTO Demandeur (nom,adresse ,code) VALUES (?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setString (1, d.getNom());
			Stmt.setString (2,d.getAdresse());
			Stmt.setInt(3,d.getCode());

			Stmt.execute();

			SConnection.close();

			System.out.println("Demandeur "+ d.getNom() + " est ajoute avec succees!" );

			}


		}
		catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public static Demandeur findByCode(int code) { //READ
		String rq="select * from Demandeur where code=?";
		Demandeur d= null;
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, code);
			ResultSet res= st.executeQuery();
			if(res.next())
				d=new Demandeur(res.getString(1), res.getString(2),res.getInt(3));


			st.close();
		} catch (SQLException e) {
			System.out.println("La requete1 n'a pas pu etre executee ");
		}
		return d;
	}

	public static  void UPDATEName(Demandeur d, String name) {
		int n=0;
		String rq1="update Demandeur set nom= ? where code=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setString(1,name);
			st.setInt(2,d.getCode());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du Demandeur avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du Demandeur a echoue ");
		}
		if(n==0)
		{try {
			String rq2="insert into Demandeur(nom,adresse,code) values (?,?,?)";
			st=cnx.prepareStatement(rq2);			
			st.setString(1,d.getNom());
			st.setString(2, d.getAdresse() );
			st.setInt(3, d.getCode() );	

			n= st.executeUpdate();
			System.out.println("Ajout du Demandeur avec succes ");
			st.close();
		} catch (SQLException e) {
			System.out.println("L'ajout du Demandeur a echoue ");
		}
		}
	}

	public static void DELETE(int c) {
		int n=0;
		String rq="Delete from Demandeur where code=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, c);
			n= st.executeUpdate();
			if(n==1)
				System.out.println("Le Demandeur a ete supprimer avec succes ");
			else 
				System.out.println("Aucun Demandeur n'a ete archive  ");
			st.close();
		}catch(SQLException e) {
			System.out.println("La requete delete n'a pas pu etre executee");
		}
	}

}
