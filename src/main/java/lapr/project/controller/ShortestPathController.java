package lapr.project.controller;

import jdk.internal.net.http.common.Pair;
import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Location;
import lapr.project.domain.store.CapitalStore;
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
        Location beg, end;
        Pair<LinkedList<Location>,Double> result;
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

    public Pair<List<String>,Double> getShortestMaritimePath(double lat1, double lon1, double lat2, double lon2){
        PortStore portStore = this.company.getPortStore();
        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        Location beg, end;
        beg=portStore.getPortByCoordinatesIfExists(lat1,lon1);
        end=portStore.getPortByCoordinatesIfExists(lat2,lon2);
        if (beg==null || end==null || beg==end){
            return null;
        }
        Pair<LinkedList<Location>,Double> result;
        List<String> strings = new ArrayList<>();
        result=freightNetwork.getShortestLandOrSeaPath(beg,end);
        String toAdd;
        for (Location loc:result.first) {
            if (portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude())!=null){
                toAdd="Port: " + portStore.getPortByCoordinatesIfExists(loc.getLatitude(),loc.getLongitude()).getName();
                strings.add(toAdd);
            }
        }
        return new Pair<>(strings, result.second);
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
