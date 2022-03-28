/**
 * 
 */
package com.contact.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import com.contact.helpers.FileManager;
import com.contact.helpers.Initializable;
import com.contact.helpers.Stylable;
import com.contact.models.ContactModel;

/**
 * @author mizael
 */
public class ContactPopup extends JDialog implements Initializable, Stylable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private ContactModel contactModel;
	private JLabel profileLabel, nameLabel, telLabel, mailLabel;
	private JTextField profileTextField, nameTextField, telTextField, mailTextField;
	private JButton saveButton, cancelButton, browseButton;




	public ContactPopup(JFrame parent, String title, boolean modal, ContactModel contactModel) {
		super(parent, title, modal);
		this.contactModel = contactModel;
		this.title = title;
		
		initComponents();
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	

	

	private JPanel getBottomComponents() {
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(41, 30, 39));
		
		
		// Arakaraka ilay button notsindrina ny message
		this.saveButton = new JButton("Enregistrer");
		this.saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				ContactModel contact = new ContactModel(
						profileTextField.getText(),
						nameTextField.getText(),
						telTextField.getText(),
						mailTextField.getText());
						
				if(!ContactValidator.isValid(contact, title)) {
					JOptionPane.showMessageDialog(
							ContactPopup.this, ContactException.getContactErrors(), "Erreur", JOptionPane.ERROR_MESSAGE);
					ContactException.delContactErrors();
					setVisible(true);
				}
				else {
					// Copier et remplacer le profile
					contactModel.getFileManager().copyFile(
							profileTextField.getText(), contactModel.getProfile());
					
					profileTextField.setText(contactModel.getFileManager().getFilePath(false));
					
					contact.setProfile(profileTextField.getText());
					contactModel.setContactModel(contact);
					
					// Message à afficher
					String message = (title.equals("Ajouter contact") ? "ajouté":"modifié"); 
					JOptionPane.showMessageDialog(
							ContactPopup.this, "Contact " + message + " avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
					
					setVisible(false);
				}
				
//				System.out.println(contactModel.toString());
				
			
				
				
			}
		});
		
		
		
		
		this.cancelButton = new JButton("Annuler");
		this.cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		
		bottom.add(this.saveButton);	
		bottom.add(this.cancelButton);	
		return bottom;
		
	}
	
	public ContactModel getContact() {
		setVisible(true);
		return this.contactModel.getContactModel();
	}
	
	private JPanel getMiddleComponents() throws ParseException {
//		System.out.println(contactModel.toString());
		JPanel middle = new JPanel();
		middle.setBorder(BorderFactory.createTitledBorder(
				null, "Informations", 0, 0, new Font("Déjà Vu", Font.BOLD, 16), new Color(10, 200, 100)));
		middle.setLayout(new GridLayout(3, 2));
		middle.setBackground(new Color(41, 30, 39));
		
		this.nameLabel = new JLabel("Nom du contact :");
		this.nameTextField = new JTextField();
		this.nameTextField.setText(contactModel.getName());
		
		this.telLabel = new JLabel("Numéro de téléphone :");
		this.telTextField = new JFormattedTextField(new MaskFormatter("### ## ### ##"));
		this.telTextField.setText(contactModel.getTel());
		
		this.mailLabel = new JLabel("Adresse e-mail :");
		this.mailTextField = new JTextField();
		this.mailTextField.setText(contactModel.getMail());
	
		middle.add(this.nameLabel);
		middle.add(this.nameTextField);
		middle.add(this.telLabel);
		middle.add(this.telTextField);
		middle.add(this.mailLabel);
		middle.add(this.mailTextField);
		
		
		return middle;
	}
	
	private JPanel getTopComponents() {
		JPanel top = new JPanel();
		top.setBackground(new Color(41, 30, 39));
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.setBackground(new Color(41, 30, 39));
		
		
//		System.out.println(contactModel.getProfile());
		
		
		this.profileLabel = new JLabel(
				contactModel.getFileManager().createIcon(
						contactModel.getProfile(), 100, 100, Image.SCALE_SMOOTH));
		
		this.profileTextField = new JTextField(contactModel.getProfile());
		
		this.browseButton = new JButton("Parcourir");
		this.browseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sourceSelectedFile = contactModel.getFileManager().browseFile("Selectionnez une image", "Image PNG", "png");
//				System.out.println(sourceSelectedFile);
				
				if(!sourceSelectedFile.isEmpty()) {
					profileTextField.setText(sourceSelectedFile);
				}
				
				profileLabel.setIcon(
						contactModel.getFileManager().createIcon(
								profileTextField.getText(), 100, 100, Image.SCALE_SMOOTH));
				
			}
		});
		
		box.add(this.profileLabel);
		box.add(this.browseButton);
		
		top.add(box);
		
		return top;
	}
	
	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		getContentPane().add(getTopComponents(), BorderLayout.NORTH);
		
		try {
			getContentPane().add(getMiddleComponents());
		}catch(ParseException e) {
			
		}
		
		getContentPane().add(getBottomComponents(), BorderLayout.SOUTH);
		
		styleComponents();
		
		
	}
	
	@Override
	public void styleComponents() {
		// TODO Auto-generated method stub
		this.saveButton.setIcon(
				new FileManager().createIcon(
						"./icons/save.png", 20, 20, Image.SCALE_SMOOTH));
		this.saveButton.setBackground(new Color(41, 30, 39));
		this.saveButton.setForeground(Color.white);
		
		
		this.cancelButton.setIcon(
				new FileManager().createIcon(
						"./icons/annuler.png", 20, 20, Image.SCALE_SMOOTH));
		this.cancelButton.setBackground(new Color(41, 30, 39));
		this.cancelButton.setForeground(Color.white);
		
		this.browseButton.setIcon(
				new FileManager().createIcon(
						"./icons/dossier.png", 20, 20, Image.SCALE_SMOOTH));
		this.browseButton.setBackground(new Color(41, 30, 39));
		this.browseButton.setForeground(Color.white);
		
		
		this.nameLabel.setForeground(Color.white);
		this.telLabel.setForeground(Color.white);
		this.mailLabel.setForeground(Color.white);
		
		
		this.nameTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		this.telTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		this.mailTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		
		this.nameTextField.setFont(new Font("Déjà Vu", Font.BOLD, 14));
		this.telTextField.setFont(new Font("Déjà Vu", Font.BOLD, 14));
		this.mailTextField.setFont(new Font("Déjà Vu", Font.BOLD, 14));
	
	}
	
	
	

	

}
