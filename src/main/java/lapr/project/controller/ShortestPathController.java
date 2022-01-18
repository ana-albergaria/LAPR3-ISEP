package lapr.project.controller;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Port;
import lapr.project.domain.store.CapitalStore;
import lapr.project.domain.store.PortStore;

import java.util.List;

public class ShortestPathController {

    private final Company company;

    public ShortestPathController(){
        this(App.getInstance().getCompany());
    }

    public ShortestPathController(Company company){
        this.company=company;
    }

    public List<String> getShortestPath(double lat1, double lon1, double lat2, double lon2, int path){
        //1: land path | 2: maritime path | 3: land or sea path
        if (path==1){
            return getShortestLandPath(lat1,lon1,lat2,lon2);
        } else if (path==2){
            return getShortestMaritimePath(lat1,lon1,lat2,lon2);
        } /*else if (path==3){
            return getShortestLandOrSeaPath(lat1,lon1,lat2,lon2);
        }*/
        return null;
    }

    public List<String> getShortestLandPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CapitalStore capitalStore = this.company.getCapitalStore();
        Port begPort=null, endPort=null;
        Capital begCap=null, endCap=null;
        if (portStore.getPortByCoordinatesIfExists(lat1,lon1)!=null){
            begPort=portStore.getPortByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                endPort=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                //begPort and endPort
            } else if (capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                endCap=capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                //begPort and endCap
            }
        } else if (capitalStore.getCapitalByCoordinatesIfExists(lat1,lon1)!=null){
            begCap=capitalStore.getCapitalByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                endPort=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                //begCap and endPort
            } else if (capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                endCap=capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                //begCap and endCap
            }
        }
        return null;
    }

    public List<String> getShortestMaritimePath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        Port begPort = portStore.getPortByCoordinatesIfExists(lat1,lon1);
        Port endPort = portStore.getPortByCoordinatesIfExists(lat2,lon2);
        if (begPort==null || endPort==null){
            return null;
        }
        //...
        return null; //não é assim
    }

    /*public List<String> getShortestLandOrSeaPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CapitalStore capitalStore = this.company.getCapitalStore();
    }*/

}
