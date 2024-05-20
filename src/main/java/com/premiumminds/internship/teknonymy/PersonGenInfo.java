package com.premiumminds.internship.teknonymy;

public record PersonGenInfo(Person person, int generation){

    public boolean isFarthestGenThan(PersonGenInfo node){
        return this.generation > node.generation;
    }

    public boolean sameGeneration(PersonGenInfo node){
        return this.generation == node.generation;
    }
    
}