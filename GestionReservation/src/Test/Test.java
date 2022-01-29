package Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import DAO.DemandeurDAO;
import DAO.FactureDAO;
import DAO.PlageHoraireDAO;
import DAO.ReservationDAO;
import model.Demandeur;
import model.Facture;
import model.PlageHoraire;
import model.Reservation;
import model.Salle;
import DAO.SalleDAO;
public class Test {

	public static void main(String[] args) throws SQLException {



		Demandeur d1 = new Demandeur("Ahmad", "14ddFD", 4);
		DemandeurDAO.CREATE(d1);

		DemandeurDAO.UPDATEName(d1,"Fedi");


		Salle s1 = new Salle(2, 800, 5000);
		SalleDAO.CREATE(s1);
		System.out.println(SalleDAO.findByNum(s1.getNumero()));
		SalleDAO.UPDATNbreEquipement(s1,15);
		PlageHoraire pl = new PlageHoraire (551, new Date (13,02,2021), new Time(12,02, 0),new Time(13,02,0));
		PlageHoraireDAO.CREATE(pl);

		Reservation r1 = new Reservation(s1.getNumero(),d1.getCode(),pl.getNumero(), LocalTime.of(12,02), LocalTime.of(13,0));
		r1.confirmer();

	}

}
