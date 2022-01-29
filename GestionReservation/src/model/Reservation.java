package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import DAO.DemandeurDAO;
import DAO.FactureDAO;
import DAO.SalleDAO;
import DAO.PlageHoraireDAO;
import DAO.ReservationDAO;


public class Reservation {
	private String num_reservation;

	private int num_salle;

	private int code_demandeur;

	private int num_plageHoraire;

	private LocalTime td;
	private LocalTime tf;

	private static List<Reservation> lesReservation=new ArrayList<Reservation>();

	public Reservation() 
	{}

	public Reservation(int num_salle,int code_demandeur, int num_plageHoraire, LocalTime td, LocalTime tf) {
		this.num_reservation = genererNumero();
		this.num_salle = num_salle;
		this.code_demandeur = code_demandeur;
		this.num_plageHoraire = num_plageHoraire;
		this.tf= tf;
		this.td=td;
	}

	public static List<Reservation> getLesReservation() {
		return lesReservation;
	}
	public static void setLesReservation(List<Reservation> lesReservation) {
		Reservation.lesReservation = lesReservation;
	}
	public void setNum_reservation(String num_reservation) {
		this.num_reservation = num_reservation;
	}
	public String getNum_reservation() {
		return num_reservation;
	}


	public  Salle getSalle() {
		return SalleDAO.findByNum(num_salle);
	}

	public int getNum_salle() {
		return num_salle;
	}
	public void setNum_salle(int num_salle) {
		this.num_salle = num_salle;
	}





	public Demandeur getDemandeur() {
		return DemandeurDAO.findByCode(code_demandeur);
	}
	public int getCode_demandeur() {
		return code_demandeur;
	}

	public void setCode_demandeur(int code_demandeur) {
		this.code_demandeur = code_demandeur;
	}


	public PlageHoraire getPlageHoraire() {
		return PlageHoraireDAO.findByNum(num_plageHoraire);
	}
	public int getNum_plageHoraire() {
		return num_plageHoraire;
	}
	public void setNum_plageHoraire(int num_plageHoraire) {
		this.num_plageHoraire = num_plageHoraire;
	}







	public static String genererNumero()
	{
		String num=null;
		int nb=lesReservation.size();
		if (nb==0)
			nb=1;
		num="RES"+ nb;
		return num;
	}



	Salle s = getSalle();

	public static  double CalculPrixLocation( Salle s, LocalTime time1 ,LocalTime time2) {
		double montant=0;


		long diff = ChronoUnit.HOURS.between(time1,time2);
		if(diff<=3)
		{
			if(s.getSuperfice()<=500)

				montant=diff*200 + (s.getNbreEquipement()*5);
			else
				montant=diff*250 + (s.getNbreEquipement()*5);
		}
		else if(diff>3)

		{
			if(s.getSuperfice()<=500)
				montant=diff*180 + (s.getNbreEquipement()*5);

			else
				montant=diff*300 + (s.getNbreEquipement()*5);
		}

		return montant;
	}



	public boolean Annuler() {

		if(lesReservation.contains(this)) {
			lesReservation.remove(this);
			ReservationDAO.annuler(this.num_reservation);
			System.out.println("Reservation annul�e!");
			return true;
		}
		System.out.println("Reservation non trouve!");
		return false;
	}
	public boolean confirmer() {
		Reservation r1 = this;
		Reservation.lesReservation.add(r1);
		ReservationDAO.CREATE(r1);
		Salle s=SalleDAO.findByNum(num_salle);
		s.setEtat(1);
		SalleDAO.update(s);
		Facture f=new Facture(Facture.genererNumero(),CalculPrixLocation(s,td,tf) ,LocalDate.now(),code_demandeur);
		Facture.getLesFactures().add(f);
		FactureDAO.CREATE(f);
		return true;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code_demandeur;
		result = prime * result + num_plageHoraire;
		result = prime * result + ((num_reservation == null) ? 0 : num_reservation.hashCode());
		result = prime * result + num_salle;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		result = prime * result + ((td == null) ? 0 : td.hashCode());
		result = prime * result + ((tf == null) ? 0 : tf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (code_demandeur != other.code_demandeur)
			return false;
		if (num_plageHoraire != other.num_plageHoraire)
			return false;
		if (num_reservation == null) {
			if (other.num_reservation != null)
				return false;
		} else if (!num_reservation.equals(other.num_reservation))
			return false;
		if (num_salle != other.num_salle)
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		if (td == null) {
			if (other.td != null)
				return false;
		} else if (!td.equals(other.td))
			return false;
		if (tf == null) {
			if (other.tf != null)
				return false;
		} else if (!tf.equals(other.tf))
			return false;
		return true;
	}

}

