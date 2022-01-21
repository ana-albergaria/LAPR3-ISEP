package lapr.project.controller;

import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    /**
     * Constructor for the controller.
     */
    public ShortestPathController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public ShortestPathController(Company company){
        this.company=company;
    }

    /**
     * Get shortest path, given an origin, destination and path type.
     *
     * @param beg beginning location
     * @param end ending location
     * @param path type of path
     * @return a list with the locals of the path
     */
    public List<String> getShortestPath(Location beg, Location end, int path){
        //1: land path | 2: maritime path | 3: land or sea path
        if (path==1){
            return getShortestLandPath(beg,end);
        } else if (path==2){
            return getShortestMaritimePath(beg,end);
        } else if (path==3){
            return getShortestLandOrSeaPath(beg,end);
        }
        return null;
    }

    /**
     * Get the shortest land path given a beginning and ending location.
     * @param beg beginning location
     * @param end ending location
     * @return a list with the locals of the path
     */
    public List<String> getShortestLandPath(Location beg, Location end){
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        LinkedList<Location> result;
        List<String> strings = new ArrayList<>();
        String toAdd;
        if (beg==end || beg==null || end==null){
            return null;
        }
        result=freightNetwork.getShortestLandPath(beg,end);
        for (Location loc:result) {
            if (loc instanceof Port){
                toAdd="Port: " + ((Port) loc).getName();
                strings.add(toAdd);
            } else if (loc instanceof Capital){
                toAdd="Capital: " + ((Capital) loc).getName();
                strings.add(toAdd);
            }
        }
        return strings;
    }

    /**
     * Get the shortest maritime path given a beginning and ending location.
     * @param beg beginning location
     * @param end ending location
     * @return a list with the locals of the path
     */
    public List<String> getShortestMaritimePath(Location beg, Location end){
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        if (beg==null || end==null || beg==end || beg instanceof Capital || end instanceof Capital){
            return null;
        }
        LinkedList<Location> result;
        List<String> strings = new ArrayList<>();
        result=freightNetwork.getShortestMaritimePath(beg,end);
        String toAdd;
        for (Location loc:result) {
            if (loc instanceof Port){ //JÁ DEVE SER PORQUE O METODO NAO VAI METER CAPITAIS
                toAdd="Port: " + ((Port) loc).getName();
                strings.add(toAdd);
            }
        }
        return strings;
    }

    /**
     * Get the shortest land or sea path given a beginning and ending location.
     * @param beg beginning location
     * @param end ending location
     * @return a list with the locals of the path
     */
    public List<String> getShortestLandOrSeaPath(Location beg, Location end){
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        LinkedList<Location> result;
        List<String> strings = new ArrayList<>();
        String toAdd;
        if (beg==end || beg==null || end==null){
            return null;
        }
        result=freightNetwork.getShortestLandOrSeaPath(beg,end);
        for (Location loc:result) {
            if (loc instanceof Port){
                toAdd="Port: " + ((Port) loc).getName();
                strings.add(toAdd);
            } else if (loc instanceof Capital){
                toAdd="Capital: " + ((Capital) loc).getName();
                strings.add(toAdd);
            }
        }
        return strings;
    }

}
