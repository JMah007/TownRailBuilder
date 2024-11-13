package edu.curtin.app;

public class Town {
    private String name;
    private int population;
    private int numGoodsStocked;
    private int numGoodsTransported;

    /**
     * This is a constructor that initalises this class
     */
    public Town(String newName, int newPopulation){
        this.name = newName;
        this.population = newPopulation;
        this.numGoodsStocked = 0;
        this.numGoodsTransported = 0;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getNumGoodsStocked() {
        return numGoodsStocked;
    }

    public int getNumGoodsTransported() {
        return numGoodsTransported;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setNumGoodsStocked(int num){
        this.numGoodsStocked = num;

    }

    public void setNumGoodsTransported(int num){
        this.numGoodsTransported = num;

    }

    /**
     * This method calculates how mnay goods are produced by the town. Each person produces 1 good every time this method is called so the total produced is the value of the population
     */
    public void produceGoods(){
        int numGoodsProduced;
        
        numGoodsProduced = getPopulation() * 1; // each person produces 1 good per day

        setNumGoodsStocked(numGoodsStocked + numGoodsProduced); 
    } 
}
