package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonThreeChildrenTest() {
    Person person = new Person(
        "Tiffany",
        'F',
        new Person[]{ 
          new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
          new Person("Mary",'F', null, LocalDateTime.of(1045, 1, 1, 0, 0)),
          new Person("Tyrone",'M', null, LocalDateTime.of(1047, 1, 1, 0, 0)),
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "mother of Mary";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoChildrenOneGrandChildTest(){
    Person person = new Person(
        "John",
        'M',
        new Person[]{ 
          new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
          new Person("Mary",'F', new Person[]{ new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) }, LocalDateTime.of(1046, 1, 1, 0, 0))
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Jane";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoChildrenEachOneGrandChildTest(){
    Person person = new Person(
        "Tiffany",
        'F',
        new Person[]{ 
          new Person("Holy",'F', new Person[] {
            new Person("Jenny",'F', null, LocalDateTime.of(1045, 1, 1, 0, 0))
          }, LocalDateTime.of(1046, 1, 1, 0, 0)),

          new Person("Mary",'F', new Person[]{ 
            new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0))
          }, LocalDateTime.of(1046, 1, 1, 0, 0))
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandmother of Jenny";
    assertEquals(result, expected);
  }

  @Test
  public void PersonGreatGrandChildTest(){
    Person person = new Person(
        "John",
        'M',
        new Person[]{ 
          new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
          new Person("Mary",'F', new Person[]{ 
            new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
            new Person("Jenny",'F', new Person[]{
              new Person("Janine",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0))
            }, LocalDateTime.of(1046, 1, 1, 0, 0))
          }, LocalDateTime.of(1046, 1, 1, 0, 0))
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of Janine";
    assertEquals( expected,result);
  }

  @Test
  public void PersonGreatGreatGrandChildTest(){
    Person person = new Person(
        "John",
        'f',
        new Person[]{ 
          new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
          new Person("Mary",'F', new Person[]{ 
            new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)),
            new Person("Jenny",'F', new Person[]{
              new Person("Janine.",'F', new Person[]{
                new Person("Tyrone",'M', null, LocalDateTime.of(1046, 1, 1, 0, 0))
              }, LocalDateTime.of(1046, 1, 1, 0, 0))
            }, LocalDateTime.of(1046, 1, 1, 0, 0))
          }, LocalDateTime.of(1046, 1, 1, 0, 0))
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-grandmother of Tyrone";
    assertEquals(result, expected);
  }



}