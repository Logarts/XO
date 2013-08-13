package data;

public class Space {
	private int[][] field;
	private boolean gameOver = false;
	private boolean win = false;
	Space() {
		field = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				field[i][j] = 1;
			}
		}
	}
	public boolean getWin() {
		return win;
	}
	public boolean getOver() {
		return gameOver;
	}
	private char unCode(int code) {
		switch (code) {
		case(1): return ' ';
		case(5): return 'X';
		case(10): return 'O';
		default: return 'N';
		}
	}
	public void print() {
		System.out.println("    1   2   3  ");
		for (int i = 0; i < 3; i++) {
			System.out.print(i+1 + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print("| " + unCode(field[i][j]) + " ");
			}
			System.out.println("|");
		}
	}
	private boolean switchCase (int count, int player, int first, int second, boolean canWin) {
		switch(count) {
		case(1000):
			win = true;
			return true;
		case(125):
			win = true;
			return true;
		case(25):
			switch(player) {
			case (5):
				field[first][second] = player;
				win = true;
				return true;
			case (10):
				if (!canWin) {
					field[first][second] = player;
					return true;
				}
				break;
			}
			break;
		case(100):
			switch(player) {
			case(10):
				field[first][second] = player;
				win = true;
				return true;
			case(5):
				if (!canWin) {
					field[first][second] = player;
					return true;
				}
				break;
			}
			break;
		}
		return false;
	}
	private boolean horSearch (int player, boolean canWin) {
		for (int i = 0; i < 3; i++) {
			int flag = -1;
			int count = 1;
			for (int j = 0; j < 3; j++) {
				count *= field[i][j];
				if (field[i][j] == 1) {
					flag = j;
				}
			}
			if (switchCase(count, player, i, flag, canWin)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	private boolean verSearch (int player, boolean canWin) {
		for (int j = 0; j < 3; j++) {
			int flag = -1;
			int count = 1;
			for (int i = 0; i < 3; i++) {
				count *= field[i][j];
				if (field[i][j] == 1) {
					flag = i;
				}
			}

			if (switchCase(count, player, flag, j, canWin)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean diagSearch (int player, boolean canWin) {
		int count = 1;
		int flag = -1;
		{
			for (int i = 0; i < 3; i++) {
				count *= field[i][i];
				if (field[i][i] == 1) {
				flag = i;
				}
			}
			if (switchCase(count, player, flag, flag, canWin)) {
				return true;
			}
		}
		flag = -1;
		count = 1;
		{
			for (int i = 0; i < 3; i++) {
				count *= field[i][2-i];
				if (field[i][2-i] == 1) {
				flag = i;
				}
			}
			if (switchCase(count, player, flag, 2-flag, canWin)) {
				return true;
			}
		}
		return false;
	}
	
	public void getStep (int player) {
	
		//CAN WIN?
		//hor
		if (horSearch(player, true)) {
			return;
		}
		//ver
		if (verSearch(player, true)) {
			return;
		}		
		//diag
		if (diagSearch(player, true)) {
			return;
		}		
		//MAY LOSE?
		//hor
		if (horSearch(player, false)) {
			return;
		}		
		//ver
		if (verSearch(player, false)) {
			return;
		}
		//diag
		if (diagSearch(player, false)) {
			return;
		}
		//ELSE
		if (field[1][1] == 1) {
			field[1][1] = player;
			return;
		}
		for (int i = 0; i < 3; i += 2) {
			for (int j = 0; j < 3; j+= 2) {
				if (field[i][j] == 1) {
					field[i][j] = player;
					return;
				}
			}
		}
		for (int i = 0; i < 3; i+= 2) {
			for (int j = 1; j < 3; j += 2) {
				if (field[i][j] == 1) {
					field[i][j] = player;
					return;
				}
			}
		}
		gameOver = true;		
	}
	
	public void setValue (int i, int j, int player) {
		field[i][j] = player;
	}
	
}
	