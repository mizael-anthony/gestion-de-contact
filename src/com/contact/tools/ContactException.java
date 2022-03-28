/**
 * 
 */
package com.contact.tools;

/**
 * @author mizael
 *
 */
public class ContactException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String errors;

	public static void addContactErrors(String error) {
		errors += error + "\n";
	}
	
	public static void delContactErrors() {
		errors = "";
	}
	
	public static String getContactErrors() {
		return errors;
	}
	
	public static void setContactErrors(String error) {
		errors = error;
	}
	
	public ContactException() {
		errors = new String();
	}
	
	public ContactException(String message) {
		super(message);
	}
	
	
	
	
	
	

}
