package model;

public class Salle {
	private int numero;
	private double superfice;
	private int nbreEquipement;
	private int etat = 0;

	public Salle(int numero, double superfice, int nbreEquipement) {
		super();
		this.numero = numero;
		this.superfice = superfice;
		this.nbreEquipement = nbreEquipement;
	}

	public Salle() {
		super();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSuperfice() {
		return superfice;
	}

	public void setSuperfice(double superfice) {
		this.superfice = superfice;
	}

	public int getNbreEquipement() {
		return nbreEquipement;
	}

	public void setNbreEquipement(int nbreEquipement) {
		this.nbreEquipement = nbreEquipement;
	}

	@Override
	public String toString() {
		return "Salle [numero=" + numero + ", superfice=" + superfice + ", nbreEquipement=" + nbreEquipement + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (nbreEquipement != other.nbreEquipement)
			return false;
		if (numero != other.numero)
			return false;
		if (Double.doubleToLongBits(superfice) != Double.doubleToLongBits(other.superfice))
			return false;
		return true;
	}

	public void setEtat(int i) {
		etat = i;
	}

	public int getEtat() {
		return etat;
	}



}
