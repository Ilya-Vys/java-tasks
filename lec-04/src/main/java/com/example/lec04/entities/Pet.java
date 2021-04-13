package com.example.lec04.entities;


import com.example.lec04.petStorage.Observer;

public class Pet implements Observer {

    public static int count;
    private int id;
    private String name;
    private int weight;
    private int ageYears;
    private int ageMonths;
    private int ageDays;
    private Person owner;

    public Pet() {
    }

    private Pet(Builder builder){
        this.id = builder.id;
        this.owner = builder.owner;
        this.name = builder.name;
        this.weight = builder.weight;
        this.ageYears = builder.ageYears;
        this.ageMonths = builder.ageMonths;
        this.ageDays = builder.ageDays;
    }

    @Override
    public void handleEvent(Person person, int age) {
        if (this.getOwner().equals(person)){
            System.out.println(person + " change age to " + age + " from "+ name);
            this.getOwner().setAge(age);
        }
    }

    public static class Builder {
        private final Person owner;
        private final String name;
        private final int id;
        private int weight;
        private int ageYears;
        private int ageMonths;
        private int ageDays;

        public Builder(Person owner, String name) {
            this.owner = owner;
            this.name = name;
            this.id = count++;
        }

        public Builder weight(int weight){
            this.weight = weight;
            return this;
        }

        public Builder ageYears(int age){
            this.ageYears = age;
            return this;
        }

        public Builder ageMonths(int age){
            this.ageMonths = age;
            return this;
        }

        public Builder ageDays(int age){
            this.ageDays = age;
            return this;
        }

        public Pet build(){
            return new Pet(this);
        }
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public int getAgeDays() {
        return ageDays;
    }

    public Person getOwner() {
        return owner;
    }

    public static void setCount(int count) {
        Pet.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public void setAgeDays(int ageDays) {
        this.ageDays = ageDays;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", ageYears=" + ageYears +
                ", ageMonths=" + ageMonths +
                ", ageDays=" + ageDays +
                ", owner=" + owner +
                '}';
    }
}
