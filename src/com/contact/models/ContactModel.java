/**
 * 
 */
package com.contact.models;

import java.io.Serializable;

import com.contact.helpers.FileManager;

/**
 * @author mizael
 *
 */
public class ContactModel extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String profile, name, tel, mail;
	private transient FileManager fileManager;
	private final transient String DEFAULT_PATH = new String("./data/");
	private final transient String DEFAULT_PROFILE = new String("./img/google-contacts.png");

	public ContactModel() {
		this.profile = DEFAULT_PROFILE;
		this.name = "";
		this.tel = " ";
		this.mail = "";
		
		this.fileManager = new FileManager();
	}
	
	public ContactModel(String file) {
		this.fileManager = new FileManager(DEFAULT_PATH + file);
		setContactModel(read());
		
	
	}
	
	
	public ContactModel(String profile, String name, String tel, String mail) {

		setProfile(profile);
		setName(name);
		setTel(tel);
		setMail(mail);
		
		this.fileManager = new FileManager(DEFAULT_PATH + getName());
	}
	
	
	
	@Override
	public void create() {	
		this.fileManager.writeObjectFile(this);
	}
	
	@Override
	public void delete() {
		this.fileManager.removeFile();
	}
	
	public ContactModel getContactModel() {
		return this;
	}
	
	/**
	 * @return the fileManager
	 */
	public FileManager getFileManager() {
		return fileManager;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	@Override
	public ContactModel read(){
		return (ContactModel) this.fileManager.readObjectFile();
	}

	public void setContactModel(ContactModel contact) {
		setProfile(contact.getProfile());
		setName(contact.getName());
		setTel(contact.getTel());
		setMail(contact.getMail());
		this.fileManager.setFilePath(DEFAULT_PATH + getName());
	}
	
	/**
	 * @param fileManager the fileManager to set
	 */
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
		
	}

	@Override
	public String toString() {
		return getProfile() + "\n" + getName() + "\n" + getTel() + "\n" + getMail();
	}
	
	
	@Override
	public void update(Object obj) {
		ContactModel contact = (ContactModel)obj;
		this.fileManager.writeObjectFile(contact);
		setContactModel(contact);
		
	}
	
	
	

	
	

}
