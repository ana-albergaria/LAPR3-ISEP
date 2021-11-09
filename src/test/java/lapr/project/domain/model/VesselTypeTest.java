package lapr.project.domain.model;

import org.junit.Test;

/*
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
 */

public class VesselTypeTest {

    /**
     * Test to ensure Length cannot be under or equal to 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsOver0(){
        VesselType vesselType=new VesselType(70,0,32,13.6,79);
    }

    /**
     * Test to ensure Width cannot be under or equal to 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureWidthIsOver0(){
        VesselType vesselType=new VesselType(70,294,0,13.6,79);
    }

    /**
     * Test to ensure Draft cannot be under or equal to 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureDraftIsOver0(){
        VesselType vesselType=new VesselType(70,294,32,0,79);
    }

    /**
     * Test to ensure Cargo cannot be under or equal to 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCargoIsOver0(){
        VesselType vesselType=new VesselType(70,294,32,13.6,-1);
    }

}
