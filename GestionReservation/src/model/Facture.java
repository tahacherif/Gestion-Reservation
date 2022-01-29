package model;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

import DAO.FactureDAO;

public class Facture implements Comparable<Facture> {
	private static SortedSet<Facture> lesFactures=new TreeSet<Facture>();

	public Facture(String numero, double montant, LocalDate date, int code_demendeur) {
		super();
		this.numero = numero;
		this.montant = montant;
		this.date = date;
		this.code_demendeur = code_demendeur;
		Facture.lesFactures.add(this);

	}
	private String numero;
	private double montant;
	private LocalDate date;
	
	
	public int getCode_demendeur() {
		return code_demendeur;
	}
	public void setCode_demendeur(int code_demendeur) {
		this.code_demendeur = code_demendeur;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	private int code_demendeur;


	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontat(double montat) {
		this.montant = montat;
	}
	
	@Override
	public String toString() {
		return "Facture [numero=" + numero + ", montat=" + montant + ", date=" + date + "]";
	}
	public Facture() {
		super();
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public static String genererNumero()
	{
		String num=null;
		LocalDate d=LocalDate.now();
		int nb=1;
		for(Facture f : FactureDAO.findAll())
			if(f.date.equals(d))
				nb+=1;
		if(nb<10)
			num="F"+d.getYear()+d.getMonthValue()+d.getDayOfMonth()+"00"+nb;
		else if(nb>9 && nb<100)
			num="F"+d.getYear()+d.getMonthValue()+d.getDayOfMonth()+"0"+nb;
		else
			num="F"+d.getYear()+d.getMonthValue()+d.getDayOfMonth()+nb;
		return num;
	}
	public static SortedSet<Facture> getLesFactures() {
		return lesFactures;
	}
	public static void setLesFactures(SortedSet<Facture> lesFactures) {
		Facture.lesFactures = lesFactures;
	}
	public void Emmetre() {
	}
	public void Imprimer() {

	}
	@Override
	public int compareTo(Facture o) {
		return o.getDate().compareTo(this.date);
	}
}