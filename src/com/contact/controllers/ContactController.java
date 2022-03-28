/**
 * 
 */
package com.contact.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.contact.helpers.FileManager;
import com.contact.models.ContactModel;
import com.contact.tools.ContactPopup;
import com.contact.tools.ContactTemplate;
import com.contact.views.ContactView;

/**
 * @author mizael
 *
 */
public class ContactController extends Controller {
	private ContactModel contactModel;
	private ContactView contactView;
	
	private final FileManager FILE_MANAGER;
	private List<ContactTemplate> contactTemplates; 
	
	public ContactController(ContactModel contactModel, ContactView contactView) {
		this.contactModel = contactModel;
		this.contactView = contactView;
		
		this.FILE_MANAGER = new FileManager("./data/");
		
		this.contactTemplates = new ArrayList<ContactTemplate>();
		
		fileToScreen(null);
		screenToFile();
		

		
	}
	
	
	private boolean confirm(String question) {
		int option = JOptionPane.showConfirmDialog(null,
				question,
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		return option == JOptionPane.YES_OPTION;
	}	
	
	private void fileToScreen(String fileName) {
		
		removeComponentAt(2);
		
		contactTemplates.clear();
		List<FileManager> fileManagers = FILE_MANAGER.getFilesFromSamePath(fileName);


		setContactsCount(fileManagers.size());
		setContactsSelectedCount(contactTemplates.size());
		
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout((fileManagers.size() / 3), 3));
		grid.setBackground(new Color(41, 30, 39));
		
		for(FileManager fileManager: fileManagers) {

			contactModel = new ContactModel(fileManager.getFileName());
			
			ContactTemplate contactTemplate = new ContactTemplate(contactModel);
			contactTemplate.setBackground(new Color(41, 30, 39));
			grid.add(contactTemplate);
			
			
			contactTemplate.addMouseListener(new MouseAdapter() {
				
							
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					contactTemplate.getSelectCheckBox().setVisible(true);
					
					if(!contactTemplate.getSelectCheckBox().isSelected()) {
						contactTemplate.getSelectCheckBox().setSelected(true);
						
						// Zay clicked ihany no apidirina anaty liste
						contactTemplates.add(contactTemplate);
						
					}
					
					else {
						contactTemplate.getSelectCheckBox().setSelected(false);
						
						// Fafana zay tsy clicked sad atao false mihitsy le setSelected()
						contactTemplates.remove(contactTemplate);

					}
					
					setContactsSelectedCount(contactTemplates.size());
					
					
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					contactTemplate.getNameLabel().setForeground(Color.white);
					contactTemplate.setBorder(BorderFactory.createLineBorder(Color.white));
					contactTemplate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					contactTemplate.getNameLabel().setFont(new Font("Monospace", Font.BOLD, 18));
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					contactTemplate.setCursor(null);
					contactTemplate.setBorder(null);
					contactTemplate.getNameLabel().setForeground(Color.lightGray);
					contactTemplate.getNameLabel().setFont(new Font("Monospace", Font.ITALIC, 15));
					
					if(!contactTemplate.getSelectCheckBox().isSelected()) {
						contactTemplate.getSelectCheckBox().setVisible(false);
					}
					
					
				}
			});
			
			
			
			
			
		}
		
		
		JPanel pan = new JPanel();
		pan.setBackground(new Color(41, 30, 39));
		pan.add(grid);

		
		JScrollPane scroll = new JScrollPane(pan);
		loadComponent(scroll);
		
	}
	
	private void loadComponent(Component component) {
		contactView.getContentPane().add(component);
		contactView.setContentPane(contactView.getContentPane());
	}
	
	private void removeComponentAt(int position) {

		try {
			contactView.getContentPane().remove(position);
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
	}

	
	private void screenToFile() {
		
		contactView.getSearchButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 
				fileToScreen(contactView.getSearchTextField().getText());
			}
		});
		
		
		contactView.getNewButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ContactPopup contactPopup = new ContactPopup(
						null, "Ajouter contact", true, new ContactModel());

				contactModel.setContactModel(contactPopup.getContact());
				contactModel.create();
				fileToScreen(null);
			}
		});
		
		
		contactView.getEditButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int contactsCliked = contactTemplates.size();
				
				if(contactsCliked == 0) {
					JOptionPane.showMessageDialog(
							null, "Selectionnez le contact à modifier !", "Modification", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(contactsCliked > 1) {
					JOptionPane.showMessageDialog(
						null, "On ne peut modifier qu'un seul contact !", "Modification", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					contactModel = contactTemplates.get(0).getContact(); 
					
					ContactPopup contactPopup = new ContactPopup(
							null, "Modifier contact", true, contactModel);
					contactModel.delete();
					contactModel.update(contactPopup.getContact());
					fileToScreen(null);
				
				}
				
			}
		});
		
		
		contactView.getDelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(contactTemplates.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "Selectionnez le(s) contact(s) à supprimer !", "Suppression", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					String contactDeleted = (contactTemplates.size() > 1) ? "ces contacts":"ce contact";
					
					if(confirm("Voulez-vous vraiment supprimer " + contactDeleted + " ?")) {
						
						contactDeleted = (contactTemplates.size() > 1) ? "contacts supprimés":"contact supprimé";
						
						for(int i = 0; i < contactTemplates.size(); i++) {
							contactModel = contactTemplates.get(i).getContact();
							contactModel.delete();
			
						}
						
						fileToScreen(null);
						JOptionPane.showMessageDialog(
								null, contactDeleted + " avec succès !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
						
							
						
					}
					
					
					else {
						JOptionPane.showMessageDialog(
								null, "Suppression annulé", "Suppression", JOptionPane.WARNING_MESSAGE);

						
					}
					
				}
				
				
				
			}

		});
		
		contactView.getClearButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Ovaina nom anle clear button
				if(confirm("Voulez-vous vraiment tout supprimer ?")) {
					FILE_MANAGER.delFilesFromSamePath();
					
				}
				
			}
		});
		
		contactView.getRefreshButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fileToScreen(null);
			}
		});
		
		
	}
	
	private void setContactsCount(int count) {
		contactView.getContactCounterTextField().setText(String.valueOf(count));
		
	}
	
	private void setContactsSelectedCount(int count) {
		contactView.getContactSelectedTextField().setText(String.valueOf(count));
	}
	
	


}
