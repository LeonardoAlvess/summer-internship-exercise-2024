package com.premiumminds.internship.teknonymy;

import java.time.LocalDateTime;

public record Person(String name,Character sex, Person[] children, LocalDateTime dateOfBirth) {
    
    public boolean hasChildren() {
        if (this.children == null) {
            return false;
        }
        return this.children.length != 0;
    }

    public int getNumberOfChildren() {
        return this.children.length;
    }

    public boolean isOlderThan(Person person) {
        return this.dateOfBirth.isBefore(person.dateOfBirth);
    }

    public String teknonymyToString(String childName, int generation) {
        
        String[] femaleNames = {"mother", "grandmother"}; 
        String[] maleNames = {"father", "grandfather"};
        String result = "";

        while(generation >=3) {
            result += "great-";
            generation--;
        };

        if(this.sex == 'M'){
            result += maleNames[generation-1] + " of " + childName;
        } else {
            result += femaleNames[generation-1] + " of " + childName;
        }
        
        return result;
    }
    
}
