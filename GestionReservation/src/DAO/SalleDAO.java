package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Salle;

public class SalleDAO {

	public static void CREATE(Salle s) {
		try{
			if (findByNum(s.getNumero())==null) {



				String query = "INSERT INTO salle (numero,superfice ,nbreEquipement) VALUES (?,?,?)";
				PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
				Stmt.setInt (1,s.getNumero());
				Stmt.setDouble (2,s.getSuperfice());
				Stmt.setInt(3,s.getNbreEquipement());

				Stmt.execute();

				SConnection.close();

				System.out.println("Salle "+ s.getNumero() + " est ajoute avec succees!" );


			}

		}
		catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public static Salle  findByNum(int numero) { //READ
		String rq="select * from salle where numero=?";
		Salle s= null;
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, numero);
			ResultSet res= st.executeQuery();
			if(res.next())
				s=new Salle(res.getInt(1), res.getDouble(2),res.getInt(3));


			st.close();
		} catch (SQLException e) {
			System.out.println("La requete1 n'a pas pu etre executee ");
		}
		return s;
	}

	public static  void UPDATNbreEquipement(Salle s, int nbreEquipement) {
		int n=0;
		String rq1="update salle set nbreEquipement= ? where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setInt(1,nbreEquipement);
			st.setInt(2,s.getNumero());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du Salle avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du Salle a echoue ");
		}
		if(n==0)
		{
			CREATE(s);
		}
	}


	public static void DELETE(int numero) {
		int n=0;
		String rq="Delete from salle where numero=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setInt(1, numero);
			n= st.executeUpdate();
			if(n==1)
				System.out.println("La salle a ete supprimer avec succes ");
			else 
				System.out.println("Aucune salle n'a ete archive  ");
			st.close();
		}catch(SQLException e) {
			System.out.println("La requete delete n'a pas pu etre executee");
		}
	}
	public static boolean update(Salle s) {
		if (s == null)
			return false;
		Connection cnx = SConnection.getInstance();
		int n = 0;
		try {
			PreparedStatement st = cnx.prepareStatement("update salle set etat=? where numero= ?");
			st.setLong(2, s.getNumero());
			st.setInt(1, s.getEtat());
			n = st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SConnection.close();
		return n > 0;
	}
}
