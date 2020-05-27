import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {
	Players p = new Players();
	Scanner scn = new Scanner(System.in);
	ArrayList<Players> listPlayers = new ArrayList<Players>();
	ArrayList<Players> listTemp = new ArrayList<Players>();// for greedy
	ArrayList<Players> listDynamic = new ArrayList<Players>();// for dynamic
	int x = 1, n = 1, k = 1;// input deðiþkenleri

	Management() {
		readFile();
		System.out.println("File is read.");
		input();
		temps();
		System.out.println("****Greedy Approach****");
		GreedyApproach();
		// System.out.println(knap());
	}

	public void readFile() {
		// dosya okuma kýsmý
		BufferedReader br = null;
		{
			try {
				br = new BufferedReader(new FileReader("C:\\Users\\mustafa\\Desktop\\input.txt"));
				String line;
				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					String parse[] = line.split("\t");
					listPlayers.add(new Players(parse[0], Integer.parseInt(parse[1].trim()),
							Integer.parseInt(parse[2].trim()), Integer.parseInt(parse[3].trim())));
				}
			} catch (Exception e) {
				System.out.println("ERROR!");
				System.exit(0);
			} finally {
				try {
					if (br != null) {
						br.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void input() {
		// input alma kýsmý
		try {
			System.out.print("Enter the amount to spend (X): ");
			x = scn.nextInt();
			System.out.print("Enter the number of the positions (N): ");// ilk n pozisyon
			n = scn.nextInt();
			System.out.print("Enter the number of the available players for each position (K): ");// ilk k oyuncu
			k = scn.nextInt();
			if ((x <= 0) || (n <= 0) || (k <= 0)) {
				System.out.println("Enter true values!");
				System.exit(0);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter true format input!"); // int yerine baþka tür girilmemesi için hata mesajý
			System.exit(0);
		} catch (Exception e) {
			System.out.println("ERROR!");
			System.exit(0);
		}
	}

	public void temps() {
		int count = 0;// while indexi(kaç eleman ekleyeceksem)
		int position = 0;
		int pos = 0;// listin indexi
		int p = 0;// kaç eleman yani k

		while (count < (n * k)) {
			if (position != listPlayers.get(pos).getPosition()) {
				position = listPlayers.get(pos).getPosition();
				p = 0;
			}
			if (position == listPlayers.get(pos).getPosition() && p < k) { // ekleme kýsmý tempe
				listTemp.add(listPlayers.get(pos));
				listDynamic.add(listPlayers.get(pos));
				count++;
				p++;
			}
			pos++;
		}
	}

	public void GreedyApproach() {

		// little price calculate
		int littleP = listTemp.get(0).getPrice();
		for (int i = 0; i < listTemp.size(); i++) {
			if (littleP > listTemp.get(i).getPrice())
				littleP = listTemp.get(i).getPrice();
		}

		// result for greedy
		int bigR = 0;
		int index = 0;// keep big ratio index
		int a = 0;
		int totalR = 0;
		int startM = x;// harcanan parayý bulmak için
		ArrayList<Integer> ps = new ArrayList<>(); // keep prev positions
		while (littleP <= x && a < n) {
			for (int i = 0; i < listTemp.size(); i++) { // big ratio
				if (bigR < listTemp.get(i).getRating() && listTemp.get(i).getPrice() <= x) {
					bigR = listTemp.get(i).getRating();
					index = i;
				}
				if (littleP > listTemp.get(i).getPrice())
					littleP = listTemp.get(i).getPrice();
			}
			boolean flag = ps.contains(listTemp.get(index).getPosition());
			if (flag == false && littleP <= x) {
				x = x - listTemp.get(index).getPrice(); // new price
				ps.add(listTemp.get(index).getPosition());
				System.out.println(listTemp.get(index).getName() + " " + listTemp.get(index).getPosition() + " "
						+ listTemp.get(index).getRating() + " " + listTemp.get(index).getPrice());
				a++;
				totalR += listTemp.get(index).getRating();
			}
			listTemp.remove(index);
			bigR = 0;
		}
		System.out
				.println("\nRemaining Money= " + x + "\nSpending Money= " + (startM - x) + "\nTotal Rating= " + totalR);
	}

	public int knap() {
		int n = listDynamic.size() / 2;
		int val[] = new int[n];
		int wt[] = new int[n];
		int W = x;
		for (int i = 0; i < n; i++) {
			val[i] = listDynamic.get(i).getRating();
		}

		int j = 0;
		for (int i = n; i < 2 * n; i++) {
			wt[j] = listDynamic.get(i).getRating();
			j++;
		}
		int K[][] = new int[n + 1][W + 1];
		int r = 0;
		while (x > 0) {
			for (int i = 0; i <= n; i++) {
				for (int w = 0; w <= W; w++) {
					if (i == 0 || w == 0)
						K[i][w] = 0;
					else if (wt[i - 1] <= w) {
						K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
					} else
						K[i][w] = K[i - 1][w];
				}
			}
			r = K[n][W];

		}
		return r;
	}

	static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}
