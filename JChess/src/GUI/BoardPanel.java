package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import board.Board;
import board.Square;
import manager.Manager;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class BoardPanel extends JPanel implements MouseListener
{

	private static final long serialVersionUID = 1L;
	private Manager manager;
	private Piece selectedPiece;
	private Piece previousPiece;
	private final String[] PIECE_TYPES = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
	private final String[] PROMOTIONS = {"Rook", "Knight", "Bishop", "Queen"};
	private final String[] BLACK_IMGS = {"res/bp.png", "res/br.png", "res/bn.png", "res/bb.png", "res/bq.png", "res/bk.png"};
	private final String[] WHITE_IMGS = {"res/wp.png", "res/wr.png", "res/wn.png", "res/wb.png", "res/wq.png", "res/wk.png"};
	private boolean isInCheck = false;
	private boolean[][] checkTest = new boolean[8][8];
	private int rKing = 9, cKing = 9, rcKing = 9, ccKing = 9;
	private char currentCol = 'W';
	
	public BoardPanel()
	{
		setPreferredSize(new Dimension(400, 400));
		addMouseListener(this);
		manager = new Manager();
		selectedPiece = null;
	}
	
	public void paintComponent(Graphics g)
	{
		//adds the background image
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		File boardImgFile = new File("res/chessboard.png");
		BufferedImage boardImg = null;
		try
		{
			boardImg = ImageIO.read(boardImgFile);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		g2.drawImage(boardImg, 0, 0, null);
//		System.out.println("Drawing Pieces");
		drawPieces(g2);
	}
	
	private void drawPieces(Graphics2D g2)
	{
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				Square currSqr = manager.getBoard().getSquare(r, c);
				if(currSqr.getPiece() != null)
				{
					Piece p = currSqr.getPiece();
					if (p != null)
					{
						for(int i = 0; i < PIECE_TYPES.length; i++)
						{
							if(p.getType().equals(PIECE_TYPES[i]))
							{
								File pieceImgFile = null;
								if(p.getColor() == 'W')
								{
									pieceImgFile = new File(WHITE_IMGS[i]);
								}
								else
								{
									pieceImgFile = new File(BLACK_IMGS[i]);
								}
								BufferedImage pieceImg = null;
								try
								{
									pieceImg = ImageIO.read(pieceImgFile);
								}
								catch (IOException e)
								{
									e.printStackTrace();
								}
								Image scaledImg = pieceImg.getScaledInstance(45, 45, 0);
								g2.drawImage(scaledImg, 25 + currSqr.getCol() * 45, 25 + currSqr.getRow() * 45, null);
							}
							if(p == selectedPiece)
							{
								g2.setColor(Color.YELLOW);
								g2.drawRect(25 + currSqr.getCol() * 45, 25 + currSqr.getRow() * 45, 45, 45);
								g2.setColor(Color.GREEN);
								boolean[][] possibleMoves = p.determinePossibleMoves(manager.getBoard(), true);
								for(int row = 0; row < 8; row++)
								{
									for(int col = 0; col < 8; col++)
									{
										if(possibleMoves[row][col])   
										{
											g2.drawRect(25 + col * 45, 25 + row * 45, 45, 45);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
		
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		// Determine the row and column based on the x and y coordinates
		int col = (x - 25) / 45;
		int row = (y - 25) / 45;
		// Should be a valid set of coordinates, but if not, don't do anything
	if (col >= 0 && col < 8 && row >= 0 && row < 8)
	{
			// If so, figure out what piece was clicked
			Piece newPiece = manager.getBoard().getSquare(row, col).getPiece();
			// If a valid piece was selected and no piece is currently selected, make this
			// new piece the selected piece
			if(newPiece != null && selectedPiece == null && currentCol == newPiece.getColor())
			{
				selectedPiece = newPiece;
			}
			// If the selected piece is clicked again, then deselect it
			else if (newPiece == selectedPiece)
			{
				selectedPiece = null;
			}
			previousPiece = selectedPiece;
						
			// Otherwise, check and see if it was a valid move
	
		if(isInCheck && selectedPiece != null && selectedPiece != newPiece && selectedPiece.getColor() == currentCol)
		{	
			char currentColor = selectedPiece.getColor();
			 // could make checktest a possiblemoves boolean with the selected piece moved
			
		//checkmate testing
			//for all possible moves of checked color
			boolean isCheckmate = true;
			for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 8; c++) {
					//find piece of selected color
					if(manager.getBoard().getSquare(r, c).getPiece() != null && manager.getBoard().getSquare(r, c).getPiece().getColor() == currentColor) {
						//determine piece possible moves
						//for all possible moves of that piece
						boolean[][] pM = manager.getBoard().getSquare(r, c).getPiece().determinePossibleMoves(manager.getBoard(), true);
						for(int ro = 0; ro < 8; ro++) {
							for(int co = 0; co < 8; co++) {
								//make temporary move
								if(pM[ro][co]) {
									//pastR = r, //pastC = c
									Piece t = manager.getBoard().getSquare(r, c).getPiece().makeMove(ro, co, manager.getBoard());
									
									//find king location 
									for(int kr = 0; kr < 8; kr++)
									{
										for(int kc = 0; kc < 8; kc++)
										{
											if((manager.getBoard().getSquare(kr, kc).getPiece() != null) && (manager.getBoard().getSquare(kr, kc).getPiece().getType().equals("King")) && (manager.getBoard().getSquare(kr, kc).getPiece().getColor() == currentColor))
											{
												rcKing = kr;
												ccKing = kc;
											}
										}
									}
									//see if it is in enemy possible moves
									//if the king is not in enemy possible moves, break, not checkmate
									//RESET TEMP MOVE & CHECKMATETEST
									boolean[][] checkMateTest = new boolean[8][8];
									for(int a = 0; a < 8; a++){
										for(int b = 0; b < 8; b++){
											if((manager.getBoard().getSquare(a, b).getPiece() != null) && manager.getBoard().getSquare(a, b).getPiece().getColor() != currentColor){	
												if(manager.getBoard().getSquare(a, b).getPiece().getType().equals("Pawn")){
													for(int d = 0; d < 8; d++){
														for(int f = 0; f < 8; f++){
															if(manager.getBoard().getSquare(a, b).getPiece() != null && manager.getBoard().getSquare(a, b).getPiece().determinePossibleMoves(manager.getBoard(), false)[d][f]){
																checkMateTest[d][f] = true;
															}							
														}
													}
												}
												else {
												for(int d = 0; d < 8; d++){
													for(int f = 0; f < 8; f++){
														if(manager.getBoard().getSquare(a, b).getPiece() != null && manager.getBoard().getSquare(a, b).getPiece().determinePossibleMoves(manager.getBoard(), true)[d][f]){
															checkMateTest[d][f] = true;
														}							
													}
												}
												}
											}
										}
									}
									//if the king is still in checkmate
									if(!checkMateTest[rcKing][ccKing])
										isCheckmate = false;
									manager.getBoard().getSquare(ro, co).getPiece().makeMove(r, c, manager.getBoard());
									if(t != null) manager.getBoard().getSquare(ro, co).setPiece(t);									
									
								}
							}
						}
					}
					
				}
			}
			if(isCheckmate) {
				if(currentColor == 'W') {
					JOptionPane.showMessageDialog(null, "Black wins! Checkmate!");
				}
				else JOptionPane.showMessageDialog(null, "White wins! Checkmate!");
			}			
			
			int pastR = selectedPiece.getCurrentSquare().getRow();
			int pastC = selectedPiece.getCurrentSquare().getCol();
			boolean[][] possibleMoves = selectedPiece.determinePossibleMoves(manager.getBoard(), true);
			if(possibleMoves[row][col]) {
				Piece taken = selectedPiece.makeMove(row, col, manager.getBoard());
			
			//creates possible moves array for entire enemy side
			for(int r = 0; r < 8; r++){
				for(int c = 0; c < 8; c++){
					if((manager.getBoard().getSquare(r, c).getPiece() != null) && manager.getBoard().getSquare(r, c).getPiece().getColor() != currentColor){	
						if(manager.getBoard().getSquare(r, c).getPiece().getType().equals("Pawn")){
							for(int ro = 0; ro < 8; ro++){
								for(int co = 0; co < 8; co++){
									if(manager.getBoard().getSquare(r, c).getPiece() != null && manager.getBoard().getSquare(r, c).getPiece().determinePossibleMoves(manager.getBoard(), false)[ro][co]){
										checkTest[ro][co] = true;
									}							
								}
							}
						}
						else {
						for(int ro = 0; ro < 8; ro++){
							for(int co = 0; co < 8; co++){
								if(manager.getBoard().getSquare(r, c).getPiece() != null && manager.getBoard().getSquare(r, c).getPiece().determinePossibleMoves(manager.getBoard(), true)[ro][co]){
									checkTest[ro][co] = true;
								}							
							}
						}
						}
					}
				}
			}
			//gets king location
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					if((manager.getBoard().getSquare(r, c).getPiece() != null) && (manager.getBoard().getSquare(r, c).getPiece().getType().equals("King")) && (manager.getBoard().getSquare(r, c).getPiece().getColor() == currentColor))
					{
						rKing = r;
						cKing = c;
						break;
					}
				}
			}
			if(checkTest[rKing][cKing])
			{
				selectedPiece.makeMove(pastR, pastC, manager.getBoard());
				if(taken != null) 
					taken.makeMove(row, col, manager.getBoard());
			}
			//brings it out of check
			else
			{
				isInCheck = false;
				selectedPiece.updateTotalMoves();
				selectedPiece = null;
				if(currentColor == 'B') {
					manager.getBoard().updateTurns();
					currentCol = 'W';
				}
				else { currentCol = 'B'; }
				manager.getBoard().changeView();
				
			}
			//reset checkTest()
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					checkTest[i][j] = false;
				}
			}
			}
		}
		else if(selectedPiece != null && !isInCheck)
		{
			char currentColor = selectedPiece.getColor();
			boolean[][] possibleMoves = selectedPiece.determinePossibleMoves(manager.getBoard(), true);
			boolean isValidMove = true;
			int PastRR = selectedPiece.getCurrentSquare().getRow();
			int PastCC = selectedPiece.getCurrentSquare().getCol();
			Piece tooken = selectedPiece.makeMove(row, col, manager.getBoard());
			boolean[][] noMoveIntoCheck = new boolean[8][8];
			for(int r = 0; r < 8; r++){
				for(int c = 0; c < 8; c++){
					if((manager.getBoard().getSquare(r, c).getPiece() != null) && manager.getBoard().getSquare(r, c).getPiece().getColor() != currentColor){	
						if(manager.getBoard().getSquare(r, c).getPiece().getType().equals("Pawn")){
							for(int ro = 0; ro < 8; ro++){
								for(int co = 0; co < 8; co++){
									if(manager.getBoard().getSquare(r, c).getPiece() != null && manager.getBoard().getSquare(r, c).getPiece().determinePossibleMoves(manager.getBoard(), false)[ro][co]){
										noMoveIntoCheck[ro][co] = true;
									}							
								}
							}
						}
						else {
						for(int ro = 0; ro < 8; ro++){
							for(int co = 0; co < 8; co++){
								if(manager.getBoard().getSquare(r, c).getPiece() != null && manager.getBoard().getSquare(r, c).getPiece().determinePossibleMoves(manager.getBoard(), true)[ro][co]){
									noMoveIntoCheck[ro][co] = true;
								}							
							}
						}
						}
					}
				}
			}
			//gets king location
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					if((manager.getBoard().getSquare(r, c).getPiece() != null) && (manager.getBoard().getSquare(r, c).getPiece().getType().equals("King")) && (manager.getBoard().getSquare(r, c).getPiece().getColor() == currentColor))
					{
						rKing = r;
						cKing = c;
						break;
					}
				}
			}
			if(noMoveIntoCheck[rKing][cKing])
				isValidMove = false;
			selectedPiece.makeMove(PastRR, PastCC, manager.getBoard());
			manager.getBoard().getSquare(row, col).setPiece(tooken);
			
			if(possibleMoves[row][col] && isValidMove)
			{
				//updating ep bookkeeping
				if(selectedPiece.getType().equals("Pawn") && selectedPiece.getCurrentSquare().getRow() - row == 2)
					selectedPiece.getCurrentSquare().getPiece().updateEp(true);
				
				//removing en passant piece
				if((selectedPiece.getType().equals("Pawn")) && (selectedPiece.isInBounds(row, col)) &&
				(possibleMoves[row][col]) && (manager.getBoard().getSquare(row, col).isEmpty()) &&
				(Math.abs(row - selectedPiece.getCurrentSquare().getRow()) == 1) &&
				(Math.abs(col - selectedPiece.getCurrentSquare().getCol()) == 1) &&
				(selectedPiece.isInBounds(selectedPiece.getCurrentSquare().getRow()-1, selectedPiece.getCurrentSquare().getCol()))) {
					//leftside
					if((manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()-1).getPiece() != null) && 
					manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()-1).getPiece().getEp()) {
						manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()-1).setPiece(null);		
					//rightside
					}
					else if((manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()+1).getPiece() != null) && 
					manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()+1).getPiece().getEp()) {
						manager.getBoard().getSquare(selectedPiece.getCurrentSquare().getRow(), selectedPiece.getCurrentSquare().getCol()+1).setPiece(null);
					}
				}
												
				Piece taken = selectedPiece.makeMove(row, col, manager.getBoard());
				selectedPiece.updateTotalMoves();
				
				//Castling
				if(selectedPiece.getType().equals("King") && selectedPiece.getColor() == 'W' && selectedPiece.getTotalMoves() == 1)
				{
					//rook to the left
					if(col == 2)
					{	
						manager.getBoard().getSquare(7, 0).getPiece().makeMove(7, 3, manager.getBoard());
					}
					//rook to the right
					if(col == 6)
					{
						manager.getBoard().getSquare(7, 7).getPiece().makeMove(7, 5, manager.getBoard());
					}
				}
				else if(selectedPiece.getType().equals("King") && selectedPiece.getColor() == 'B' && selectedPiece.getTotalMoves() == 1)
				{
					//rook to the left
					if(col == 1)
					{
						manager.getBoard().getSquare(7, 0).getPiece().makeMove(7, 2, manager.getBoard());
					}
					//rook to the right
					if(col == 5)
					{
						manager.getBoard().getSquare(7, 7).getPiece().makeMove(7, 4, manager.getBoard());
					}
				}
				
				//finds enemy king location
				for(int r = 0; r < 8; r++) {
					for(int c = 0; c < 8; c++) {
						if((manager.getBoard().getSquare(r, c).getPiece() != null) && (manager.getBoard().getSquare(r, c).getPiece().getColor() != currentColor)) {
							if(manager.getBoard().getSquare(r, c).getPiece().getType().equals("King")) {
								rKing = r;
								cKing = c;
								for(int ro = 0; ro < 8; ro++) {
									for(int co = 0; co < 8; co++) {
										if((manager.getBoard().getSquare(ro, co).getPiece() != null) && (manager.getBoard().getSquare(ro, co).getPiece().getColor() == currentColor)){
											if(manager.getBoard().getSquare(ro, co).getPiece().determinePossibleMoves(manager.getBoard(), true)[rKing][cKing]){
												System.out.println("Is in check");
												isInCheck = true;
											}
										}
									}
								}
							}
						}
					}
				}
								
				selectedPiece = null;
//				System.out.println("Changing View");

				manager.getBoard().changeView();
				if(currentCol == 'W') currentCol = 'B';
				else currentCol = 'W';
			}
		}
		if(selectedPiece != null) {
//		System.out.println("Repainting");
		char epCol = 'W';
		char currentColor = selectedPiece.getColor();
		if(currentColor == 'B') {
			epCol = 'B';
		}
		//clear all ep's of color epCol
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(manager.getBoard().getSquare(i,j).getPiece() != null && manager.getBoard().getSquare(i, j).getPiece().ep && manager.getBoard().getSquare(i, j).getPiece().getColor() == epCol) {
					manager.getBoard().getSquare(i, j).getPiece().updateEp(false);
				}
			}
		}
		}

		if(previousPiece != null && previousPiece.getType().equals("Pawn") && previousPiece.getCurrentSquare().getRow() == 7 && row == 0) {
			
			JPanel panel = new JPanel(new GridLayout(2, 1)); // layout sets combobox under label
			JLabel label = new JLabel("Choose your Promotion!");
			JComboBox selection = new JComboBox(PROMOTIONS);
			panel.add(label);
			panel.add(selection);
			String[] options = {"OK"};
			JOptionPane.showOptionDialog(null, panel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			int answer = selection.getSelectedIndex();
			
			if(answer == 0)
				previousPiece.getCurrentSquare().setPiece(new Rook(previousPiece.getColor(), previousPiece.getCurrentSquare()));
			else if(answer == 1)
				previousPiece.getCurrentSquare().setPiece(new Knight(previousPiece.getColor(), previousPiece.getCurrentSquare()));
			else if(answer == 2)
				previousPiece.getCurrentSquare().setPiece(new Bishop(previousPiece.getColor(), previousPiece.getCurrentSquare()));
			else if(answer == 3)
				previousPiece.getCurrentSquare().setPiece(new Queen(previousPiece.getColor(), previousPiece.getCurrentSquare()));

			for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 8; c++) {
					if((manager.getBoard().getSquare(r, c).getPiece() != null) && (manager.getBoard().getSquare(r, c).getPiece().getColor() != previousPiece.getColor())) {
						if(manager.getBoard().getSquare(r, c).getPiece().getType().equals("King")) {
							rKing = r;
							cKing = c;
							for(int ro = 0; ro < 8; ro++) {
								for(int co = 0; co < 8; co++) {
									if((manager.getBoard().getSquare(ro, co).getPiece() != null) && (manager.getBoard().getSquare(ro, co).getPiece().getColor() == previousPiece.getColor())){
										if(manager.getBoard().getSquare(ro, co).getPiece().determinePossibleMoves(manager.getBoard(), true)[rKing][cKing]){
											System.out.println("Is in check");
											isInCheck = true;
										}
									}
								}
							}
						}
					}
				}
			}

		}
		
		
		
		repaint();
	}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	
	@Override
	public void mouseExited(MouseEvent arg0) {}
}
