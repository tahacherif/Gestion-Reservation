package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reservation;

public class ReservationDAO {
	public static void CREATE(Reservation r) {
		try{

			String query = "INSERT INTO reservation (num_reservation,num_salle ,code_demandeur,num_plageHoraire) VALUES (?,?,?,?)";
			PreparedStatement Stmt = SConnection.getInstance().prepareStatement(query);
			Stmt.setString (1,r.getNum_reservation());
			Stmt.setInt (2,r.getNum_salle());
			Stmt.setInt(3,r.getCode_demandeur());
			Stmt.setInt(4,r.getNum_plageHoraire());


			Stmt.execute();

			SConnection.close();

			System.out.println("Reservation "+ r.getNum_reservation() + " est ajoute avec succees!" );




		}
		catch(SQLException e) {System.out.println(e.getMessage());}
	}

	/*public static Reservation findByNum(String numero) { //READ
		String rq="select * from reservation where num_reservation=?";
		Reservation r= null;
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setString(1, numero);
			ResultSet res= st.executeQuery();
			if(res.next())
				r=new Reservation(res.getInt(1), res.getInt(2),res.getInt(3),((LocalTime) res.getTime(4), (LocalTime) res.getTime(5));


			st.close();
		} catch (SQLException e) {
			System.out.println("La requete1 n'a pas pu etre executee ");
		}
		return r;
	}*/
	public static boolean 	annuler(String num) {
		if (num == null)
			return false;
		Connection cnx = SConnection.getInstance();
		int n = 0;
		try {
			PreparedStatement st = cnx.prepareStatement("delete from reservation where num_reservation= ?");
			st.setString(1, num);
			n = st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SConnection.close();
		return n > 0;
	}


	public static  void UPDATESalle(Reservation r, int num_salle) {
		int n=0;
		String rq1="update Reservation set num_salle= ? where num_reservation=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setInt(1,num_salle);
			st.setString(2,r.getNum_reservation());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du salle de la  Reservation avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du salle de la  Reservation a echoue ");
		}
	}
	public static  void UPDATEPlageHoraire(Reservation r, int num_plageHoraire) {
		int n=0;
		String rq1="update Reservation set num_plageHoraire= ? where num_reservation=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {

			st=cnx.prepareStatement(rq1);
			st.setInt(1,num_plageHoraire);
			st.setString(2,r.getNum_reservation());
			n= st.executeUpdate();
			st.close();
			if(n==1) {
				System.out.println("Mise a jour du PlageHoraire de la  Reservation avec succes ");
			}
		} catch (SQLException e) {
			System.out.println("Mise a jour du PlageHoraire de la  Reservation a echoue ");
		}
	}


	public static void DELETE(String numero) {
		int n=0;
		String rq="Delete from Reservation where num_reservation=?";
		Connection cnx= SConnection.getInstance();
		PreparedStatement st;
		try {
			st=cnx.prepareStatement(rq);
			st.setString(1, numero);
			n= st.executeUpdate();
			if(n==1)
				System.out.println("La Reservation a ete supprimer avec succes ");
			else 
				System.out.println("Aucune Reservation n'a ete archive  ");
			st.close();
		}catch(SQLException e) {
			System.out.println("La requete delete n'a pas pu etre executee");
		}
	}


}
