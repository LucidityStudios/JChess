package pieces;
import board.Board;
import board.Square;

public class Rook extends Piece
{
	public Rook(char color, Square currentSquare)
	{
		super("Rook", color, currentSquare);
	}
	public boolean[][] determinePossibleMoves(Board b, boolean dkd)
	{
		boolean[][] result = new boolean[8][8];
		int r = getCurrentSquare().getRow();
		int c = getCurrentSquare().getCol();
		
		int i = 1;
		// north
		while(isInBounds(r - i, c) == true && (b.getSquare(r - i, c).isEmpty() == true || (b.getSquare(r - i, c).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c).getPiece()))))
		{
			result[r - i][c] = true;
				
			if(b.getSquare(r - i, c).isEmpty() == false && isEnemyPiece(b.getSquare(r - i, c).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// south
		while(isInBounds(r + i, c) == true && (b.getSquare(r + i, c).isEmpty() == true || (b.getSquare(r + i, c).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c).getPiece()))))
		{
			result[r + i][c] = true;
			if(b.getSquare(r + i, c).isEmpty() == false && isEnemyPiece(b.getSquare(r + i, c).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// west
		while(isInBounds(r, c - i) == true && (b.getSquare(r, c - i).isEmpty() == true || (b.getSquare(r, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r, c - i).getPiece()))))
		{
			result[r][c - i] = true;
			if(b.getSquare(r, c - i).isEmpty() == false && isEnemyPiece(b.getSquare(r, c - i).getPiece()))
			{
				break;
			}
			i ++;
		}
		i = 1;
		// east
		while(isInBounds(r, c + i) == true && (b.getSquare(r, c + i).isEmpty() == true || (b.getSquare(r, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r, c + i).getPiece()))))
		{
			result[r][c + i] = true;
			if(b.getSquare(r, c + i).isEmpty() == false && isEnemyPiece(b.getSquare(r, c + i).getPiece()))
			{
				break;
			}
			i ++;
		}
		return result;
	}
}
