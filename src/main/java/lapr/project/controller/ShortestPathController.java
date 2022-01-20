package lapr.project.controller;

import jdk.internal.net.http.common.Pair;
import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Location;
import lapr.project.domain.store.CountryStore;
import lapr.project.domain.store.PortStore;

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

    public List<String> getShortestPath(double lat1, double lon1, double lat2, double lon2, int path){
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

    public List<String> getShortestLandPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CountryStore countryStore = this.company.getCountryStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Location beg, end;
        LinkedList<Location> result;
        List<String> strings = new ArrayList<>();
        String toAdd;
        if (portStore.getPortByCoordinatesIfExists(lat1,lon1)!=null){
            beg=portStore.getPortByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                if (beg==end){
                    return null;
                }
                //begPort and endPort
                result=freightNetwork.getShortestLandPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            } else if (countryStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=countryStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                //begPort and endCap
                result=freightNetwork.getShortestLandPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            }
        } else if (countryStore.getCapitalByCoordinatesIfExists(lat1,lon1)!=null){
            beg=countryStore.getCapitalByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                //begCap and endPort
                result=freightNetwork.getShortestLandPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            } else if (countryStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=countryStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                if (beg==end){
                    return null;
                }
                //begCap and endCap
                result=freightNetwork.getShortestLandPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            }
        }
        return null;
    }

    public List<String> getShortestMaritimePath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Location beg, end;
        beg=portStore.getPortByCoordinatesIfExists(lat1,lon1);
        end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
        if (beg==null || end==null || beg==end){
            return null;
        }
        LinkedList<Location> result;
        List<String> strings = new ArrayList<>();
        result=freightNetwork.getShortestMaritimePath(beg,end);
        String toAdd;
        for (Location loc:result) {
            if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                strings.add(toAdd);
            }
        }
        return strings;
    }

    public List<String> getShortestLandOrSeaPath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        CountryStore countryStore = this.company.getCountryStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        LinkedList<Location> result;
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
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            } else if (countryStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=countryStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                //begPort and endCap
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            }
        } else if (countryStore.getCapitalByCoordinatesIfExists(lat1,lon1)!=null){
            beg=countryStore.getCapitalByCoordinatesIfExists(lat1,lon1);
            if (portStore.getPortByCoordinatesIfExists(lat2,lon2)!=null){
                end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
                //begCap and endPort
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            } else if (countryStore.getCapitalByCoordinatesIfExists(lat2,lon2)!=null){
                end=countryStore.getCapitalByCoordinatesIfExists(lat2,lon2);
                if (beg==end){
                    return null;
                }
                //begCap and endCap
                result=freightNetwork.getShortestLandOrSeaPath(beg,end);
                for (Location loc:result) {
                    if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    } else if (countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                        toAdd="Capital: " + countryStore.getCapitalByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                        strings.add(toAdd);
                    }
                }
                return strings;
            }
        }
        return null;
    }

}
