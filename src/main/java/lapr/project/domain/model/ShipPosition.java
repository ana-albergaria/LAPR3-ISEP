package lapr.project.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
        if (Integer.toString(mmsi).length()!=9)
            throw new IllegalArgumentException("MMSI must hold 9 digits.");
        //ver se está no sistema?
    }

    private void checkBaseDateTime(Date baseDateTime){
        if (baseDateTime==null){
            throw new IllegalArgumentException("Base Date Time cannot be null.");
        }
        //mais...?
    }

    private void checkLat(double lat){
        if (lat<-90 || (lat>90 && lat!=91)){
            throw new IllegalArgumentException("Latitude must be between -90 and 90. It might also be 91 in case of being unavailable.");
        }
    }

    private void checkLon(double lon){
        if (lon<-180 || (lon>180 && lon!=181)){
            throw new IllegalArgumentException("Longitude must be between -180 and 180. It might also be 181 in case of being unavailable.");
        }
    }

    //TESTAR
    private void checkSog(double sog){
        if (sog<0){
            throw new IllegalArgumentException("SOG must be positive.");
        }
    }

    private void checkCog(double cog){
        if (cog<0 || cog>359){
            throw new IllegalArgumentException("COG must be between 0 and 359.");
        }
    }

    private void checkHeading(int heading){
        if (heading<0 || (heading>359 && heading!=511)){
            throw new IllegalArgumentException("Heading must be between 0 and 359. It might also be 511 in case of being unavailable.");
        }
    }

    private void checkTranscieverClass(String transcieverClass){
        if(Objects.isNull(transcieverClass)){
            throw new IllegalArgumentException("Transciever Class cannot be null.");
        }
        if (StringUtils.isBlank(transcieverClass))
            throw new IllegalArgumentException("Transciever Class cannot be blank.");
    }

    //+ toString ?

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return String.format(">> SHIP POSITION%nBase Date Time: %s%n" +
                "Latitude: %f%nLongitude: %f%nSpeed Over Ground (SOG): %f%nCourse Over Ground (COG): %f%n" +
                "Transciever Class: %s%n%n", sdf.format(baseDateTime), lat, lon, sog, cog, heading, transcieverClass);
    }

    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        ShipPosition otherShipPosition = (ShipPosition) otherObject;

        return baseDateTime.equals(otherShipPosition.baseDateTime) &&
                MMSI == otherShipPosition.MMSI &&
                lat == otherShipPosition.lat &&
                lon == otherShipPosition.lon &&
                sog == otherShipPosition.sog &&
                cog == otherShipPosition.cog &&
                heading == otherShipPosition.heading &&
                transcieverClass.equals(otherShipPosition.transcieverClass);
    }

    @Override
    public int compareTo(ShipPosition o) {
        return baseDateTime.compareTo(o.getBaseDateTime());
    }

}
