package menu;

import connect.Choice;
import connect.DataBaseLeaderBoard;
import dame.Checkers;
import dame.online;
import pack.Client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class menuGui extends JPanel implements MouseListener,Runnable {
	private Image i_plyonln;
	private Image i_bg;
	private Image i_lb;
	
	private Image i_2ply;
	private int x;
	private int y;
	private Image i_prof;
	public static Container cp;
	private pack.Client cli;
	private Object i_back;
	private int y2;
	private int x1;
	private int y1;
	private int x2;
	private int y3;
	private int x3;
	public menuGui(Container container) {
		setLayout(null);
		this.addMouseListener(this);
		this.cp=container;
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(43, 39, 89, 23);
		add(btnNewButton);
		
		try {
			i_bg  = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\background.png");
			i_prof  = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\profile.png");
			i_plyonln= Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\online.png");
			i_2ply= Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\src\\PlayerVsPlayer.png");
			i_lb =Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\src\\LeaderBoard.png");
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}




	public void update( Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, size().width, size().height);
		if(i_bg != null) {
			int x = 0, y = 0;
			while(y < size().height) {
				x = 0;
				while(x< size().width) {
					g.drawImage( i_bg, x, y, this);
					x=x+( i_bg).getWidth(null);
				}
				y=y+i_bg.getHeight(null);
			}
		}
		else {
			g.clearRect(0, 0, size().width, size().height);
		}

		x=(this.getWidth()/2)-(i_plyonln.getWidth(null)/2);
		y=(this.getHeight()/6);
		x1=this.getWidth()/2-(i_2ply.getWidth(null)/2);
		y1=this.getHeight()-430;
		x2=this.getWidth()/2-(i_prof.getWidth(null)/2);
		y2=this.getHeight()-280;
		x3=this.getWidth()/2-(i_lb.getWidth(null)/2);
		y3=this.getHeight()-150;
		g.drawImage( i_plyonln,x,y, this);
		g.drawImage( i_2ply,x1,y1,this);
		g.drawImage(i_prof, x2,y2,this);
		g.drawImage(i_lb,x3 ,y3,this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			//Click sur Online
			if ((getMousePosition().getX()>this.getWidth()/2-(i_plyonln.getWidth(null)/2))&&(getMousePosition().getX()<this.getWidth()/2-(i_plyonln.getWidth(null)/2)+i_plyonln.getWidth(null))) {
				if ((getMousePosition().getY()>this.getHeight()/6)&&(getMousePosition().getY()<this.getHeight()/6+i_plyonln.getHeight(null))) {
					cp.removeAll();
					online o=new online(cp);
					o.update(cp.getGraphics());
					Client cli = new Client();
					cli.setVisible(true);
					cp.add(o);
					cp.add(cli);
					cp.revalidate();

				}
			}
		} catch (java.lang.NullPointerException | RemoteException | NotBoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		//Click sur 2 Players
			if ((getMousePosition().getX()>this.getWidth()/2-i_2ply.getWidth(null)/2)&&(getMousePosition().getX()<this.getWidth()/2-(i_2ply.getWidth(null)/2)+i_2ply.getWidth(null))) 
			{

				if ((getMousePosition().getY()>this.getHeight()-430)&&(getMousePosition().getY()<this.getHeight()-430+i_2ply.getHeight(null))) 
				{				
					this.getGraphics().clearRect(0, 0, size().width, size().height);
					cp.removeAll();
					cp.revalidate();
					Checkers c=new Checkers(cp);
					c.update(cp.getGraphics());
					cp.add(c);
					cp.revalidate();

				}


			}
		

	//Click Sur Profile
			if ((getMousePosition().getX()>this.getWidth()/2-(i_prof.getWidth(null)/2))&&(getMousePosition().getX()<this.getWidth()/2-(i_prof.getWidth(null)/2)+i_prof.getWidth(null))) {

				if ((getMousePosition().getY()>this.getHeight()-300)&&(getMousePosition().getY()<this.getHeight()-300+i_prof.getHeight(null))) {
					this.getGraphics().clearRect(0, 0, size().width, size().height);
					cp.removeAll();
					cp.revalidate();
					Choice ch = new Choice(cp);
					ch.setVisible(true);
					cp.add(ch);
					cp.revalidate();

				}

			}
		//Click sur LeaderBoard
			try {
				if ((getMousePosition().getX() > this.getWidth()/2-(i_lb.getWidth(null)/2)) && (getMousePosition().getX() < this.getWidth()/2-(i_lb.getWidth(null)/2)+i_lb.getWidth(null))) {
				if ((getMousePosition().getY()>this.getHeight()-150)&&(getMousePosition().getY()<this.getHeight()-150+i_lb.getHeight(null))) {
					new DataBaseLeaderBoard ();

				}
}
			} catch (java.lang.NullPointerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}

