import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Ovelsesgruppe {
	
	private String navn;
	private List<Ovelse> ovelser;
	
	public Ovelsesgruppe(String navn, Collection<Ovelse> ovelser) {
		this.navn = navn;
		this.ovelser = new ArrayList<Ovelse>(ovelser);
	}
	
	public List<Ovelse> getOvelser() {
		return ovelser;
	}
	
}

