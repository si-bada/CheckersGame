package connect;

import menu.menuGui;

import javax.swing.JLabel;

import dame.Checkers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Choice extends Container implements ActionListener {

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton button;
	private JLabel label;
			Container cp;
	
	/**
	 * Create the panel.
	 */
	public Choice(Container cp) {
		setLayout(null);
		setVisible(true);
		this.setSize(1000,700);
		this.cp=cp;
		btnNewButton = new JButton("");
		btnNewButton.setFocusable(true);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\user.png"));
		btnNewButton.setBounds(431, 430, 159, 149);
		btnNewButton.setPreferredSize(new Dimension(100,100));
		btnNewButton.addActionListener(this);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusable(true);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\login.png"));
		btnNewButton_1.setBounds(426, 118, 164, 200);
		btnNewButton_1.addActionListener(this);
		add(btnNewButton_1);
		
		button = new JButton("");
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\back.png"));
		button.setBounds(831, 583, 159, 93);
		button.addActionListener(this);
		add(button);
		
		label = new JLabel("");
		label.setFocusable(false);
		label.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\background.png"));
		label.setBounds(0, 0, 1031, 893);
		add(label);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() ==btnNewButton){
			new User();
		}
		if(arg0.getSource()==btnNewButton_1){
			new Login();
		}
		if(arg0.getSource()==button){
			
			//this.getGraphics().clearRect(0, 0, this.getWidth(),this.getHeight());
			cp.removeAll();
			cp.revalidate();
			menuGui mn = new menuGui(cp);
			mn.setVisible(true);
			cp.add(mn);
			cp.revalidate();
			
		}
	}
}
