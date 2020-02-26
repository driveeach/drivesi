package questao1;
import java.util.Date;


public class Originator { 
	private String state; 
	private Date lastUpdate;


	/* lotsofmemory usingprivatedata thatdoes nothaveto be 
saved. Insteadweuse a small memento object. */ 
	public void set(String state, Date lastUpdate) throws InterruptedException { 
		System.out.println("Originator: Setting state to " + state); 
		System.out.println("Originator: Setting Update to " + lastUpdate.toString()); 
		this.state= state; 
		this.lastUpdate= lastUpdate;
		Thread.currentThread().sleep(3000);
	} 
	public Object saveToMemento() { 
		System.out.println("Originator: Saving to Memento."); 
		return new Memento(state,lastUpdate); 
	} 
	public void restoreFromMemento(Object m) { 
		if(m instanceof Memento) { 
			Memento memento= (Memento) m; 
			state= memento.getSavedState();
			lastUpdate = memento.getSavedDate();
			System.out.println("Originator: State after restoring from Memento: " + state); 
			System.out.println("lastUpdate " + lastUpdate.toString()); 
		} 
	} 

}