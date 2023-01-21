package pieces;
import board.Board;
import board.Square;

public class King extends Piece
{
	public King(char color, Square currentSquare)
	{
		super("King", color, currentSquare);
	}
	public boolean[][] determinePossibleMoves(Board b, boolean dkd)
	{
		boolean[][] result = new boolean[8][8];
		int r = getCurrentSquare().getRow();
		int c = getCurrentSquare().getCol();

		if(isInBounds(r - 1, c - 1) && (b.getSquare(r - 1, c - 1).isEmpty() == true || isEnemyPiece(b.getSquare(r - 1, c - 1).getPiece())))
		{
			result[r - 1][c - 1] = true;
		}
		if(isInBounds(r - 1, c) && (b.getSquare(r - 1, c).isEmpty() == true || isEnemyPiece(b.getSquare(r - 1, c).getPiece())))
		{
			result[r - 1][c] = true;
		}
		if(isInBounds(r - 1, c + 1) && (b.getSquare(r - 1, c + 1).isEmpty() == true || isEnemyPiece(b.getSquare(r - 1, c + 1).getPiece())))
		{
			result[r - 1][c + 1] = true;
		}
		if(isInBounds(r, c + 1) && (b.getSquare(r, c + 1).isEmpty() == true || isEnemyPiece(b.getSquare(r, c + 1).getPiece())))
		{
			result[r][c + 1] = true;
		}
		if(isInBounds(r + 1, c + 1) && (b.getSquare(r + 1, c + 1).isEmpty() == true || isEnemyPiece(b.getSquare(r + 1, c + 1).getPiece())))
		{
			result[r + 1][c + 1] = true;
		}
		if(isInBounds(r + 1, c) && (b.getSquare(r + 1, c).isEmpty() == true || isEnemyPiece(b.getSquare(r + 1, c).getPiece())))
		{
			result[r + 1][c] = true;
		}
		if(isInBounds(r + 1, c - 1) && (b.getSquare(r + 1, c - 1).isEmpty() == true || isEnemyPiece(b.getSquare(r + 1, c - 1).getPiece())))
		{
			result[r + 1][c - 1] = true;
		}
		if(isInBounds(r, c - 1) && (b.getSquare(r, c - 1).isEmpty() == true || isEnemyPiece(b.getSquare(r, c - 1).getPiece())))
		{
			result[r][c - 1] = true;
		}
		
		//castling
		if(getColor() == 'W')
		{
			//white castle left
			if(getTotalMoves() == 0 && b.getSquare(7, 0).getPiece() != null && b.getSquare(7, 0).getPiece().getTotalMoves() == 0 && b.getSquare(7, 1).getPiece() == null && b.getSquare(7,  2).getPiece() == null && b.getSquare(7, 3).getPiece() == null)	
			{
				result[7][2] = true;
			}
			//white castle right
			if(getTotalMoves() == 0 && b.getSquare(7, 7).getPiece() != null && b.getSquare(7, 7).getPiece().getTotalMoves() == 0 && b.getSquare(7, 5).getPiece() == null && b.getSquare(7, 6).getPiece() == null)
			{
				result[7][6] = true;
			}
			
		}
		else
		{
			if(getTotalMoves() == 1 && b.getSquare(7, 0).getPiece() != null && b.getSquare(7, 0).getPiece().getTotalMoves() == 0 && b.getSquare(7, 1).getPiece() == null && b.getSquare(7,  2).getPiece() == null)	
			{
				result[7][1] = true;
			}
			if(getTotalMoves() == 1 && b.getSquare(7, 7).getPiece() != null && b.getSquare(7, 7).getPiece().getTotalMoves() == 0 && b.getSquare(7, 4).getPiece() == null && b.getSquare(7,  5).getPiece() == null && b.getSquare(7, 6).getPiece() == null)
			{
				result[7][5] = true;
			}
		}
		
		return result;
	}
}
//TODO
//add checkmate behavior / cannot move king into check.