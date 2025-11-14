package gitproject;
import java.text.DecimalFormat;
import java.util.Scanner;
public class linear {
    public static void main(String[] args) {
        int studC, critC;
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.#");
        do {
            System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
            while (!scan.hasNextInt()) {
                System.out.println("Ievadi skaitli!");
                scan.next();
            }
            studC = scan.nextInt();
        } while (studC < 1);
        String[] stud = new String[studC];
        do {
            System.out.println("Norādi kritēriju skaitu:");
            while (!scan.hasNextInt()) {
                System.out.println("Ievadi skaitli!");
                scan.next();
            }
            critC = scan.nextInt();
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
        for (int i = 0; i < crit.length; i++) {
            do {
                System.out.println("Ievadi " + (i + 1) + ". kritēriju:");
                crit[i] = scan.nextLine().trim();
            } while (!crit[i].matches("^[\\p{L} ]+$"));
            System.out.println("Norādi kritērija '" + crit[i] + "' svaru:");
            while (!scan.hasNextInt()) {
                System.out.println("Ievadi skaitli!");
                scan.next();
            }
            critW[i] = scan.nextInt();
            scan.nextLine();
        }
        for (int i = 0; i < studC; i++) {
            for (int j = 0; j < critC; j++) {
                do {
                    System.out.println("Norādi " + stud[i] + " vērtējumu par kritēriju '" + crit[j] + "':");
                    while (!scan.hasNextInt()) {
                        System.out.println("Ievadi skaitli!");
                        scan.next();
                    }
                    critG[i][j] = scan.nextInt();
                } while (critG[i][j] < 0 || critG[i][j] > 10);
            }
        }
        double rezult;
        for(int i=0; i<stud.length; i++) {
        	rezult =0;
        	for(int j=0; j<crit.length; j++) {
        		rezult += ((double)critW[j]/100)*critG[i][j];
        	}
        	finalG[i] = rezult;
        }
        scan.close();
        System.out.println("Gala vērtējumi: ");
        for(int i=0; i<stud.length; i++) {
        	for(int j=0; j<crit.length; j++) {
        		System.out.println("Studenta "+stud[i]+" vārtējums par kritēriju "+crit[j]+" ir "+critG[i][j]+", kura svars ir "+critW[j]);
        	}
        	System.out.println("Semestra vērtējums: "+df.format(finalG[i])+"balles"+"\n+++++++++++++++++++++++++++++++++++++++++\n");
        }
    }
}