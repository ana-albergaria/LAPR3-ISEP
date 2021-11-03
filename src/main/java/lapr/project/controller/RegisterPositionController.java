package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Position;
import lapr.project.dto.PositionDTO;

import java.util.Date;

public class RegisterPositionController {

    private final Company company;

    private Position position;

    public RegisterPositionController(){
        this(App.getInstance().getCompany());
    }

    public RegisterPositionController(Company application){
        this.company=application;
        this.position=null;
    }

    public boolean registerPosition(Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean registerPosition(PositionDTO positionDTO){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean savePosition(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //get position (?)

}
