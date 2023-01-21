package pieces;
import board.Board;
import board.Square;

public abstract class Piece
{
	private Square currentSquare;
	private char color;
	private String type;
	private int totalMoves;
	public boolean castle;
	public boolean ep;

	public Piece(String myType, char myColor, Square myCurrentSquare)
	{
		ep = false;
		type = myType;
		color = myColor;
		currentSquare = myCurrentSquare;
		totalMoves = 0;
	}
	public boolean getEp() {
		return ep;
	}
	public void updateEp(boolean b) {
		ep = b;
	}
	public Square getCurrentSquare()
	{
		return currentSquare;
	}
	public char getColor()
	{
		return color;
	}
	public char getEnemyColor()
	{
		if(color == 'W')
		{
			return 'B';
		}
		return 'W';
	}
	public String getType()
	{
		return type;
	}
	public void setType(String mType)
	{
		type = mType;
	}
	public void setCurrentSquare(Square nSquare)
	{
		currentSquare = nSquare;
	}
	public Piece makeMove(int row, int col, Board b)
	{
		Piece result = b.getSquare(row, col).getPiece();
		
		b.getSquare(row, col).setPiece(this);
		getCurrentSquare().setPiece(null);
		setCurrentSquare(b.getSquare(row, col));
		
		return result;
	}
	public boolean isInBounds(int r, int c)
	{
		if(r <= 7 && r >= 0 && c <= 7 && c >= 0)
		{
			return true;
		}
		return false;
	}
	public boolean isEnemyPiece(Piece p)
	{
		if(color != p.getColor())
		{
			return true;
		}
		return false;
	}
	public int getTotalMoves()
	{
		return totalMoves;
	}
	public void updateTotalMoves()
	{
		totalMoves++;
	//	System.out.println("Total Moves Updated");
	}
	public void justCastled()
	{
		castle = true;
	}
	public boolean hasCastled()
	{
		return castle;
	}
	public abstract boolean[][] determinePossibleMoves(Board b, boolean isNotPawn);
}
