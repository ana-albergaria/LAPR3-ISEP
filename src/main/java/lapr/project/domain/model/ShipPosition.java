package lapr.project.domain.model;

import java.time.LocalDate;
import java.util.Date;

public class ShipPosition implements Comparable<ShipPosition> {
    private final int MMSI;

    private final Date baseDateTime;

    private final double lat;

    private final double lon;

    private final double sog;

    private final double cog;

    private final int heading;

    private final String transcieverClass;

    public ShipPosition(int mmsi, Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        checkBaseDateTime(baseDateTime);
        checkLat(lat);
        checkLon(lon);
        checkSog(sog);
        checkCog(cog);
        checkHeading(heading);
        checkTranscieverClass(transcieverClass);
        checkMMSI(mmsi);
        this.MMSI = mmsi;
        this.baseDateTime=baseDateTime;
        this.lat=lat;
        this.lon=lon;
        this.sog=sog;
        this.cog=cog;
        this.heading=heading;
        this.transcieverClass=transcieverClass;
    }


    public Date getBaseDateTime() {
        return baseDateTime;
    }

    public int getMMSI() {
        return MMSI;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getSog() {
        return sog;
    }

    public double getCog() {
        return cog;
    }

    public int getHeading() {
        return heading;
    }

    public String getTranscieverClass() {
        return transcieverClass;
    }

    private void checkMMSI(int mmsi){
        //to develop
    }
    private void checkBaseDateTime(Date baseDateTime){
        //to develop
    }

    private void checkLat(double lat){
        //to develop
    }

    private void checkLon(double lon){
        //to develop
    }

    private void checkSog(double sog){
        //to develop
    }

    private void checkCog(double cog){
        //to develop
    }

    private void checkHeading(int heading){
        //to develop
    }

    private void checkTranscieverClass(String transcieverClass){
        //to develop
    }

    //+ toString ?

    @Override
    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(ShipPosition o) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return baseDateTime.compareTo(o.getBaseDateTime());
    }
}
