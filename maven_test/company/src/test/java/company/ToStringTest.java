package company;

import org.junit.Test;

import company.Entity.Person;

public class ToStringTest{

    @Test
    public void SimpleToStringTest(){
        Person p = new Person("firstnamecool","lastnamesuper");
        System.out.println(p.toString());
    }
}