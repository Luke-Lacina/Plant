package local.luke;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class PlantManager {

    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlantOnIndex(int index) {
        return         plants.get(index);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> getNeedWatering() {
        List<Plant> needWatering = new ArrayList<>();
        for (Plant plant : plants) {
            if (LocalDate.now().isAfter(plant.getWatering().plusDays(plant.getFrequencyOfWatering()))) {
                needWatering.add(plant);
            }
        }
        return needWatering;
    }

    public List<Plant> getSortPlantsByName() {
        List<Plant> sortPlantsByName = new ArrayList<>(plants);
        Collections.sort(sortPlantsByName);
        return sortPlantsByName;
    }

    public List<Plant> getSortPlantsByWatering() {
        List<Plant> sortPlantsByWatering = new ArrayList<>(plants);
        sortPlantsByWatering.sort(Comparator.comparing(Plant::getWatering));
        return sortPlantsByWatering;
    }

    public void loadPlantsFromFile(String fileName, String delimiter) throws PlantException {
        List<Plant> tempPlants = new ArrayList<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            String line;
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineNumber++;
                String[] parts = line.split(delimiter);
                Plant plant = parsePlant(parts, lineNumber, line);
                tempPlants.add(plant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileName + " nebyl nalezen!\n" + e.getMessage());
        }

        plants.clear();
        plants.addAll(tempPlants);
    }

    private static Plant parsePlant(String[] parts, int lineNumber, String line) throws PlantException {
        if (parts.length != 5) {
            throw new PlantException("Špatný formát vstupního souboru: Nesprávný počet položek na řádku číslo " + lineNumber + ": " + line +"!");
        }
        String name = parts[0].trim();
        String notes = parts[1].trim();
        LocalDate planted = LocalDate.parse(parts[4].trim());
        LocalDate watering = LocalDate.parse(parts[3].trim());
        int frequencyOfWatering = Integer.parseInt(parts[2].trim());

        return new Plant(name, notes, planted, watering, frequencyOfWatering);
    }

    public void SAVEPlantsToFile(String fileName, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                writer.println(
                    plant.getName() + delimiter
                    + plant.getNotes() + delimiter
                    + plant.getFrequencyOfWatering() + delimiter
                    + plant.getWatering() + delimiter
                    + plant.getPlanted());
            }

        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru " + fileName + e.getLocalizedMessage());
        }
    }
}
