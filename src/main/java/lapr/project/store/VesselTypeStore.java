package lapr.project.store;

import lapr.project.dto.VesselTypeDTO;
import lapr.project.model.VesselType;

import java.util.ArrayList;

public class VesselTypeStore {

    /**
     * List of vessel types.
     */
    private ArrayList<VesselType> vesselTypeList = new ArrayList<>();

    /**
     * Creates a vessel type instance with all the arguments.
     * @param vesselTypeID vessel type ID.
     * @param length vessel type length.
     * @param width vessel type width.
     * @param draft vessel type draft.
     * @param cargo vessel type cargo.
     * @return the created vessel type.
     */
    public VesselType registerVesselType(int vesselTypeID, int length, int width, double draft, int cargo){
        return new VesselType(vesselTypeID, length, width, draft, cargo);
    }

    public VesselType registerVesselType(VesselTypeDTO vesselTypeDTO){
        return new VesselType(vesselTypeDTO.getVesselTypeID(), vesselTypeDTO.getLength(), vesselTypeDTO.getWidth(), vesselTypeDTO.getDraft(), vesselTypeDTO.getCargo());
    }

    public boolean validateVesselType(VesselType vesselType){
        if (vesselType==null)
            return false;
        //+ ver se existe no auth
        return false;
    }

    /*public boolean saveVesselType(VesselType vesselType){
        if (!validateVesselType(vesselType))
            return false;
        if (this.vesselTypeList.add(vesselType)){
            return makeVesselType(vesselType.getVesselTypeID(), vesselType.getLength(), vesselType.getWidth()), vesselType.getDraft(), vesselType.getCargo());
        }
    }*/

    /*public boolean makeVesselType(int vesselTypeID, int length, int width, double draft, int cargo){
        return auth.addVesselType(vesselTypeID,length,width,draft,cargo);
    }*/

}
