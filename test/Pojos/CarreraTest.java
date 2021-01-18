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
public class CarreraTest {
    
    public CarreraTest() {
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
     * Test of getIdCarrera method, of class Carrera.
     */
    @org.junit.Test
    public void testGetIdCarrera() {
        System.out.println("getIdCarrera");
        Carrera instance = new Carrera();
        int expResult = 0;
        int result = instance.getIdCarrera();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdCarrera method, of class Carrera.
     */
    @org.junit.Test
    public void testSetIdCarrera() {
        System.out.println("setIdCarrera");
        int idCarrera = 0;
        Carrera instance = new Carrera();
        instance.setIdCarrera(idCarrera);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Carrera.
     */
    @org.junit.Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Carrera instance = new Carrera();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Carrera.
     */
    @org.junit.Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Carrera instance = new Carrera();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdFacultad method, of class Carrera.
     */
    @org.junit.Test
    public void testGetIdFacultad() {
        System.out.println("getIdFacultad");
        Carrera instance = new Carrera();
        int expResult = 0;
        int result = instance.getIdFacultad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdFacultad method, of class Carrera.
     */
    @org.junit.Test
    public void testSetIdFacultad() {
        System.out.println("setIdFacultad");
        int idFacultad = 0;
        Carrera instance = new Carrera();
        instance.setIdFacultad(idFacultad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Carrera.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Carrera instance = new Carrera();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
