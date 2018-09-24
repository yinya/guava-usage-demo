package com.yy.guava.demo;

import com.google.common.collect.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionTest {

    @Test
    public void multiSet01(){
        //create a multiset collection
        Multiset<String> multiset = HashMultiset.create();

        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");

        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : "+multiset.count("b"));

        //print the total size of the multiset
        System.out.println("Total Size : "+multiset.size());

        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();

        //display the elements of the set
        System.out.println("Set [");

        for (String s : set) {
            System.out.println(s);
        }

        System.out.println("]");

        //display all the elements of the multiset using iterator
        Iterator<String> iterator  = multiset.iterator();
        System.out.println("MultiSet [");

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("]");

        //display the distinct elements of the multiset with their occurrence count
        System.out.println("MultiSet [");

        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement() + ", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        //remove extra occurrences
        multiset.remove("b",2);

        //print the occurrence of an element
        System.out.println("Occurence of 'b' : " + multiset.count("b"));

    }


    @Test
    public void multiMap01(){

        Multimap<String,String> multimap = getMultimap();

        // 注意修改这些list对原先的multimap是生效的。
        List<String> lowerList = (List<String>)multimap.get("lower");
        System.out.println("Initial lower case list");
        System.out.println(lowerList.toString());

        lowerList.add("f");
        System.out.println("Modified lower case list");
        System.out.println(lowerList.toString());

        List<String> upperList = (List<String>)multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());

        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());

        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");

        for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        System.out.println("Keys of Multimap");
        Set<String> keys =  multimap.keySet();

        for(String key:keys) {
            System.out.println(key);
        }

        System.out.println("Values of Multimap");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }

    private Multimap<String,String> getMultimap() {

        //Map<String, List<String>>
        // lower -> a, b, c, d, e
        // upper -> A, B, C, D

        Multimap<String,String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        return multimap;
    }


    /**
     * A BiMap is a special kind of map which maintains an inverse view of
     * the map while ensuring that no duplicate values are present in the map
     * and a value can be used safely to get the key back
     *
     * /何时用？？？
     */
    @Test
    public void bitMap01(){
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");


        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }


    @Test
    public void table01(){
        //Table<R,C,V> == Map<R,Map<C,V>>
      /*
      *  Company: IBM, Microsoft, TCS
      *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
      *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan }
      *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil }
      *
      * */

        //create a table
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "102","Sunil");

        //get Map corresponding to IBM
        Map<String,String> ibmEmployees =  employeeTable.row("IBM");

        System.out.println("List of IBM Employees");

        for(Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        //get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");

        for(String employer: employers) {
            System.out.print(employer + " ");
        }

        System.out.println();

        //get a Map corresponding to 102
        Map<String,String> EmployerMap =  employeeTable.column("102");

        for(Map.Entry<String, String> entry : EmployerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }
}
