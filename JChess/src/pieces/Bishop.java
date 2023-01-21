package pieces;
import board.Board;
import board.Square;

public class Bishop extends Piece
{
	public Bishop(char color, Square currentSquare)
	{
		super("Bishop", color, currentSquare);		
	}
	
	public boolean[][] determinePossibleMoves(Board b, boolean dkd)
	{
		boolean[][] result = new boolean[8][8];
		int r = getCurrentSquare().getRow();
		int c = getCurrentSquare().getCol();
		
		int i = 1;
		// up left
		while(isInBounds(r - i, c - i) == true && (b.getSquare(r - i, c - i).isEmpty() == true || (b.getSquare(r - i, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c - i).getPiece()))))
		{
			result[r - i][c - i] = true;
				
			if(b.getSquare(r - i, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c - i).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// up right
		while(isInBounds(r - i, c + i) == true && (b.getSquare(r - i, c + i).isEmpty() == true || (b.getSquare(r - i, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c + i).getPiece()))))
		{
			result[r - i][c + i] = true;
			if(b.getSquare(r - i, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c + i).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// down left
		while(isInBounds(r + i, c - i) == true && (b.getSquare(r + i, c - i).isEmpty() == true || (b.getSquare(r + i, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c - i).getPiece()))))
		{
			result[r + i][c - i] = true;
			if(b.getSquare(r + i, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c - i).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// down right
		while(isInBounds(r + i, c + i) == true && (b.getSquare(r + i, c + i).isEmpty() == true || (b.getSquare(r + i, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c + i).getPiece()))))
		{
			result[r + i][c + i] = true;
			if(b.getSquare(r + i, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c + i).getPiece()))
			{
				break;
			}
			i ++;
		}
		return result;
	}
}
