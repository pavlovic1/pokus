package pro1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String fileName = "participants.txt";
        List<String> lines = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();

        // 1. Načtení dat ze souboru
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(processLine(line, currentYear));
                }
            }
        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
            return;
        }

        // 2. Obrácení pořadí seznamu
        Collections.reverse(lines);

        // 3. Výpis na konzoli
        System.out.println("Seznam účastníků (od nejnovějšího s věkem):");
        for (String participant : lines) {
            System.out.println(participant);
        }
    }

    /**
     * Metoda rozdělí řádek, vypočítá věk a vrátí upravený řetězec.
     */
    private static String processLine(String line, int currentYear) {
        try {
            // Předpokládá formát: Jméno Příjmení, Rok
            String[] parts = line.split(",");
            if (parts.length < 2) return line;

            String name = parts[0].trim();
            int birthYear = Integer.parseInt(parts[1].trim());
            int age = currentYear - birthYear;

            return name + ", " + age;
        } catch (NumberFormatException e) {
            return line + " (Chyba ve formátu roku)";
        }
    }
}
