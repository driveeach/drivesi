// by rafaelcavazin@gmail.com

package questao1;
import java.util.Date;

public class MementoExample{ 
	public static void main(String[] args) throws InterruptedException { 
		Caretaker caretaker= new Caretaker(); 
		Originator originator= new Originator(); 
		originator.set("State1",new Date()); 
		originator.set("State2",new Date()); 
		caretaker.addMemento(originator.saveToMemento()); 
		originator.set("State3", new Date()); 
		caretaker.addMemento(originator.saveToMemento()); 
		originator.set("State4", new Date()); 
		originator.restoreFromMemento(caretaker.getMemento(1)); 
	} 
}