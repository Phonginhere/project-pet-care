package com.example.searchpetforbreeding.models;

import java.util.Date;

public class Pets {

    private String petName;

    private boolean gender;
    private String breed;
    private String species;

    private Long petHeight;
    private Long petWeight;
    private String birthDate;
    private String color;
    private boolean intact;
    private String notes;

    private int petId;
    private int userId;

    public Pets() {
    }

    public Pets(String petName, boolean gender, String breed, String species, Long petHeight, Long petWeight, String birthDate, String color, boolean intact, String notes, int petId, int userId) {
        this.petName = petName;
        this.gender = gender;
        this.breed = breed;
        this.species = species;
        this.petHeight = petHeight;
        this.petWeight = petWeight;
        this.birthDate = birthDate;
        this.color = color;
        this.intact = intact;
        this.notes = notes;
        this.petId = petId;
        this.userId = userId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Long getPetHeight() {
        return petHeight;
    }

    public void setPetHeight(Long petHeight) {
        this.petHeight = petHeight;
    }

    public Long getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(Long petWeight) {
        this.petWeight = petWeight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isIntact() {
        return intact;
    }

    public void setIntact(boolean intact) {
        this.intact = intact;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
