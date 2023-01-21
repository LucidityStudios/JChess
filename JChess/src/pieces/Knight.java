package pieces;
import board.Board;
import board.Square;

public class Knight extends Piece
{
	public Knight(char color, Square currentSquare)
	{
		super("Knight", color, currentSquare);
	}
	public boolean[][] determinePossibleMoves(Board b, boolean dkd)
	{
		boolean[][] result = new boolean[8][8];
		int r = getCurrentSquare().getRow();
		int c = getCurrentSquare().getCol();
		
		if(isInBounds(r - 1, c - 2) == true && (b.getSquare(r - 1, c - 2).isEmpty() == true || isEnemyPiece(b.getSquare(r - 1, c - 2).getPiece())))
		{
			result[r - 1][c - 2] = true;
		}	
		if(isInBounds(r - 2, c - 1) == true && (b.getSquare(r - 2, c - 1).isEmpty() == true || isEnemyPiece(b.getSquare(r - 2, c - 1).getPiece())))
		{
			result[r - 2][c - 1] = true;
		}		
		if(isInBounds(r - 2, c + 1) == true && (b.getSquare(r - 2, c + 1).isEmpty() == true || isEnemyPiece(b.getSquare(r - 2, c + 1).getPiece())))
		{
			result[r - 2][c + 1] = true;
		}		
		if(isInBounds(r - 1, c + 2) == true && (b.getSquare(r - 1, c + 2).isEmpty() == true || isEnemyPiece(b.getSquare(r - 1, c + 2).getPiece())))
		{
			result[r - 1][c + 2] = true;
		}		
		if(isInBounds(r + 1, c - 2) == true && (b.getSquare(r + 1, c - 2).isEmpty() == true || isEnemyPiece(b.getSquare(r + 1, c - 2).getPiece())))
		{
			result[r + 1][c - 2] = true;
		}		
		if(isInBounds(r + 2, c - 1) == true && (b.getSquare(r + 2, c - 1).isEmpty() == true || isEnemyPiece(b.getSquare(r + 2, c - 1).getPiece())))
		{
			result[r + 2][c - 1] = true;
		}			
		if(isInBounds(r + 2, c + 1) == true && (b.getSquare(r + 2, c + 1).isEmpty() == true || isEnemyPiece(b.getSquare(r + 2, c + 1).getPiece())))
		{
			result[r + 2][c + 1] = true;
		}
		if(isInBounds(r + 1, c + 2) == true && (b.getSquare(r + 1, c + 2).isEmpty() == true || isEnemyPiece(b.getSquare(r + 1, c + 2).getPiece())))	
		{
			result[r + 1][c + 2] = true;
		}
		return result;
	}
}
