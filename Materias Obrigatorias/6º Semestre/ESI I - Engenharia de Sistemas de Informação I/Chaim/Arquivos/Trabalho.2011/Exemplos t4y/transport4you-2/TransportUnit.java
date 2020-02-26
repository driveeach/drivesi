/**
 * 
 */
package dsd.transport4you.unit.simulator;

import java.util.Observable;

import dsd.transport4you.unit.events.IDoorsClosedEvent;
import dsd.transport4you.unit.events.IDoorsOpenEvent;
import dsd.transport4you.unit.events.IUnitDirectionChangedToBackwardEvent;
import dsd.transport4you.unit.events.IUnitDirectionChangedToForwardEvent;
import dsd.transport4you.unit.settings.Constants;

/**
 * Represents transport unit. Throws events when doors closes and opens.
 * 
 * @author Dino
 *
 */
public class TransportUnit extends Observable{

	//DOORS_OPEN, DOORS_CLOSED
	public String doorsState;
	private String currentDirection;

	/**
	 * Constructor. Sets default doors state to closed.
	 * @param transportUnitId 
	 * @param  
	 */
	public TransportUnit(){
		this.doorsState = Constants.DOORS_CLOSED;
		this.currentDirection = Constants.DIRECTION_FORWARD;
	}

	public String getCurrentDirection() {
		return currentDirection;
	}
	
	/**
	 * Sets current direction to forward or backward
	 * @param direction Direction of transport unit
	 */
	private void setCurrentDirection(String direction) {
		this.currentDirection = direction;

		setChanged();

		if (isDirectionForward()) {
			notifyObservers(new IUnitDirectionChangedToForwardEvent());
		}else if (isDirectionBackward()) {
			notifyObservers(new IUnitDirectionChangedToBackwardEvent());
		}	
	}

	/**
	 * Changes the direction of transport unit to forward
	 */
	public void changeDirectionToForward(){
		if (isDirectionBackward()) {
			setCurrentDirection(Constants.DIRECTION_FORWARD);
		}
	}

	/**
	 * Changes the direction of transport unit to backward
	 */
	public void changeDirectionToBackward(){
		if (isDirectionForward()) {
			setCurrentDirection(Constants.DIRECTION_BACKWARD);
		}
	}

	/**
	 * Checks if current direction is set to backward
	 * @return true if current direction is backward
	 */
	private boolean isDirectionBackward() {
		if (currentDirection.equals(Constants.DIRECTION_BACKWARD)) {
			return true;
		}else{
			return false;
		}	
	}

	/**
	 * Checks if current direction is set to forward
	 * @return true if current direction is forward
	 */
	private boolean isDirectionForward() {
		if (currentDirection.equals(Constants.DIRECTION_FORWARD)) {
			return true;
		}else{
			return false;
		}	
	}

	/**
	 * Opens the door of this transport unit
	 */
	public void openDoor(){
		if(isDoorsClosed()){
			setDoorsState(Constants.DOORS_OPEN);
		}
	}

	/**
	 * Closes the door of this transport unit
	 */
	public void closeDoor(){
		if(isDoorsOpened()){
			setDoorsState(Constants.DOORS_CLOSED);
		}
	}

	public String getDoorsState() {
		return doorsState;
	}

	/**
	 * Sets the state of door: <i>opened</> or <i>closed</>
	 * @param currentState Doors state
	 */
	private void setDoorsState(String currentState) {
		this.doorsState = currentState;

		setChanged();

		if (isDoorsClosed()) {
			notifyObservers(new IDoorsClosedEvent());
		}else if (isDoorsOpened()) {
			notifyObservers(new IDoorsOpenEvent());
		}
	}

	/**
	 * Checks if doors are being opened
	 * @return True if doors are opened, false if they are not opened
	 */
	private boolean isDoorsOpened(){
		if(doorsState.equals(Constants.DOORS_OPEN)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if doors are being closed
	 * @return True if doors are close, false if they are not closed
	 */
	private boolean isDoorsClosed(){
		if(doorsState.equals(Constants.DOORS_CLOSED)){
			return true;
		}else{
			return false;
		}
	}
}
