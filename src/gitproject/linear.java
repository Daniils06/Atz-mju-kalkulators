package gitproject;

import java.text.DecimalFormat;
import java.util.Scanner;

public class linear {

    public static void main(String[] args) {
        int studC, critC;
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.#");

        // --- Student count ---
        do {
            System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
            while (!scan.hasNextInt()) {
                System.out.println("Ievadi veselu skaitli:");
                scan.next(); // discard invalid
            }
            studC = scan.nextInt();
            scan.nextLine(); // FIX — consume leftover newline
        } while (studC < 1);

        String[] stud = new String[studC];

        // --- Criterion count ---
        do {
            System.out.println("Norādi kritēriju skaitu: ");
            while (!scan.hasNextInt()) {
                System.out.println("Ievadi veselu skaitli:");
                scan.next(); // discard invalid
            }
            critC = scan.nextInt();
            scan.nextLine(); // FIX — consume leftover newline
        } while (critC < 1);

        String[] crit = new String[critC];
        int[] critW = new int[critC];
        int[][] critG = new int[studC][critC];
        double[] finalG = new double[studC];

        scan.nextLine();
        for (int i = 0; i < stud.length; i++) {
            do {
                System.out.println("Ievadi " + (i + 1) + ". studenta vārdu:");
                stud[i] = scan.nextLine().trim();
            } while (!stud[i].matches("^[\\p{L} ]+$"));
        }
    }
}
