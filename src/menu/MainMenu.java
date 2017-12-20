package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private static MainMenu frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {
					frame = new MainMenu();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setBounds(100, 100, 1000, 700);
		setLocation(200, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		menuGui mg = new menuGui(getContentPane());
		this.add(mg);
		this.setVisible(true);
	}

}
