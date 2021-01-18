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
public class AcademiaTest {
    
    public AcademiaTest() {
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
     * Test of getIdAcademia method, of class Academia.
     */
    @org.junit.Test
    public void testGetIdAcademia() {
        System.out.println("getIdAcademia");
        Academia instance = new Academia();
        int expResult = 0;
        int result = instance.getIdAcademia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdAcademia method, of class Academia.
     */
    @org.junit.Test
    public void testSetIdAcademia() {
        System.out.println("setIdAcademia");
        int idAcademia = 0;
        Academia instance = new Academia();
        instance.setIdAcademia(idAcademia);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Academia.
     */
    @org.junit.Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Academia instance = new Academia();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Academia.
     */
    @org.junit.Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Academia instance = new Academia();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdCoordinador method, of class Academia.
     */
    @org.junit.Test
    public void testGetIdCoordinador() {
        System.out.println("getIdCoordinador");
        Academia instance = new Academia();
        int expResult = 0;
        int result = instance.getIdCoordinador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdCoordinador method, of class Academia.
     */
    @org.junit.Test
    public void testSetIdCoordinador() {
        System.out.println("setIdCoordinador");
        int idCoordinador = 0;
        Academia instance = new Academia();
        instance.setIdCoordinador(idCoordinador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Academia.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Academia instance = new Academia();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
