package ProjectBinaryIO;

import java.util.ArrayList;
import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * Memento saves the fields of AdvancedIceCreamCone and has mutators for stored fields.
 * Also implements Serializable for use with CareTaker to write into storage.
 * @author Alexander Dung
 *
 */
public class Memento implements Serializable{
	
	/** The number of scoops. */
	private int numberOfScoops;
	
	/** The flavor. */
	private String flavor;
	
	/** The type of cone. */
	private String typeOfCone;
	
	/** The toppings. */
	private ArrayList<String> toppings = new ArrayList<String>();
	
	/**
	 * Instantiates a new memento with empty topppings.
	 *
	 * @param ns the number of scoops
	 * @param flv the flavor of ice cream
	 * @param cone the type of cone
	 */
	public Memento(int ns, String flv, String cone) {
		this.numberOfScoops = ns;
		this.flavor = flv;
		this.typeOfCone = cone;
		this.toppings = new ArrayList<String>();
	}
	
	/**
	 * Instantiates a new memento with given toppings.
	 *
	 * @param ns the number of scoops
	 * @param flv the flavor of ice cream
	 * @param cone the type of cone
	 * @param tp the topping(s)
	 */
	public Memento(int ns, String flv, String cone, ArrayList<String> tp) {
		this.numberOfScoops = ns;
		this.flavor = flv;
		this.typeOfCone = cone;
		this.toppings = tp;
	}
	
	/**
	 * Gets the number of scoops.
	 *
	 * @return the number of scoops
	 */
	public int getScoops() {
		return this.numberOfScoops;
	}
	
	/**
	 * Gets the flavor of ice cream.
	 *
	 * @return the flavor of ice cream
	 */
	public String getFlavor() {
		return this.flavor;
	}
	
	/**
	 * Gets the type of cone.
	 *
	 * @return the type of cone
	 */
	public String getCone() {
		return this.typeOfCone;
	}
	
	/**
	 * Gets the toppings.
	 *
	 * @return the toppings
	 */
	public ArrayList<String> getToppings(){
		return this.toppings;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("This memento represents an AdvancedIceCreamCone with the following fields: \n"
				+ "%d scoop(s) of %s ice cream on a %s cone, with toppings: %s", numberOfScoops, flavor, typeOfCone, toppings.toString());
	  }
}
