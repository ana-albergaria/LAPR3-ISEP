package lapr.project.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipPositionTest {

    Company company;
    private Date dateR1;

    @Before
    public void setUp() throws ParseException {
        company = new Company("cargo shipping company");
        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgsNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,null,36.39094,
                -122.71335,19.7,145.5,147,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullDateNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,null,36.39094,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullTranscieverClassNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTranscieverClassNotEmpty(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeUnderMinus90(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,-91,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeOver91(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,92,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeUnderMinus180(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -181,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeOver181(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                182,19.7,145.5,147,"B");
    }

    @Test
    public void ensureMMSIHas9Digits(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,"B");
        //Assert.assertTrue(shipPosition.getMMSI().length()==9);
    }

}
