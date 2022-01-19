package lapr.project.domain.store;

import lapr.project.domain.model.Capital;

import java.util.ArrayList;
import java.util.List;

public class CapitalStore {

    private List<Capital> capitalsList = new ArrayList<>();

    public void importCapitals(List<Capital> existentCapitals){
        for(Capital capital : existentCapitals){
            if(capital!=null && !capitalsList.contains(capital)){
                capitalsList.add(capital);
            }
        }
    }

    public List<Capital> getCapitalsList(){
        return capitalsList;
    }

    public Capital getCapitalByCoordinatesIfExists(double lat, double lon){
        for (Capital capital : capitalsList){
            if (capital.getLatitude()==lat && capital.getLongitude()==lon){
                return capital;
            }
        }
        return null;
    }

}
