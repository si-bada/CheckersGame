package dame;


	import java.awt.*;
	import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.ConnectionDataBase;
import connect.Login;
import menu.menuGui;

import javax.swing.JList;
import javax.swing.JPanel;



	@SuppressWarnings("serial")
	public class Checkers extends JPanel implements MouseListener,Runnable {

		private Image i_bg;
		private CheckersCanvas board=null;
		private Image i_back=null;
		private Container cp;
				String pseudo;
				
		public Checkers(Container cp) {
			this.cp=cp;
			setLayout(null);
			cp.setPreferredSize(new Dimension(700, 900));
			this.addMouseListener(this);
			JList list = new JList();
			list.setBackground(Color.RED);
			list.setBounds(309, 193, 115, -129);
			pseudo = Login.getPseudo();
			add(list);
			try {
				i_bg  = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\background.png");
				i_back  = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\back.png");

			} catch (Exception e) {
			}

			board = new CheckersCanvas();
			add(board);

			board.newGameButton.setBackground(Color.lightGray);
			add(board.newGameButton);

			board.resignButton.setBackground(Color.lightGray);
			add(board.resignButton);

			board.message.setForeground(Color.BLACK);
			board.message.setFont(new Font("Serif", Font.BOLD, 14));
			add(board.message);
			board.setBounds(0,0,480,480);
			board.newGameButton.setBounds(board.getWidth(), 60, 100, 30);
			board.resignButton.setBounds(board.getWidth(), 120, 100, 30);
			board.message.setBounds(0, board.getHeight(), 330, 30);

			

		}


		public void start()
		{
		
		}
		public void update(Graphics g)
		{
			paint(g);
			
		}
		public void paint(Graphics g)
		{g.clearRect(0, 0, size().width, size().height);
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
		while (i_back==null) {
			try {

				i_back= Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\online.png");
			} catch (Exception e) {
			}

		}
		g.drawImage( i_back,size().width-i_back.getWidth(this),size().height-i_back.getHeight(this), this);
		

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if ((getMousePosition().getX()>this.getWidth()-(i_back.getWidth(null)))&&(getMousePosition().getX()<this.getWidth())) {
				if ((getMousePosition().getY()>this.getHeight()-(i_back.getHeight(null)))&&(getMousePosition().getY()<this.getHeight())) {
					this.getGraphics().clearRect(0, 0, size().width, size().height);
					cp.removeAll();
					cp.revalidate();
					menuGui o=new menuGui(cp);
					o.update(cp.getGraphics());
					cp.add(o);
					cp.revalidate();
					

				}
			}
		}

		public void run() {
		
		}





		class CheckersCanvas extends Canvas implements ActionListener, MouseListener {


			Button resignButton; 
			Button newGameButton; 

			Label message; 

			CheckersData board; 

			boolean gameInProgress;


			int currentPlayer;     
			int selectedRow, selectedCol;
			CheckersMove[] legalMoves;  
			private java.sql.ResultSetMetaData rsmd;



			public CheckersCanvas() {
				setBackground(Color.BLACK);
				addMouseListener(this);
				setFont(new  Font("Serif", Font.BOLD, 14));
				resignButton = new Button("Resign");
				resignButton.addActionListener(this);
				newGameButton = new Button("New Game");
				newGameButton.addActionListener(this);
				message = new Label("",Label.CENTER);
				board = new CheckersData();
				doNewGame();

			}


			public void actionPerformed(ActionEvent evt) {
				Object src = evt.getSource();
				if (src == newGameButton)
					doNewGame();
				else if (src == resignButton)
					doResign();
			}


			void doNewGame() {
				if (gameInProgress == true) {
					message.setText("Finish the current game first!");
					return;
				}
				board.setUpGame(); 
				currentPlayer = CheckersData.P1;  
				legalMoves = board.getLegalMoves(CheckersData.P1); 
				selectedRow = -1; 
				message.setText("Player-1:  Make your move.");
				gameInProgress = true;
				newGameButton.setEnabled(false);
				resignButton.setEnabled(true);
				repaint();
			}


			void doResign() {
				if (gameInProgress == false) {
					message.setText("There is no game in progress!");
					return;
				}
				if (currentPlayer == CheckersData.P1)
					gameOver("Player-1 resigns.  Player-2 wins.");
				else
					gameOver("Player-2 resigns.  Player-1 winds.");
			}


			void gameOver(String str) {
				message.setText(str);
				newGameButton.setEnabled(true);
				resignButton.setEnabled(false);
				gameInProgress = false;
			}


			void doClickSquare(int row, int col) throws NumberFormatException, SQLException {

				for (int i = 0; i < legalMoves.length; i++)
					if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
						selectedRow = row;
						selectedCol = col;
						if (currentPlayer == CheckersData.P1)
							message.setText("Player-1:  Make your move.");
						else
							message.setText("Player-2:  Make your move.");
						repaint();
						return;
					}


				if (selectedRow < 0) {
					message.setText("Click the piece you want to move.");
					return;
				}


				for (int i = 0; i < legalMoves.length; i++)
					if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
					&& legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
						doMakeMove(legalMoves[i]);
						return;
					}


				message.setText("Click the square you want to move to.");

			}  // end doClickSquare()


			void doMakeMove(CheckersMove move) throws NumberFormatException, SQLException {

				board.makeMove(move);


				if (move.isJump()) {
					legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
					if (legalMoves != null) {
						if (currentPlayer == CheckersData.P1)
							message.setText("Player-1:  You must continue jumping.");
						else
							message.setText("Player-2:  You must continue jumping.");
						selectedRow = move.toRow;
						selectedCol = move.toCol;
						repaint();
						return;
					}
				}


				if (currentPlayer == CheckersData.P1) {
					currentPlayer = CheckersData.P2;
					legalMoves = board.getLegalMoves(currentPlayer);
					if (legalMoves == null)
						gameOver("Player-2 has no moves.  Player-1 wins.");
							
					else if (legalMoves[0].isJump())
						message.setText("Player-2:  Make your move.  You must jump.");
					else
						message.setText("Player-2:  Make your move.");
				}
				else {
					currentPlayer = CheckersData.P1;
					legalMoves = board.getLegalMoves(currentPlayer);
					if (legalMoves == null)
						gameOver("Player-1 has no moves.  Player-2 wins.");
							
					else if (legalMoves[0].isJump())
						message.setText("Player-1:  Make your move.  You must jump.");
					else
						message.setText("Player-1:  Make your move.");
				}


				selectedRow = -1;

					
				if (legalMoves != null) {
					boolean sameStartSquare = true; //1 seule piece dispo
					for (int i = 1; i < legalMoves.length; i++)
						if (legalMoves[i].fromRow != legalMoves[0].fromRow
						|| legalMoves[i].fromCol != legalMoves[0].fromCol) {
							sameStartSquare = false;
							break;
						}
					if (sameStartSquare) {
						selectedRow = legalMoves[0].fromRow;
						selectedCol = legalMoves[0].fromCol;
					}
				}


				repaint();

			}  // end doMakeMove();


		


			



			public void update(Graphics g) {
				paint(g);
			}


			public void paint(Graphics g) {

				g.setColor(Color.BLACK);
				g.drawRect(0,0,getSize().width-1,getSize().height-1);
				g.drawRect(1,1,getSize().width-3,getSize().height-3);


				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if ( row % 2 == col % 2 )
							g.setColor(new Color(165,136,65));//beige
						else
							g.setColor(Color.white);
						g.fillRect(2 + col*60, 2 + row*60, 60, 60);
						switch (board.pieceAt(row,col)) {
						case CheckersData.P1:
							g.setColor(new Color(115,21,23));
							g.fillOval(4 + col*60, 4 + row*60, 56,56);
							break;
						case CheckersData.P2:
							g.setColor(Color.BLACK);
							g.fillOval(4 + col*60, 4 + row*60, 56, 56);
							break;
						case CheckersData.P1_KING:
							g.setColor(new Color(115,21,23));
							g.fillOval(4 + col*60, 4 + row*60, 56, 56);
							g.setColor(Color.white);
							g.drawString("K", 26 + col*60, 36 + row*60);
							break;
						case CheckersData.P2_KING:
							g.setColor(Color.BLACK);
							g.fillOval(4 + col*60, 4 + row*60, 56, 56);
							g.setColor(Color.white);
							g.drawString("K", 26 + col*60, 36 + row*60);

							break;
						}
					}
				}


				if (gameInProgress) {
					g.setColor(Color.cyan);
					for (int i = 0; i < legalMoves.length; i++) {
						g.drawRect(2 + legalMoves[i].fromCol*60, 2 + legalMoves[i].fromRow*60, 59, 59);
					}
					if (selectedRow >= 0) {
						g.setColor(Color.white);
						g.drawRect(2 + selectedCol*60, 2 + selectedRow*60, 59, 59);
						g.drawRect(3 + selectedCol*60, 3 + selectedRow*60, 57, 57);
						g.setColor(Color.BLACK);
						for (int i = 0; i < legalMoves.length; i++) {
							if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow)
								g.drawRect(2 + legalMoves[i].toCol*60, 2 + legalMoves[i].toRow*60, 59, 59);
						}
					}
				}
			}  // end paint()


			public Dimension getPreferredSize() {
				return new Dimension(480, 480);
			}


			public Dimension getMinimumSize() {
				return new Dimension(480, 480);
			}


			public void mousePressed(MouseEvent evt) {
				if (gameInProgress == false)
					message.setText("Click \"New Game\" to start a new game.");
				else {
					int col = (evt.getX() - 2) / 60;
					int row = (evt.getY() - 2) / 60;
					if (col >= 0 && col < 8 && row >= 0 && row < 8)
						try {
							doClickSquare(row,col);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}


			public void mouseReleased(MouseEvent evt) { }
			public void mouseClicked(MouseEvent evt) { }
			public void mouseEntered(MouseEvent evt) { }
			public void mouseExited(MouseEvent evt) { }


		}  // end class SimpleCheckerboardCanvas




		class CheckersMove {
			int fromRow, fromCol;  
			int toRow, toCol;   
			CheckersMove(int r1, int c1, int r2, int c2) {
				fromRow = r1;
				fromCol = c1;
				toRow = r2;
				toCol = c2;
			}
			boolean isJump() {
				return (fromRow - toRow == 2 || fromRow - toRow == -2);
			}
		}  // end class CheckersMove.




		class CheckersData {


			public static final int
			EMPTY = 0,
			P1 = 1,
			P1_KING = 2,
			P2 = 3,
			P2_KING = 4;

			private int[][] board; 


			public CheckersData() {
				board = new int[8][8];
				setUpGame();
			}

			public void setUpGame() {
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if ( row % 2 == col % 2 ) {
							if (row < 3)
								board[row][col] = P2;
							else if (row > 4)
								board[row][col] = P1;
							else
								board[row][col] = EMPTY;
						}
						else {
							board[row][col] = EMPTY;
						}
					}
				}
			}  // end setUpGame()


			public int pieceAt(int row, int col) {
				return board[row][col];
			}


			public void setPieceAt(int row, int col, int piece) {
				board[row][col] = piece;
			}


			public void makeMove(CheckersMove move) {
				makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
			}


			public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
				board[toRow][toCol] = board[fromRow][fromCol];
				board[fromRow][fromCol] = EMPTY;
				if (fromRow - toRow == 2 || fromRow - toRow == -2) {
					int jumpRow = (fromRow + toRow) / 2; 
					int jumpCol = (fromCol + toCol) / 2;  
					board[jumpRow][jumpCol] = EMPTY;
				}
				if (toRow == 0 && board[toRow][toCol] == P1)
					board[toRow][toCol] = P1_KING;
				if (toRow == 7 && board[toRow][toCol] == P2)
					board[toRow][toCol] = P2_KING;
			}


			public CheckersMove[] getLegalMoves(int player) {
				if (player != P1 && player != P2)
					return null;

				int playerKing;  
				if (player == P1)
					playerKing = P1_KING;
				else
					playerKing = P2_KING;

				Vector moves = new Vector(); 

				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (board[row][col] == player || board[row][col] == playerKing) {
							if (canJump(player, row, col, row+1, col+1, row+2, col+2))
								moves.addElement(new CheckersMove(row, col, row+2, col+2));
							if (canJump(player, row, col, row-1, col+1, row-2, col+2))
								moves.addElement(new CheckersMove(row, col, row-2, col+2));
							if (canJump(player, row, col, row+1, col-1, row+2, col-2))
								moves.addElement(new CheckersMove(row, col, row+2, col-2));
							if (canJump(player, row, col, row-1, col-1, row-2, col-2))
								moves.addElement(new CheckersMove(row, col, row-2, col-2));
						}
					}
				}

				if (moves.size() == 0) {
					for (int row = 0; row < 8; row++) {
						for (int col = 0; col < 8; col++) {
							if (board[row][col] == player || board[row][col] == playerKing) {
								if (canMove(player,row,col,row+1,col+1))
									moves.addElement(new CheckersMove(row,col,row+1,col+1));
								if (canMove(player,row,col,row-1,col+1))
									moves.addElement(new CheckersMove(row,col,row-1,col+1));
								if (canMove(player,row,col,row+1,col-1))
									moves.addElement(new CheckersMove(row,col,row+1,col-1));
								if (canMove(player,row,col,row-1,col-1))
									moves.addElement(new CheckersMove(row,col,row-1,col-1));
							}
						}
					}
				}
				if (moves.size() == 0)
					return null;
				else {
					CheckersMove[] moveArray = new CheckersMove[moves.size()];
					for (int i = 0; i < moves.size(); i++)
						moveArray[i] = (CheckersMove)moves.elementAt(i);
					return moveArray;
				}

			}  // end getLegalMoves


			public CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
				if (player != P1 && player != P2)
					return null;
				int playerKing; 
				if (player == P1)
					playerKing = P1_KING;
				else
					playerKing = P2_KING;
				Vector moves = new Vector();  
				if (board[row][col] == player || board[row][col] == playerKing) {
					if (canJump(player, row, col, row+1, col+1, row+2, col+2))
						moves.addElement(new CheckersMove(row, col, row+2, col+2));
					if (canJump(player, row, col, row-1, col+1, row-2, col+2))
						moves.addElement(new CheckersMove(row, col, row-2, col+2));
					if (canJump(player, row, col, row+1, col-1, row+2, col-2))
						moves.addElement(new CheckersMove(row, col, row+2, col-2));
					if (canJump(player, row, col, row-1, col-1, row-2, col-2))
						moves.addElement(new CheckersMove(row, col, row-2, col-2));
				}
				if (moves.size() == 0)
					return null;
				else {
					CheckersMove[] moveArray = new CheckersMove[moves.size()];
					for (int i = 0; i < moves.size(); i++)
						moveArray[i] = (CheckersMove)moves.elementAt(i);
					return moveArray;
				}
			}  // end getLegalMovesFrom()


			private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

				if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
					return false;  

				if (board[r3][c3] != EMPTY)
					return false; 

				if (player == P1) {
					if (board[r1][c1] == P1 && r3 > r1)
						return false; 
					if (board[r2][c2] != P2 && board[r2][c2] != P2_KING)
						return false; 
					return true;  
				}
				else {
					if (board[r1][c1] == P2 && r3 < r1)
						return false;
					if (board[r2][c2] != P1 && board[r2][c2] != P1_KING)
						return false; 
					return true; 
				}

			}  // end canJump()


			private boolean canMove(int player, int r1, int c1, int r2, int c2) {

				if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
					return false; 

				if (board[r2][c2] != EMPTY)
					return false;  

				if (player == P1) {
					if (board[r1][c1] == P1 && r2 > r1)
						return false; 
					return true; 
				}
				else {
					if (board[r1][c1] == P2 && r2 < r1)
						return false; 
					return true;
				}

			}  // end canMove()


		} // end class CheckersData




	}
