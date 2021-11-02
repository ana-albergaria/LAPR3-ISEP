package lapr.project.dto;

import java.util.Date;

public class PositionDTO {

    private final Date baseDateTime;

    private final double lat;

    private final double lon;

    private final double sog;

    private final double cog;

    private final int heading;

    public PositionDTO(Date baseDateTime, double lat, double lon, double sog, double cog, int heading){
        this.baseDateTime=baseDateTime;
        this.lat=lat;
        this.lon=lon;
        this.sog=sog;
        this.cog=cog;
        this.heading=heading;
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

    //+ toString ?

}
