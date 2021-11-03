package lapr.project.domain.model;

import java.util.Date;

public class Position {

    private final Date baseDateTime;

    private final double lat;

    private final double lon;

    private final double sog;

    private final double cog;

    private final int heading;

    private final String transcieverClass;

    public Position(Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        checkBaseDateTime(baseDateTime);
        checkLat(lat);
        checkLon(lon);
        checkSog(sog);
        checkCog(cog);
        checkHeading(heading);
        checkTranscieverClass(transcieverClass);
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

}
