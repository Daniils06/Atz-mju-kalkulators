package gitproject;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class linear {

    static Scanner scan = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        int studentCount = inputInt("Cik studentus vēlies ievadīt? ", 1, Integer.MAX_VALUE);
        int critCount = inputInt("Norādi kritēriju skaitu: ", 1, Integer.MAX_VALUE);

        String[] students = inputStudents(studentCount);
        String[] criteria = inputCriteria(critCount);
        int[] weights = inputWeights(critCount);
        int[][] grades = inputGrades(studentCount, critCount, students, criteria);

        double[] finalGrades = calculateFinalGrades(grades, weights);

        sortStudents(students, finalGrades);

        printResults(students, finalGrades);

        saveToFile(students, finalGrades);

        System.out.println("\n Programma pabeigta!");
    }

    static int inputInt(String msg, int min, int max) {
        int value;
        do {
            System.out.print(msg);
            while (!scan.hasNextInt()) {
                System.out.print("Ievadi skaitli! ");
                scan.next();
            }
            value = scan.nextInt();
        } while (value < min || value > max);
        return value;
    }
    static String[] inputStudents(int count) {
        scan.nextLine(); 
        String[] arr = new String[count];

        for (int i = 0; i < count; i++) {
            do {
                System.out.print("Ievadi " + (i + 1) + ". studenta vārdu: ");
                arr[i] = scan.nextLine().trim();
            } while (!arr[i].matches("^[\\p{L} ]+$"));
        }
        return arr;
    }

    static String[] inputCriteria(int count) {
        scan.nextLine();
        String[] arr = new String[count];

        for (int i = 0; i < count; i++) {
            do {
                System.out.print("Ievadi " + (i + 1) + ". kritēriju: ");
                arr[i] = scan.nextLine().trim();
            } while (!arr[i].matches("^[\\p{L} ]+$"));
        }
        return arr;
    }

    static int[] inputWeights(int count) {
        int[] w = new int[count];
        int total;

        do {
            total = 0;
            for (int i = 0; i < count; i++) {
                w[i] = inputInt("Norādi '" + (i + 1) + "' kritērija svaru (5–100): ", 5, 100);
                total += w[i];
            }
            if (total > 100) System.out.println("Kritēriju kopējā summa nedrīkst pārsniegt 100! Mēģini vēlreiz.\n");
        } while (total > 100);

        return w;
    }

    static int[][] inputGrades(int studentCount, int critCount, String[] students, String[] criteria) {
        int[][] g = new int[studentCount][critCount];

        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < critCount; j++) {
                g[i][j] = inputInt("Ievadi " + students[i] +
                        " vērtējumu kritērijā '" + criteria[j] + "' (0–10): ", 0, 10);
            }
        }
        return g;
    }

    static double[] calculateFinalGrades(int[][] grades, int[] weights) {
        double[] finalG = new double[grades.length];

        for (int i = 0; i < grades.length; i++) {
            double sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j] * (weights[j] / 100.0);
            }
            finalG[i] = sum;
        }
        return finalG;
    }

    static void sortStudents(String[] students, double[] finalGrades) {
        for (int i = 0; i < finalGrades.length - 1; i++) {
            for (int j = i + 1; j < finalGrades.length; j++) {
                if (finalGrades[j] > finalGrades[i]) {
                    double tmpG = finalGrades[i];
                    finalGrades[i] = finalGrades[j];
                    finalGrades[j] = tmpG;

                    String tmpS = students[i];
                    students[i] = students[j];
                    students[j] = tmpS;
                }
            }
        }
    }

    static void printResults(String[] students, double[] grades) {
        System.out.println("\n--- Gala vērtējumi (sakārtoti dilstoši) ---");
        for (int i = 0; i < students.length; i++) {
            System.out.println((i + 1) + ". " + students[i] + " – " + df.format(grades[i]));
        }
    }

    static void saveToFile(String[] students, double[] grades) {
        System.out.print("\nVai saglabāt rezultātus failā? (j/n): ");
        scan.nextLine();
        String ans = scan.nextLine();

        if (!ans.equalsIgnoreCase("j")) return;

        try (FileWriter fw = new FileWriter("rezultati.txt")) {
            for (int i = 0; i < students.length; i++) {
                fw.write(students[i] + " - " + df.format(grades[i]) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Neizdevās saglabāt failu!");
        }
    }
}
