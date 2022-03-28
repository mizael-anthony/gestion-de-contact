/**
 * 
 */
package com.contact.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.contact.helpers.FileManager;
import com.contact.helpers.Initializable;
import com.contact.helpers.Stylable;



/**
 * @author mizael
 *
 */
public class ContactView extends View implements Initializable, Stylable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField searchTextField, contactCounterTextField, contactSelectedTextField;
	private JLabel contactCounterLabel, contactSelectedLabel;
	private JButton searchButton, newButton, editButton, delButton, clearButton, refreshButton;



	public ContactView() {
		super("Gestion de contact", 850, 650, new BorderLayout(), false);
	
		initComponents();
		super.launch();
		
	}

	
	private JPanel getBottomComponents() {
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(41, 30, 39));
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(1, 5, 25, 5));
		grid.setBackground(new Color(41, 30, 39));
		this.newButton = new JButton("Ajouter");
		
		this.editButton = new JButton("Modifier");
		
		this.delButton = new JButton("Supprimer");
		
		this.refreshButton = new JButton("Actualiser");
		
		this.clearButton = new JButton("Réinitialiser");
		
		
		grid.add(this.newButton);
		grid.add(this.editButton);
		grid.add(this.delButton);
		grid.add(this.refreshButton);
		grid.add(this.clearButton);
		
		bottom.add(grid);
		return bottom;
	}
	
	

	
	/**
	 * @return the clearButton
	 */
	public JButton getClearButton() {
		return clearButton;
	}
	
	
	/**
	 * @return the contactCounterTextField
	 */
	public JTextField getContactCounterTextField() {
		return contactCounterTextField;
	}
	
	/**
	 * @return the contactSelectedTextField
	 */
	public JTextField getContactSelectedTextField() {
		return contactSelectedTextField;
	}

	
	
	
	/**
	 * @return the delButton
	 */
	public JButton getDelButton() {
		return delButton;
	}




	/**
	 * @return the editButton
	 */
	public JButton getEditButton() {
		return editButton;
	}




	/**
	 * @return the newButton
	 */
	public JButton getNewButton() {
		return newButton;
	}




	/**
	 * @return the refreshButton
	 */
	public JButton getRefreshButton() {
		return refreshButton;
	}
	
	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}


	/**
	 * @return the searchTextField
	 */
	public JTextField getSearchTextField() {
		return searchTextField;
	}
	
	
	
	private JPanel getTopComponents() {

		JPanel top = new JPanel();
		top.setBackground(new Color(41, 30, 39));
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(2, 3, 100, 5));
		grid.setBackground(new Color(41, 30, 39));
		
		this.contactCounterLabel = new JLabel("Nombre de contacts");
		this.contactCounterTextField = new JTextField("0");
		this.contactCounterTextField.setEditable(false);
		
		this.contactSelectedLabel = new JLabel("Contact(s) selectionné(s)");
		this.contactSelectedTextField = new JTextField("0");
		this.contactSelectedTextField.setEditable(false);
		
		this.searchTextField = new JTextField();
		this.searchButton = new JButton("Rechercher");
		
		
		grid.add(this.contactCounterLabel);
		grid.add(this.searchTextField);
		grid.add(this.contactSelectedLabel);
		
		grid.add(this.contactCounterTextField);
		grid.add(this.searchButton);
		grid.add(this.contactSelectedTextField);
		
		top.add(grid);
		return top;
		
	}
	
	
	
	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		// Mila get container mihitsy
		
		super.container.add(getTopComponents(), BorderLayout.NORTH);
		super.container.add(getBottomComponents(), BorderLayout.SOUTH);
		styleComponents();
	}


	@Override
	public void styleComponents() {
		// TODO Auto-generated method stub
		
		
		this.searchButton.setIcon(new FileManager().createIcon(
				"./icons/search.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.newButton.setIcon(new FileManager().createIcon(
				"./icons/add.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.editButton.setIcon(new FileManager().createIcon(
				"./icons/edit.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.delButton.setIcon(new FileManager().createIcon(
				"./icons/delete.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.refreshButton.setIcon(new FileManager().createIcon(
				"./icons/refresh.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.clearButton.setIcon(new FileManager().createIcon(
				"./icons/reinitialiser.png", 25, 25, Image.SCALE_SMOOTH));
		
		Color brun = new Color(41, 30, 39);
		this.searchButton.setBackground(brun);
		this.searchButton.setForeground(Color.white);
		
		this.newButton.setBackground(brun);
		this.newButton.setForeground(Color.white);
		
		this.editButton.setBackground(brun);
		this.editButton.setForeground(Color.white);
		
		this.delButton.setBackground(brun);
		this.delButton.setForeground(Color.white);
		
		this.refreshButton.setBackground(brun);
		this.refreshButton.setForeground(Color.white);
		
		this.clearButton.setBackground(brun);
		this.clearButton.setForeground(Color.white);
		
		
		this.searchTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		this.contactCounterTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		this.contactSelectedTextField.setBorder(
				BorderFactory.createLineBorder(Color.darkGray, 2));
		
		Font fontTextField= new Font("Monospace", Font.BOLD, 14);
		this.searchTextField.setFont(fontTextField);
		this.contactCounterTextField.setFont(fontTextField);
		this.contactSelectedTextField.setFont(fontTextField);
		
		this.contactCounterTextField.setBackground(brun);
		this.contactSelectedTextField.setBackground(brun);
		
		
		this.contactCounterTextField.setForeground(Color.white);
		this.contactSelectedTextField.setForeground(Color.white);
		
		Font fontLabel = new Font("Monospace", Font.ITALIC, 14);
		this.contactCounterLabel.setFont(fontLabel);
		this.contactSelectedLabel.setFont(fontLabel);
		
		
		this.contactCounterLabel.setIcon(new FileManager().createIcon(
				"./icons/contact.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.contactSelectedLabel.setIcon(new FileManager().createIcon(
				"./icons/contact.png", 25, 25, Image.SCALE_SMOOTH));
		
		this.contactCounterLabel.setForeground(Color.white);
		this.contactSelectedLabel.setForeground(Color.white);
		
		
		
	}


}
