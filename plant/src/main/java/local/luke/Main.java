package local.luke;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PlantManager plantManager = new PlantManager();

        try {
            // Načte odkomentovaný soubor
            plantManager.loadPlantsFromFile(Settings.getFileNameImput(), Settings.getDelimiter());
            //plantManager.loadPlantsFromFile(Settings.getFileNameWrongDate(), Settings.getDelimiter());
            //plantManager.loadPlantsFromFile(Settings.getFileNameWrongFrequency(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při práci se souborem! " + e.getLocalizedMessage());;
        } catch (DateTimeException e) {
            System.err.println("Datum obsahuje nepovolené znaky! " + e.getLocalizedMessage());;
        } catch (NumberFormatException e) {
            System.err.println("Číslo obsahuje nepovolené znaky! " + e.getLocalizedMessage());;
        }

        // Vypíše informace o zálivce ze souboru kvetiny.txt
        System.out.println("Údaje ze souboru " + Settings.getFileNameImput());
        for (Plant plant : plantManager.getPlants()) {
        System.out.println(plant.getWateringInfo());
        }

        try {
            // Přidání vymyšlené rostliny
            plantManager.addPlant(new Plant("Fíkus"));

            // Přidá 10 rostlin
            for (int i = 1; i <= 10; i++) {
                plantManager.addPlant(new Plant("Tulipán na prodej " + String.format("%02d", i), 14));
            }
        } catch (PlantException e) {
            System.err.println("Chyba při přidání rostliny! " + e.getLocalizedMessage());
        }

        // Odebere rostlinu na 3. pozici
        plantManager.removePlant(plantManager.getPlantOnIndex(2));

        try {
            // Uloží květiny do souboru kvetiny-output.txt
            plantManager.SAVEPlantsToFile(Settings.getFileNameOutput(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());;
        }

        try {
            // Načte soubor kvetiny-output.txt
            plantManager.loadPlantsFromFile(Settings.getFileNameOutput(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při práci se souborem!" + e.getLocalizedMessage());;
        }

        // Vypíše informace o zálivce ze souboru kvetiny-output.txt
        System.out.println();
        System.out.println("Údaje ze souboru " + Settings.getFileNameOutput());
        for (Plant plant : plantManager.getPlants()) {
            System.out.println(plant.getWateringInfo());
        }

        // Vypíše seřazené rostliny dle jména
        System.out.println();
        System.out.println("Seřazené rostliny dle jména");
        for (Plant plant : plantManager.getSortPlantsByName()) {
            System.out.println(plant.getWateringInfo());
        }

        // Vypíše seřazené rostliny dle data poslední zálivky
        System.out.println();
        System.out.println("Seřazené rostliny dle data poslední zálivky");
        for (Plant plant : plantManager.getSortPlantsByWatering()) {
            System.out.println(plant.getWateringInfo());
        }

        // Vypíše rostliny, které je třeba zalít
        System.out.println();
        System.out.println("Rostliny, které je třeba zalít");
        for (Plant plant : plantManager.getNeedWatering()) {
            System.out.println(plant.getWateringInfo());
        }
    }
}