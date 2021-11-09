package lapr.project.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VesselTypeTest {

    /**
     * Test to ensure Length cannot be under or equal to 0.
     */
    @Test
    public void ensureLengthIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new VesselType(70,0,32,13.6,79));
        assertEquals("Length needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Width cannot be under or equal to 0.
     */
    @Test
    public void ensureWidthIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new VesselType(70,294,0,13.6,79));
        assertEquals("Width needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Draft cannot be under or equal to 0.
     */
    @Test
    public void ensureDraftIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new VesselType(70,294,32,0,79));
        assertEquals("Draft needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Cargo cannot be under or equal to 0.
     */
    @Test
    public void ensureCargoIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new VesselType(70,294,32,13.6,-1));
        assertEquals("Cargo cannot be negative.", thrown.getMessage());
    }

}
