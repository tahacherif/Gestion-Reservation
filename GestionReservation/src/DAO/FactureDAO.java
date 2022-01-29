package DAO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Facture;

public class FactureDAO {

	public static void CREATE(Facture f) {
		try{
			if(findByNum(f.getNumero())==null) {

			String query = "INSERT INTO Facture (numero,montant ,date,code_demendeur) VALUES (?,?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setString (1, f.getNumero());
			Stmt.setDouble (2,f.getMontant());
			Date d=Date.valueOf(f.getDate());

			Stmt.setDate(3,d);
			Stmt.setInt(4,f.getCode_demendeur());


			Stmt.execute();

			SConnection.close();

			System.out.println("Facture numero "+ f.getNumero() + " est ajoute avec succees!" );



			}
		}
		catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public static Facture findByNum(String id) { //READ
		String rq="select * from facture where numero=?";
		Facture f= null;
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setString(1, id);
			ResultSet res= st.executeQuery();
			if(res.next())
				f=new Facture(res.getString(1),res.getDouble(2),res.getDate(3).toLocalDate(),res.getInt(4));


			st.close();
		} catch (SQLException e) {
			System.out.println("La requete1 n'a pas pu etre executee ");
		}
		return f;
	}

	public static void UPDATEmontant(Facture f , double montant ) {
		int n=0;
		String rq1="update Facture set montant= ? where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setDouble(1,montant);
			st.setString(2,f.getNumero());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du Facture avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du Facture a echoue ");
		}
		if(n==0)
		{try {
			String query = "INSERT INTO Facture (numero,montant ,date,code_demendeur) VALUES (?,?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setString (1, f.getNumero());
			Stmt.setDouble (2,f.getMontant());
			Date d = Date.valueOf(f.getDate());
			Stmt.setDate(3,d);
			Stmt.setInt(4,f.getCode_demendeur());


			Stmt.execute();

			SConnection.close();

			System.out.println("ajout du Facture avec succes ");

		} catch (SQLException e) {
			System.out.println("L'ajout du Facture a echoue ");
		}
		}
	}

	public static  void DELETE(String num) {
		int n=0;
		String rq="Delete from Facture where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setString(1, num);
			n= st.executeUpdate();
			if(n==1)
				System.out.println("Le Facture a ete archive avec succes ");
			else 
				System.out.println("Aucun Facture n'a ete archive  ");
			st.close();
		}catch(SQLException e) {
			System.out.println("La requete delete n'a pas pu etre executee");
		}
	}

	public static List<Facture> findAll() {
		List<Facture> d = new ArrayList<Facture>();
		Connection cnx = SConnection.getInstance();

		try {
			PreparedStatement st = cnx.prepareStatement("select * from facture");
			ResultSet res = st.executeQuery();
			while(res.next())

			{
				Facture f = new Facture(res.getString(1),res.getDouble(2) ,res.getDate(3).toLocalDate(),res.getInt(4));
				d.add(f);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d;
	}

}
