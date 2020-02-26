package questao1;
import java.util.Date;

public class Memento { 
	private String state; 
	private Date lastUpdate; 
	public Memento(String stateToSave,Date lastUpdateToSave) { 
		state= stateToSave; 
		lastUpdate= lastUpdateToSave; 
	} 
	public String getSavedState() { 
		return state; 
	}
	public Date getSavedDate() { 
		return lastUpdate; 
	} 
}