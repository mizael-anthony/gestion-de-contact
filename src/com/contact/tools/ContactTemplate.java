/**
 * 
 */
package com.contact.tools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.contact.helpers.Initializable;
import com.contact.helpers.Stylable;
import com.contact.models.ContactModel;

/**
 * @author mizael
 *Ato no manao editButton sy delButton de géréna ny event tsirairay
 *
 *
 *
 */
public class ContactTemplate extends JPanel implements Initializable, Stylable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContactModel contactModel;
	private JLabel profileLabel, nameLabel, telLabel, mailLabel;
	private JCheckBox selectCheckBox;


	public ContactTemplate(ContactModel contactModel) {
		this.contactModel = contactModel; 
		initComponents();
	}
	
	
	public ContactModel getContact() {
		return contactModel;
	}
	
	
	/**
	 * @return the nameLabel
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}


	/**
	 * @return the selectCheckBox
	 */
	public JCheckBox getSelectCheckBox() {
		return selectCheckBox;
	}
	
	
	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		Box vertical = Box.createVerticalBox();

		this.profileLabel = new JLabel(
				contactModel.getFileManager().createIcon(
						contactModel.getProfile(), 100, 100, Image.SCALE_SMOOTH));
		
		this.nameLabel = new JLabel(contactModel.getName());
		this.telLabel = new JLabel(contactModel.getTel());
		this.mailLabel = new JLabel("<html><a href=#>" + contactModel.getMail() + "</a></html>");
		this.selectCheckBox = new JCheckBox();
		
		this.selectCheckBox.setVisible(false);
		this.selectCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		vertical.add(this.selectCheckBox);
		vertical.add(this.nameLabel);
		vertical.add(this.telLabel);
		vertical.add(this.mailLabel);
		
		
		setFocusable(true);
		setLayout(new GridLayout(1, 2));
		add(this.profileLabel);
		add(vertical);
		
		styleComponents();
		
		
	}

	@Override
	public void styleComponents() {
		// TODO Auto-generated method stub
		
		Font fontName = new Font("Monospace", Font.ITALIC, 15);
		this.nameLabel.setFont(fontName);
		
		Font fontTel = new Font("Monospace", Font.BOLD, 14); 
		this.telLabel.setFont(fontTel);
		
		Color nature = new Color(10, 200, 100);
		this.nameLabel.setForeground(Color.lightGray);
		this.telLabel.setForeground(nature);
		this.selectCheckBox.setBackground(new Color(41, 30, 39));
		

	}
	
}
