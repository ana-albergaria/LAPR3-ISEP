package lapr.project.data.dataControllers;

import lapr.project.data.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripThresholdConrtollerTest {
    private DatabaseConnection databaseConnection;
    private TripThresholdController ctrl;

    @BeforeEach
    void setUp() {
        databaseConnection = mock(DatabaseConnection.class);
        ctrl = mock(TripThresholdController.class);
    }

    @Test
    void getVoyagesList(){
        List<Integer> expList = new LinkedList<>();
        expList.add(73929);
        expList.add(84901);
        expList.add(57328);
        expList.add(71434);
        expList.add(62258);
        expList.add(59448);
        expList.add(64050);
        expList.add(63798);
        expList.add(89070);
        expList.add(33710);
        expList.add(29960);
        expList.add(76372);
        expList.add(98507);
        expList.add(29440);
        expList.add(36929);
        expList.add(90333);
        expList.add(1334);
        expList.add(89964);
        expList.add(68626);
        expList.add(77575);
        expList.add(55237);
        expList.add(69482);
        expList.add(82933);
        expList.add(15217);
        expList.add(25803);
        expList.add(5230);
        expList.add(47090);
        expList.add(53526);
        expList.add(80020);
        expList.add(86152);
        expList.add(13398);
        expList.add(91586);
        expList.add(22209);
        expList.add(57425);
        expList.add(57052);
        expList.add(35642);
        expList.add(6999);
        expList.add(13631);
        expList.add(25226);
        expList.add(16347);
        expList.add(54675);
        expList.add(42648);
        expList.add(89586);
        expList.add(59324);
        expList.add(46988);
        expList.add(41359);
        expList.add(56510);
        expList.add(88427);
        expList.add(98872);
        expList.add(95954);
        expList.add(47433);
        expList.add(83011);
        expList.add(68138);
        expList.add(29045);
        expList.add(10710);
        expList.add(59732);
        expList.add(74590);
        expList.add(7326);
        expList.add(5468);
        expList.add(70007);
        expList.add(81342);
        expList.add(67132);
        expList.add(68139);
        expList.add(81347);
        expList.add(81901);
        expList.add(81903);
        expList.add(81904);
        expList.add(81905);

        List<Integer> actList = new LinkedList<>();
        try {
            when(ctrl.getVoyagesBelowThreshold()).thenReturn(expList);
            actList = ctrl.getVoyagesBelowThreshold();
            Assertions.assertEquals(expList, actList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
