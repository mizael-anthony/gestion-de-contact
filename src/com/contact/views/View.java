/**
 * 
 */
package com.contact.views;

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author mizael
 *
 */
public abstract class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title;
	protected int x, y;
	private LayoutManager layoutManager;
	private boolean resizable;
	protected JPanel container;
	
	public View(String title, int x, int y, LayoutManager layoutManager, boolean resizable) {
		this.title = title;
		this.x = x;
		this.y = y;
		this.layoutManager = layoutManager;
		this.resizable = resizable;
		this.container = new JPanel();
		this.container.setLayout(this.layoutManager);
		
	}
	
	public void launch() {
		setTitle(this.title);
		setSize(this.x, this.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(this.resizable);
		setContentPane(this.container);
		setVisible(true);
	}
	
	

	
}
