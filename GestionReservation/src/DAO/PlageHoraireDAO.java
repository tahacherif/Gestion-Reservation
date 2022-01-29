package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PlageHoraire;

public class PlageHoraireDAO {


	public static void CREATE(PlageHoraire pl) {
		try{
			if(findByNum(pl.getNumero())==null) {

			String query = "INSERT INTO PlageHoraire (numero,date ,heureDebut,heureFin) VALUES (?,?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setInt (1, pl.getNumero());
			Stmt.setDate (2,pl.getDate());
			Stmt.setTime(3,pl.getHeureDebut());
			Stmt.setTime(4,pl.getHeureFin());


			Stmt.execute();

			SConnection.close();

			System.out.println("PlageHoraire numero "+ pl.getNumero() + " est ajoute avec succees!" );

			}
		}
		catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public static PlageHoraire findByNum(int numero ) { //READ
		String rq="select * from PlageHoraire where numero=?";
		PlageHoraire pl= null;
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, numero);
			ResultSet res= st.executeQuery();
			if(res.next())
				pl=new PlageHoraire(res.getInt(1),res.getDate(2),res.getTime(3), res.getTime(4));


			st.close();
		} catch (SQLException e) {
			System.out.println("La requete1 n'a pas pu etre executee ");
		}
		return pl;
	}


	public static void UPDATEDate(PlageHoraire pl , Date date ) {
		int n=0;
		String rq1="update PlageHoraire set date= ? where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setDate(1,date);
			st.setInt(2,pl.getNumero());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du PlageHoraire avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du PlageHoraire a echoue ");
		}
		if(n==0)
		{try {
			String query = "INSERT INTO PlageHoraire (numero,date ,heureDebut,heureFin) VALUES (?,?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setInt (1, pl.getNumero());
			Stmt.setDate (2,pl.getDate());
			Stmt.setTime(3,pl.getHeureDebut());
			Stmt.setTime(4,pl.getHeureFin());


			Stmt.execute();

			SConnection.close();

			System.out.println("PlageHoraire numero "+ pl.getNumero() + " est ajoute avec succees!" );



		} catch (SQLException e) {
			System.out.println("L'ajout du PlageHoraire a echoue ");
		}
		}
	}

	public static  void DELETE(int numero) {
		int n=0;
		String rq="Delete from PlageHoraire where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, numero);
			n= st.executeUpdate();
			if(n==1)
				System.out.println("Le PlageHoraire a ete archive avec succes ");
			else 
				System.out.println("Aucun PlageHoraire n'a ete archive  ");
			st.close();
		}catch(SQLException e) {
			System.out.println("La requete delete n'a pas pu etre executee");
		}
	}


}
