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
public class PeriodoTest {
    
    public PeriodoTest() {
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
     * Test of getIdPeriodo method, of class Periodo.
     */
    @org.junit.Test
    public void testGetIdPeriodo() {
        System.out.println("getIdPeriodo");
        Periodo instance = new Periodo();
        int expResult = 0;
        int result = instance.getIdPeriodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdPeriodo method, of class Periodo.
     */
    @org.junit.Test
    public void testSetIdPeriodo() {
        System.out.println("setIdPeriodo");
        int idPeriodo = 0;
        Periodo instance = new Periodo();
        instance.setIdPeriodo(idPeriodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Periodo.
     */
    @org.junit.Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Periodo instance = new Periodo();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Periodo.
     */
    @org.junit.Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Periodo instance = new Periodo();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaInicio method, of class Periodo.
     */
    @org.junit.Test
    public void testGetFechaInicio() {
        System.out.println("getFechaInicio");
        Periodo instance = new Periodo();
        String expResult = "";
        String result = instance.getFechaInicio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaInicio method, of class Periodo.
     */
    @org.junit.Test
    public void testSetFechaInicio() {
        System.out.println("setFechaInicio");
        String fechaInicio = "";
        Periodo instance = new Periodo();
        instance.setFechaInicio(fechaInicio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaFin method, of class Periodo.
     */
    @org.junit.Test
    public void testGetFechaFin() {
        System.out.println("getFechaFin");
        Periodo instance = new Periodo();
        String expResult = "";
        String result = instance.getFechaFin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaFin method, of class Periodo.
     */
    @org.junit.Test
    public void testSetFechaFin() {
        System.out.println("setFechaFin");
        String fechaFin = "";
        Periodo instance = new Periodo();
        instance.setFechaFin(fechaFin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Periodo.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Periodo instance = new Periodo();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
