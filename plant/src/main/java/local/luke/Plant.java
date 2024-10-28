package local.luke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant implements Comparable<Plant> {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (planted.isAfter(watering)) {
            throw new PlantException("Zadej jiný datum! V datu, který jsi zadal, nebyla ještě rostlina zasazena.");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException("Frekvence zálivky nesmí být menší než 1.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo() {
        String info = name + ", poslední zálivka dne " + getWatering().format(DateTimeFormatter.ofPattern("d. M. y")) + ", doporučená další zálivka dne " + getWatering().plusDays(frequencyOfWatering).format(DateTimeFormatter.ofPattern("d. M. y"));
        return info;
    }

    public void doWateringNow() throws PlantException {
        setWatering(LocalDate.now());
    }

    @Override
    public int compareTo(Plant plant) {
        return this.name.compareTo(plant.name);
    }

}
