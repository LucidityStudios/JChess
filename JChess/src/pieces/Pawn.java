package pieces;
import board.Board;
import board.Square;

public class Pawn extends Piece
{
	public Pawn(char color, Square currentSquare)
	{
		super("Pawn", color, currentSquare);
	}
	public boolean[][]determinePossibleMoves(Board b, boolean isNotPawn)
	{		
		boolean[][] result = new boolean[8][8];			
		int r = getCurrentSquare().getRow();
		int c = getCurrentSquare().getCol();
		
		if(isNotPawn) {
		// can move 1 ahead
		if(this.isInBounds(r-1, c) && b.getSquare(r - 1, c).isEmpty())
		{
			result[r - 1][c] = true;
			// can move 2 ahead
			if((r == 6) && b.getSquare(4, c).isEmpty() && this.getTotalMoves() == 0)
			{
				result[4][c] = true;
			}
		}
		// can attack diagonally right
		if(isInBounds(r - 1, c + 1) == true && b.getSquare(r - 1, c + 1).isEmpty() == false && isEnemyPiece(b.getSquare(r - 1, c + 1).getPiece()))
		{
			result[r - 1][c + 1] = true;
		}
		// can attack diagonally left
		if(isInBounds(r - 1, c - 1) == true && b.getSquare(r - 1, c - 1).isEmpty() == false && isEnemyPiece(b.getSquare(r - 1, c - 1).getPiece()))
		{
			result[r - 1][c - 1] = true;
		}
		//can ep
		//if an enemy pawn is to my left who is EPable
		if(isInBounds(r, c-1) && b.getSquare(r, c-1) != null && b.getSquare(r, c-1).getPiece() != null && b.getSquare(r, c-1).getPiece().getColor() != this.getColor() && b.getSquare(r, c-1).getPiece().getEp()) {
			result[r - 1][c - 1] = true;
		}
		if(isInBounds(r, c+1) && b.getSquare(r, c+1) != null && b.getSquare(r, c+1).getPiece() != null && b.getSquare(r, c+1).getPiece().getColor() != this.getColor() && b.getSquare(r, c+1).getPiece().getEp()) {
			result[r - 1][c + 1] = true;
		}
		}
		else {
			// can attack diagonally right
			if(isInBounds(r + 1, c + 1) == true && b.getSquare(r + 1, c + 1).isEmpty() == false && isEnemyPiece(b.getSquare(r + 1, c + 1).getPiece()))
			{
				result[r + 1][c + 1] = true;
			}
			// can attack diagonally left
			if(isInBounds(r + 1, c - 1) == true && b.getSquare(r + 1, c - 1).isEmpty() == false && isEnemyPiece(b.getSquare(r + 1, c - 1).getPiece()))
			{
				result[r + 1][c - 1] = true;
			}
		}
		return result;
	}
}
// TODO
// when pawn reaches other side of board