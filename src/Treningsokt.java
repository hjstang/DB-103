
public class Treningsokt {
	
	
	public int treningsID;
	public int dato;
	public int tidspunkt;
	public int varighet;
	public String infoOmOvelser;
	public int personligForm;
	public int prestasjon;
	
	public Treningsokt(int treningsID, int dato, int tidspunkt, int varighet, String infoOmOvelser, int personligForm,
			int prestasjon) {
		super();
		this.treningsID = treningsID;
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighet = varighet;
		this.infoOmOvelser = infoOmOvelser;
		this.personligForm = personligForm;
		this.prestasjon = prestasjon;
	}

	public int getTreningsID() {
		return treningsID;
	}

	public void setTreningsID(int treningsID) {
		this.treningsID = treningsID;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public int getTidspunkt() {
		return tidspunkt;
	}

	public void setTidspunkt(int tidspunkt) {
		this.tidspunkt = tidspunkt;
	}

	public int getVarighet() {
		return varighet;
	}

	public void setVarighet(int varighet) {
		this.varighet = varighet;
	}

	public String getInfoOmOvelser() {
		return infoOmOvelser;
	}

	public void setInfoOmOvelser(String infoOmOvelser) {
		this.infoOmOvelser = infoOmOvelser;
	}

	public int getPersonligForm() {
		return personligForm;
	}

	public void setPersonligForm(int personligForm) {
		this.personligForm = personligForm;
	}

	public int getPrestasjon() {
		return prestasjon;
	}

	public void setPrestasjon(int prestasjon) {
		this.prestasjon = prestasjon;
	}
	
	
	

}
