import java.util.Random;

public class _2048
{
	private final int rows = 4;
	private final int cols = 4;
	private int[][] board;
	private int[][] previousBoard;
	private int score;
	private int previousScore;
	
	/**
	 * Initializes board and previousBoard using rows and cols.
	 * Uses the generateTile method to add two random tiles to board.
	 */
	public _2048()
	{
		board = new int[rows][cols];
		previousBoard = new int[rows][cols];
		generateTile();
		generateTile();
	}
	
	/**
	 * Initializes the board of this object using the specified board.
	 * Initializes previousBoard using rows and cols.
	 * 
	 * Precondition: the specified board is a 4x4 2D Array.
	 * 
	 * @param board
	 */
	public _2048(int[][] board)
	{
		this.board = board;
		previousBoard = new int[rows][cols];
	}
	
	/**
	 * Generates a tile and add it to an empty spot on the board.
	 * 80% chance to generate a 2
	 * 20% chance to generate a 4
	 * 
	 * Does nothing if the board is full.
	 */
	private void generateTile()
	{
		// picking a number to generate
		Random randy = new Random();
		int odds = randy.nextInt(10);
		int number;
		if (odds > 7)
			number = 4;
		else
			number = 2;
		// finding coordinates to generate
		int row = randy.nextInt(rows);
		int col = randy.nextInt(cols);
		while (board[row][col] != 0)
		{
			row = randy.nextInt(rows);
			col = randy.nextInt(cols);
		}
		board[row][col] = number;
	}
	
	/**
	 * Returns false if the board contains a 0, true otherwise.
	 * @return
	 */
	private boolean full()
	{
		boolean full = true;
		for (int[] row : board)
		{
			for (int element : row)
			{
				// if literally a single element is zero that means that is not full
				if (element == 0)
					full = false;
			}
		}
		return full;
	}
	
	/**
	 * Returns the board.
	 * @return
	 */
	public int[][] getBoard()
	{
		return board;
	}
	
	/**
	 * Returns the score.
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Saves board into previousBoard and score into previousScore
	 * then performs a move based on the specified direction:
	 * 
	 * Valid directions (not case sensitive):
	 *  up
	 *  down
	 *  left
	 *  right
	 *  
	 * Adds a new tile to the board using the generateTile method.
	 * 
	 * @param direction
	 */
	public void move(String direction)
	{
		System.out.println("line 119: " + previousBoard[3][0]);
		previousBoard = board;
		System.out.println("line 121: " + previousBoard[3][0]);
		previousScore = score;
		// TODO fix it so that case 4 and 5 work, fun issue where there is no gap when it checks but one is created afterwards
		// potentially merge everything and then move?
		// MOVE EVERYTHING UNTIL IT IS NEXT TO SOMETHING AND THEN MERGE, then move
		switch (direction.toLowerCase())
		{
			case ("up"):
			{
				moveUp();
				break;
			}
			case ("down"):
			{
				moveDown();
				break;
			}
			case ("left"):
			{
				moveLeft();
				break;
			}
			case ("right"):
			{
				moveRight();
				break;
			}
		}
		System.out.println("line 149: " + previousBoard[3][0]);
		generateTile();
		System.out.println("line 151: " + previousBoard[3][0]);
	}

	/**
	 * Shifts all the tiles up, combines like tiles that collide.
	 */
	private void moveUp()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int rowIterator = board.length - 1; rowIterator > 0; rowIterator--) // will always make room for one under
			{
				for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
				{
					if (board[rowIterator - 1][colIterator] == 0)
					{
						board[rowIterator - 1][colIterator] = board[rowIterator][colIterator];
						board[rowIterator][colIterator] = 0;
					}
				}
			}
		}
		for (int rowIterator = board.length - 1; rowIterator > 0; rowIterator--) // will always make room for one under
		{
			for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
			{
				if (board[rowIterator - 1][colIterator] == board[rowIterator][colIterator])
				{
					score += board[rowIterator - 1][colIterator] * 2;
					board[rowIterator - 1][colIterator] *= 2;
					board[rowIterator][colIterator] = 0;
				}
			}
		}
		for (int rowIterator = board.length - 1; rowIterator > 0; rowIterator--) // will always make room for one under
		{
			for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
			{
				if (board[rowIterator - 1][colIterator] == 0)
				{
					board[rowIterator - 1][colIterator] = board[rowIterator][colIterator];
					board[rowIterator][colIterator] = 0;
				}
			}
		}
	}
	
	/**
	 * Shifts all the tiles down, combines like tiles that collide.
	 */
	private void moveDown()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int rowIterator = 0; rowIterator < board.length - 1; rowIterator++) // will always make room for one over
			{
				for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
				{
					if (board[rowIterator + 1][colIterator] == 0)
					{
						board[rowIterator + 1][colIterator] = board[rowIterator][colIterator];
						board[rowIterator][colIterator] = 0;
					}
				}
			}
		}
		for (int rowIterator = 0; rowIterator < board.length - 1; rowIterator++) // will always make room for one over
		{
			for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
			{
				if (board[rowIterator + 1][colIterator] == board[rowIterator][colIterator])
				{
					score += board[rowIterator + 1][colIterator] * 2;
					board[rowIterator + 1][colIterator] *= 2;
					board[rowIterator][colIterator] = 0;
				}
			}
		}
		for (int rowIterator = 0; rowIterator < board.length - 1; rowIterator++) // will always make room for one over
		{
			for (int colIterator = 0; colIterator < board[rowIterator].length; colIterator++)
			{
				if (board[rowIterator + 1][colIterator] == 0)
				{
					board[rowIterator + 1][colIterator] = board[rowIterator][colIterator];
					board[rowIterator][colIterator] = 0;
				}
			}
		}
	}
	
	/**
	 * Shifts all the tiles left, combines like tiles that collide.
	 */
	private void moveLeft()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int colIterator = cols - 1; colIterator > 0; colIterator--) // will always make room for one under
			{
				for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
				{
					if (board[rowIterator][colIterator - 1] == 0)
					{
						board[rowIterator][colIterator - 1] = board[rowIterator][colIterator];
						board[rowIterator][colIterator] = 0;
					}
				}
			}
		}
		System.out.println("line 261: " + previousBoard[3][0]);
		for (int colIterator = cols - 1; colIterator > 0; colIterator--) // will always make room for one under
		{
			for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
			{
				System.out.println("line 266: " + previousBoard[3][0]);
				System.out.println("rowIterator: " + rowIterator);
				if (board[rowIterator][colIterator - 1] == board[rowIterator][colIterator])
				{
					score += board[rowIterator][colIterator - 1] * 2;
					board[rowIterator][colIterator - 1] *= 2;
					board[rowIterator][colIterator] = 0;
				}
				System.out.println("line 274~: " + previousBoard[3][0]);
			}
		}
		System.out.println("line 276: " + previousBoard[3][0]);
		for (int colIterator = cols - 1; colIterator > 0; colIterator--) // will always make room for one under
		{
			for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
			{
				if (board[rowIterator][colIterator - 1] == 0)
				{
					board[rowIterator][colIterator - 1] = board[rowIterator][colIterator];
					board[rowIterator][colIterator] = 0;
				}
			}
		}
		System.out.println("line 288: " + previousBoard[3][0]);
	}
	
	/**
	 * Shifts all the tiles right, combines like tiles that collide.
	 */
	private void moveRight()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int colIterator = 0; colIterator < cols - 1; colIterator++) // will always make room for one over
			{
				for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
				{
					if (board[rowIterator][colIterator + 1] == 0)
					{
						board[rowIterator][colIterator + 1] = board[rowIterator][colIterator];
						board[rowIterator][colIterator] = 0;
					}
				}
			}
		}
		for (int colIterator = 0; colIterator < cols - 1; colIterator++) // will always make room for one over
		{
			for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
			{
				if (board[rowIterator][colIterator + 1] == board[rowIterator][colIterator])
				{
					score += board[rowIterator][colIterator + 1] * 2;
					board[rowIterator][colIterator + 1] *= 2;
					board[rowIterator][colIterator] = 0;
				}
			}
		}
		for (int colIterator = 0; colIterator < cols - 1; colIterator++) // will always make room for one over
		{
			for (int rowIterator = 0; rowIterator < board.length; rowIterator++)
			{
				if (board[rowIterator][colIterator + 1] == 0)
				{
					board[rowIterator][colIterator + 1] = board[rowIterator][colIterator];
					board[rowIterator][colIterator] = 0;
				}
			}
		}
	}
	
	/**
	 * Sets board to previousBoard and score to previousScore
	 */
	public void undo()
	{
		System.out.println("undo runs");
		board = previousBoard;
		score = previousScore;
	}
	
	/**
	 * Returns true if the game is over, false otherwise.
	 * @return
	 */
	public boolean gameOver()
	{
		if (!full())
			return false;
		// looping through rows, if numbers are the same and adjacent the game is not over
		for (int[] row : board)
		{
			for (int i = 1; i < row.length; i++)
			{
				int previousElement = row[i - 1];
				int element = row[i];
				if (previousElement == element)
					return false;
			}
		}
		// yay columns pain
		for (int i = 0; i < cols; i++)
		{
			for (int j = 1; j < rows; j++)
			{
				int previousElement = board[j - 1][i];
				int element = board[j][i];
				if (previousElement == element)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a String representation of this object.
	 */
	public String toString()
	{
		String rtn = "";
		
		for(int[] row : board)
		{
			rtn += "|";
			for(int num : row)
				if(num != 0)
				{
					String str = num + "";
					while(str.length() < 4)
						str = " " + str;
					rtn += str;
					
				}
				else
					rtn += "    ";
			rtn += "|\n";
		}
		
		rtn += "Score: " + score + "\n";
		
		return rtn;
	}
}
