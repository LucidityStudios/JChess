package pieces;
import board.Board;
import board.Square;

public class Queen extends Piece
{
	public Queen(char color, Square currentSquare)
	{
		super("Queen", color, currentSquare);
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
		i = 1;
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