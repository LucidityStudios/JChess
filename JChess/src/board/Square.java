package board;
import pieces.Piece;

public class Square
{
	private int row, col;
	private Piece piece;
	private char color;
	
	public Square(int myRow, int myCol, Piece myPiece)
	{
		row = myRow;
		col = myCol;
		piece = myPiece;
		
		if(row + col % 2 == 0)
		{
			color = 'W';
		}
		else
		{
			color = 'B';
		}
	}
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public Piece getPiece()
	{
		return piece;
	}
	public void setRow(int nRow)
	{
		row = nRow;
	}
	public void setCol(int nCol)
	{
		col = nCol;
	}
	public void setPiece(Piece nPiece)
	{
		piece = nPiece;
	}
	public boolean isEmpty()
	{
		if(piece == null)
		{
			return true;
		}
		return false;
	}
	
}
