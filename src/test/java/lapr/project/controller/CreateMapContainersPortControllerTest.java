package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.dataControllers.CreateMapContainersPortController;
import lapr.project.domain.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CreateMapContainersPortControllerTest {

    private Company comp;
    private CreateMapContainersPortController ctrl;
    private DatabaseConnection databaseConnection;
    private CreateMapContainersPortController createMapContainersPortController;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new CreateMapContainersPortController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        createMapContainersPortController = mock(CreateMapContainersPortController.class);
    }

    /*
    (é considerada a quantidade existente no inicio do dia)
    portID: 16485
    --- NEW ONES: ---
    30/4/2021: +2
        1-current: 2
    1/5/2021: +0
        2-current: 2
    2/5/2021: +0
        3-current: 2
    3/5/2021: +2
        4-current: 4
    4/5/2021: +2
        5-current: 6
    5/5/2021: +2
    5/5/2021: -2
        6-current: 6
    6/5/2021: +0
        7-current: 6
    7/5/2021: +0
        8-current: 6
    --- OLD ONES: ---   (were already in the bootstrap)
    8/5/2021: +1
    8/5/2021: +2
        9-current: 9
    --- NEW ONES: ---
    9/5/2021: +2
        10-current: 11
    10/5/2021 a 30/5/2021: +0
        11 a 31-current: 11
    */

    @Disabled
    @Test
    void testTheMapIsCorrect() throws SQLException {
        int portID=16485;
        int month=5;
        int year=2021;
        int[][] actual = ctrl.getOccupancyMap(portID,month,year);
        int[][] expected = new int[31][2];
        for (int i = 0; i < 31; i++) {
            expected[i][0]=i+1;
        }
        expected[0][1]=2;
        expected[1][1]=2;
        expected[2][1]=2;
        expected[3][1]=4;
        expected[4][1]=6;
        expected[5][1]=6;
        expected[6][1]=6;
        expected[7][1]=6;
        expected[8][1]=9;
        expected[9][1]=11;
        expected[10][1]=11;
        expected[11][1]=11;
        expected[12][1]=11;
        expected[13][1]=11;
        expected[14][1]=11;
        expected[15][1]=11;
        expected[16][1]=11;
        expected[17][1]=11;
        expected[18][1]=11;
        expected[19][1]=11;
        expected[20][1]=11;
        expected[21][1]=11;
        expected[22][1]=11;
        expected[23][1]=11;
        expected[24][1]=11;
        expected[25][1]=11;
        expected[26][1]=11;
        expected[27][1]=11;
        expected[28][1]=11;
        expected[29][1]=11;
        expected[30][1]=11;
        Assertions.assertEquals(expected,actual);
    }

    @Disabled
    @Test
    void testNoMapWhenInvalidPortID() throws SQLException {
        int portID=16486;
        int month=1;
        int year=2021;
        int[][] actual = ctrl.getOccupancyMap(portID,month,year);
        Assertions.assertNull(actual);
    }

    @Disabled
    @Test
    void testNoMapWhenMonthMinor1() throws SQLException {
        int portID=16485;
        int month=0;
        int year=2021;
        int[][] actual = ctrl.getOccupancyMap(portID,month,year);
        Assertions.assertNull(actual);
    }

    @Disabled
    @Test
    void testNoMapWhenMonthBigger12() throws SQLException {
        int portID=16485;
        int month=13;
        int year=2021;
        int[][] actual = ctrl.getOccupancyMap(portID,month,year);
        Assertions.assertNull(actual);
    }

    @Disabled
    @Test
    void testNoMapWhenMonthIsNotCompleted() throws SQLException {
        int portID=16485;
        int month=12;
        int year=2025;
        int[][] actual = ctrl.getOccupancyMap(portID,month,year);
        Assertions.assertNull(actual);
    }

}
