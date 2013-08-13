package data;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int player = 5;
		int enemy = 10;
		int count = 1;
		String choose;
		Scanner scan = new Scanner(System.in);
		do {
			choose = "e";
			do {
				try {
					System.out.println("You wanna play a litle game?...(y, n, q)");
					choose = scan.next();
				} catch (Exception e) {
					System.out.println("Wrong input!");
					break;
				}
			//y - player vs comp; n - comp vs comp; q - exit
			} while (!(choose.matches("n")) && !(choose.matches("y")) && !(choose.matches("q")));
			
			if (choose.matches("q")) break;
			if (choose.matches("n")) {
				Space space = new Space();
				do {
					System.out.println();
					if ((count % 2) == 0){
						
						space.getStep(enemy);
						space.print();
						
					} else {
						
						space.getStep(player);
						space.print();
						
					}
					count++;
				} while (!space.getOver());
			} else if (choose.matches("y")) {
				Space space = new Space();
				space.print();
				do {
					System.out.println();
					if ((count % 2) == 0){
						switch (space.getWin()) {
						case (5):
							System.out.println("Player win!");
							break;
						case (10):
							System.out.println("Enemy win!");
							break;
						}
						space.getStep(enemy);
						space.print();
						
					} else {
						
						
						if (space.getWin() == 5) {
							System.out.println("Player win!");
							break;
						} else if (space.getWin() == 10) {
							System.out.println("Enemy win!");
							break;
						}
						
						int j = scan.nextInt() - 1;
						int i = scan.nextInt() - 1;
						
							
						space.setValue(i, j, player);
						space.print();
						
					}
					count++;
				} while (!space.getOver());
			} 
		} while ((choose.matches("y")) || (choose.matches("n")));
		scan.close();
	}
}
