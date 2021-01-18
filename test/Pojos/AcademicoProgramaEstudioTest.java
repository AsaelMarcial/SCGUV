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
public class AcademicoProgramaEstudioTest {
    
    public AcademicoProgramaEstudioTest() {
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
     * Test of getIdProgramaEstudio method, of class AcademicoProgramaEstudio.
     */
    @org.junit.Test
    public void testGetIdProgramaEstudio() {
        System.out.println("getIdProgramaEstudio");
        AcademicoProgramaEstudio instance = new AcademicoProgramaEstudio();
        int expResult = 0;
        int result = instance.getIdProgramaEstudio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdProgramaEstudio method, of class AcademicoProgramaEstudio.
     */
    @org.junit.Test
    public void testSetIdProgramaEstudio() {
        System.out.println("setIdProgramaEstudio");
        int idProgramaEstudio = 0;
        AcademicoProgramaEstudio instance = new AcademicoProgramaEstudio();
        instance.setIdProgramaEstudio(idProgramaEstudio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdAcademico method, of class AcademicoProgramaEstudio.
     */
    @org.junit.Test
    public void testGetIdAcademico() {
        System.out.println("getIdAcademico");
        AcademicoProgramaEstudio instance = new AcademicoProgramaEstudio();
        int expResult = 0;
        int result = instance.getIdAcademico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdAcademico method, of class AcademicoProgramaEstudio.
     */
    @org.junit.Test
    public void testSetIdAcademico() {
        System.out.println("setIdAcademico");
        int idAcademico = 0;
        AcademicoProgramaEstudio instance = new AcademicoProgramaEstudio();
        instance.setIdAcademico(idAcademico);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AcademicoProgramaEstudio.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        AcademicoProgramaEstudio instance = new AcademicoProgramaEstudio();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
