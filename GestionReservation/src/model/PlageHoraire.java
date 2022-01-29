
package model;

import java.sql.Time;
import java.sql.Date;

public class PlageHoraire {

	private int numero;
	private Date date;
	private Time heureDebut;
	private Time heureFin;

	public PlageHoraire(int numero, Date date, Time heureDebut, Time heureFin) {
		super();
		this.numero = numero;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	public PlageHoraire() {
		super();
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Time getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}


	public void ReserverPlage() {

	}
	public void LibererPlage() {

	}
	public boolean EstLibre() {
		return true;		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlageHoraire other = (PlageHoraire) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (heureFin == null) {
			if (other.heureFin != null)
				return false;
		} else if (!heureFin.equals(other.heureFin))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlageHoraire [numero=" + numero + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin="
				+ heureFin + "]";
	}


}
