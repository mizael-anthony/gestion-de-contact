/**
 * 
 */
package com.contact.models;

import java.util.List;

/**
 * @author mizael
 *
 */
public abstract class Model {
	protected List<String> informations;
	
	public abstract void create();
	public abstract void delete();
	public abstract Object read();
	public abstract void update(Object obj);
	

}
