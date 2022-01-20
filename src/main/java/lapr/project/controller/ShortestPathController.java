package lapr.project.controller;

import jdk.internal.net.http.common.Pair;
import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;
import lapr.project.domain.store.CapitalStore;
import lapr.project.domain.store.PortStore;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathController {

    private final Company company;

    public ShortestPathController(){
        this(App.getInstance().getCompany());
    }

    public ShortestPathController(Company company){
        this.company=company;
    }

    public Pair<List<String>,Double> getShortestPath(double lat1, double lon1, double lat2, double lon2, int path){
        //1: land path | 2: maritime path | 3: land or sea path
        if (path==1){
            return getShortestLandPath(lat1,lon1,lat2,lon2);
        } else if (path==2){
            return getShortestMaritimePath(lat1,lon1,lat2,lon2);
        } else if (path==3){
            return getShortestLandOrSeaPath(lat1,lon1,lat2,lon2);
        }
        return null;
    }

    public Pair<List<String>,Double> getShortestLandPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CapitalStore capitalStore = this.company.getCapitalStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Port begPort=null, endPort=null;
        Capital begCap=null, endCap=null;
        Location beg, end;
        if (portStore.getPortByCoordinatesIfExists(lat1,lon1)!=null){
            begPort=portStore.getPortByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                endPort=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                if (begPort==endPort){
                    return null;
                }
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
                if (begCap==endCap){
                    return null;
                }
                //begCap and endCap
            }
        }
        //..... NÃO VAI RETURNAR null se houverem dados!! (abaixo)
        return null;
    }

    public Pair<List<String>,Double> getShortestMaritimePath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Location beg, end;
        Port begPort = portStore.getPortByCoordinatesIfExists(lat1,lon1);
        Port endPort = portStore.getPortByCoordinatesIfExists(lat2,lon2);
        if (begPort==null || endPort==null || begPort==endPort){
            return null;
        }
        //..... NÃO VAI RETURNAR null se houverem dados!! (abaixo)
        return null;
    }

    public Pair<List<String>,Double> getShortestLandOrSeaPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CapitalStore capitalStore = this.company.getCapitalStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Pair<LinkedList<Location>,Double> result;
        List<String> strings = new ArrayList<>();
        String toAdd;
        Location beg, end;
        if (portStore.getPortByCoordinatesIfExists(lat1,lon1)!=null){
            beg=portStore.getPortByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                if (beg==end){
                    return null;
                }
                //begPort and endPort
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result.first) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return new Pair<>(strings, result.second);
            } else if (capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                //begPort and endCap
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result.first) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return new Pair<>(strings, result.second);
            }
        } else if (capitalStore.getCapitalByCoordinatesIfExists(lat1,lon1)!=null){
            beg=capitalStore.getCapitalByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                //begCap and endPort
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result.first) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return new Pair<>(strings, result.second);
            } else if (capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=capitalStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                if (beg==end){
                    return null;
                }
                //begCap and endCap
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result.first) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + capitalStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return new Pair<>(strings, result.second);
            }
        }
        return null;
    }

}
