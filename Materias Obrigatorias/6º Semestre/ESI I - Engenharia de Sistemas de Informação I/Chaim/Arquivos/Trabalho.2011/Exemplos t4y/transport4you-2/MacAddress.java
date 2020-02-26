package dsd.transport4you.interfaces.model;

/**
 * Abstract class that represents one mac address
 * @author Dino
 *
 */
public class MacAddress implements Comparable<MacAddress>{

	/**
	 * Mac Address type. BT or WiFi
	 * 
	 * @author Dino
	 * 
	 */
	public enum AddressType {
		/**
		 * BT mac address.
		 */
		BLUETOOTH,
		
		/**
		 * WiFi mac address
		 */
		WIFI
	}
	
	private AddressType addressType;
	private String address;
	
	public MacAddress(){
		super();
	}
	
	public MacAddress(AddressType addressType, String address) {
		super();
		this.addressType = addressType;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	@Override
	public int compareTo(MacAddress other) {
		return address.compareTo(other.address);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((addressType == null) ? 0 : addressType.hashCode());
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
		MacAddress other = (MacAddress) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (addressType != other.addressType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return addressType+"-"+address;
	}
}
