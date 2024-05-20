package com.premiumminds.internship.teknonymy;

class TeknonymyService implements ITeknonymyService {

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {
    if (!person.hasChildren()) {
      return "";
    }
    PersonGenInfo target = findDescendant(new PersonGenInfo(person,0));

    return person.teknonymyToString(target.person().name(),target.generation() ); 
  };

  /*
   * Recursive method to find the farthest descendant of a person
   * In case of a tie, the oldest descendant is chosen
   * @param PersonGenInfo person
   * @return PersonGenInfo person and generation of the target descendant
   * 
   */
  public PersonGenInfo findDescendant(PersonGenInfo descendant){
    PersonGenInfo target = descendant;

    if(descendant.person().hasChildren()){
      int num_childs = descendant.person().getNumberOfChildren();
      
      // for each child, find the farthest descendant threw recursion
      for (int i = 0; i < num_childs; i++) {
        PersonGenInfo temp = findDescendant(new PersonGenInfo(descendant.person().children()[i], descendant.generation()+1));  
        if(temp.isFarthestGenThan(target)){
          target = temp;
        } else if(temp.sameGeneration(target) && temp.person().isOlderThan(target.person())){
          target = temp;
        }
      }
    }
    
    return target;
    
  };
}
