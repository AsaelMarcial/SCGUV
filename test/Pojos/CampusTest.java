/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dltun
 */
public class CampusTest {
    
    public CampusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdCampus method, of class Campus.
     */
    @org.junit.Test
    public void testGetIdCampus() {
        System.out.println("getIdCampus");
        Campus instance = new Campus();
        int expResult = 0;
        int result = instance.getIdCampus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdCampus method, of class Campus.
     */
    @org.junit.Test
    public void testSetIdCampus() {
        System.out.println("setIdCampus");
        int idCampus = 0;
        Campus instance = new Campus();
        instance.setIdCampus(idCampus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Campus.
     */
    @org.junit.Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Campus instance = new Campus();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Campus.
     */
    @org.junit.Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Campus instance = new Campus();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Campus.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Campus instance = new Campus();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
