package board;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Board
{
	private Square[][] board;
	private boolean firstTime = true;
	private int turnTotal = 1;
	public void updateTurns() {
		turnTotal++;
	}
	public int getTurnTotal() {
		return turnTotal;
	}
	public Board()
	{
		board = new Square[8][8];
		
		
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(r == 1)
				{
					Square s = new Square(r, c, null);
					Pawn p = new Pawn('B', s);
					s.setPiece(p);
					board[r][c] = s;
				}
				else if(r == 6)
				{
					Square s = new Square(r, c, null);
					Pawn p = new Pawn('W', s);
					s.setPiece(p);
					board[r][c] = s;
				}
				else
				{
					board[r][c] = new Square(r, c, null);
				}
			}
		}
		Square s = new Square(0, 0, null);
		Rook r = new Rook ('B', s);
		s.setPiece(r);
		board[0][0] = s;
		
		Square a = new Square(0, 1, null);
		Knight b = new Knight ('B', a);
		a.setPiece(b);
		board[0][1] = a;
		
		Square c = new Square(0, 2, null);
		Bishop d = new Bishop ('B', c);
		c.setPiece(d);
		board[0][2] = c;
		
		Square e = new Square(0, 3, null);
		Queen f = new Queen ('B', e);
		e.setPiece(f);
		board[0][3] = e;
		
		Square g = new Square(0, 4, null);
		King h = new King ('B', g);
		g.setPiece(h);
		board[0][4] = g;
		
		Square i = new Square(0, 5, null);
		Bishop j = new Bishop ('B', i);
		i.setPiece(j);
		board[0][5] = i;
		
		Square k = new Square(0, 6, null);
		Knight l = new Knight ('B', k);
		k.setPiece(l);
		board[0][6] = k;
		
		Square m = new Square(0, 7, null);
		Rook n = new Rook ('B', m);
		m.setPiece(n);
		board[0][7] = m;		
		
		Square s1 = new Square(7, 0, null);
		Rook r1 = new Rook ('W', s1);
		s1.setPiece(r1);
		board[7][0] = s1;
		
		Square a1 = new Square(7, 1, null);
		Knight b1 = new Knight ('W', a1);
		a1.setPiece(b1);
		board[7][1] = a1;
		
		Square c1 = new Square(7, 2, null);
		Bishop d1 = new Bishop ('W', c1);
		c1.setPiece(d1);
		board[7][2] = c1;
		
		Square e1 = new Square(7, 3, null);
		Queen f1 = new Queen ('W', e1);
		e1.setPiece(f1);
		board[7][3] = e1;
		
		Square g1 = new Square(7, 4, null);
		King h1 = new King ('W', g1);
		g1.setPiece(h1);
		board[7][4] = g1;
		
		Square i1 = new Square(7, 5, null);
		Bishop j1 = new Bishop ('W', i1);
		i1.setPiece(j1);
		board[7][5] = i1;
		
		Square k1 = new Square(7, 6, null);
		Knight l1 = new Knight ('W', k1);
		k1.setPiece(l1);
		board[7][6] = k1;
		
		Square m1 = new Square(7, 7, null);
		Rook n1 = new Rook ('W', m1);
		m1.setPiece(n1);
		board[7][7] = m1;
	}
	public Square getSquare(int r, int c)
	{
		if(r >= 0 && r <= 7 && c >= 0 && c <= 7)
		{
			return board[r][c];
		}
		return null;
	}
	public void changeView()
	{
		Square[][] nBoard = new Square[8][8];
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				nBoard[r][c] = board[7 - r][7 - c];
				nBoard[r][c].setRow(7 - r);
				nBoard[r][c].setCol(7 - c);
			}
		}
		board = nBoard;
//		if(firstTime)
	//	{
		//	firstTime = false;
			Square[][] nnBoard = new Square[8][8];
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					nnBoard[r][c] = board[r][c];
					nnBoard[r][c].setRow(r);
					nnBoard[r][c].setCol(c);
				}
			}
			board = nnBoard;
	//	}
	}
}
