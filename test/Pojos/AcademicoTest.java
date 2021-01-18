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
public class AcademicoTest {
    
    public AcademicoTest() {
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
     * Test of getIdAcademico method, of class Academico.
     */
    @org.junit.Test
    public void testGetIdAcademico() {
        System.out.println("getIdAcademico");
        Academico instance = new Academico();
        int expResult = 0;
        int result = instance.getIdAcademico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdAcademico method, of class Academico.
     */
    @org.junit.Test
    public void testSetIdAcademico() {
        System.out.println("setIdAcademico");
        int idAcademico = 0;
        Academico instance = new Academico();
        instance.setIdAcademico(idAcademico);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroPersonal method, of class Academico.
     */
    @org.junit.Test
    public void testGetNumeroPersonal() {
        System.out.println("getNumeroPersonal");
        Academico instance = new Academico();
        String expResult = "";
        String result = instance.getNumeroPersonal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumeroPersonal method, of class Academico.
     */
    @org.junit.Test
    public void testSetNumeroPersonal() {
        System.out.println("setNumeroPersonal");
        String numeroPersonal = "";
        Academico instance = new Academico();
        instance.setNumeroPersonal(numeroPersonal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Academico.
     */
    @org.junit.Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Academico instance = new Academico();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Academico.
     */
    @org.junit.Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Academico instance = new Academico();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorreo method, of class Academico.
     */
    @org.junit.Test
    public void testGetCorreo() {
        System.out.println("getCorreo");
        Academico instance = new Academico();
        String expResult = "";
        String result = instance.getCorreo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorreo method, of class Academico.
     */
    @org.junit.Test
    public void testSetCorreo() {
        System.out.println("setCorreo");
        String correo = "";
        Academico instance = new Academico();
        instance.setCorreo(correo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Academico.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Academico instance = new Academico();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
