/**
 * 
 */
package com.contact.tools;

import com.contact.models.ContactModel;

/**
 * @author mizael
 */
public class ContactValidator {
	private static boolean existsName(ContactModel contact) {
		return contact.getFileManager().getFilesFromSamePath(contact.getName()).isEmpty();
	}
	
	private static boolean existsTel(ContactModel contact) {
		return contact.getFileManager().getFilesFromSamePath(contact.getTel()).isEmpty();
	}
	
	private static boolean isMail(ContactModel contact) {
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String mail = contact.getMail();
		return mail.matches(EMAIL_PATTERN);
	}	
	
	private static boolean isName(ContactModel contact) {
		//TODO: autorisation espace voir regex 
		final String NAME_PATTERN = "^[A-Za-z0-9]*$";
		String name = contact.getName(); 
		return name.matches(NAME_PATTERN);
	}
	
	private static boolean isTel(ContactModel contact) {
		final String TEL_PATTERN = "^03[[2-4]9]";
		String tel = contact.getTel();
		tel = tel.replace(" ", "");
		return (tel.length() == 10) && (tel.substring(0, 3).matches(TEL_PATTERN));
	}
	
	private static boolean isThereEmpty(ContactModel contact) {
		String name = contact.getName();
		String tel = contact.getTel();
		String mail = contact.getMail();
		return (name.isEmpty()) || (tel.isBlank() || tel.isEmpty()) || (mail.isEmpty());
	}
	
	public static boolean isValid(ContactModel contactModel, String title) {
		boolean valid = true;
		ContactException contactException = new ContactException();
		
		
		try {
			// Ref popup dialog ajout leiz de mila vérifiena le anarana sy le num
			if(title.equals("Ajouter contact")) {
				if(!existsName(contactModel))
					ContactException.addContactErrors(contactModel.getName() + " existe déjà !");
				if(!existsTel(contactModel))
					ContactException.addContactErrors("Ce numéro appartient déjà à un contact !");
			
			}
			
			if(!isName(contactModel))
				ContactException.addContactErrors("Le nom ne doit pas contenir d'espace ni de caractère bizarre !");
			if(!isTel(contactModel))
				ContactException.addContactErrors("Ce numéro de téléphone n'existe pas à Madagascar !");
			if(!isMail(contactModel))
				ContactException.addContactErrors("Adresse e-mail incorrecte !");
			if(isThereEmpty(contactModel))
				ContactException.setContactErrors("Veuillez remplir le(s) champs vide !");
			
			// Si la liste des exceptions n'est pas vide
			if(!ContactException.getContactErrors().isEmpty())
				throw contactException;
			
			
		}catch(ContactException e) {
			valid = false;
		}

		return valid;
	}
	
	
}
