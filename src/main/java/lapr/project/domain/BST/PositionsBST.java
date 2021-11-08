package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.shared.Constants;

import java.util.*;

public class PositionsBST extends BST<ShipPosition> {
    public void createBstShipPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getStartDate(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return smallestElement().getBaseDateTime();
    }

    public Date getEndDate(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return biggestElement().getBaseDateTime();
    }

    public Double getMaxSog(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        double max = allPos.get(0).getSog();
        for(ShipPosition pos : allPos){
            if(pos.getSog() > max)
                max=pos.getSog();
        }
        return max;
    }

    public Double getMeanCog(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        double mean=0;
        for(ShipPosition pos : allPos){
            mean += pos.getCog();
        }
        return (mean / (double) allPos.size());
    }

    public Double getMeanSog(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();

        double mean=0;
        for(ShipPosition pos : allPos){
            mean += pos.getSog();
        }
        return (mean / (double) allPos.size());
    }

    public Double getDepartLatitude(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return smallestElement().getLat();
    }

    public Double getDepartLongitude(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return smallestElement().getLon();
    }

    public Double getArrivalLatitude(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return biggestElement().getLat();
    }

    public Double getArrivalLongitude(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return biggestElement().getLon();
    }

    public Double getDeltaDistance(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        ShipPosition start = smallestElement();
        ShipPosition end = biggestElement();
        return distanceBetweenInKm(start.getLat(), end.getLat(), start.getLon(), end.getLon());
    }

    public Double getTotalDistance(){
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        double totalDist = 0;
        for(int i = 0; i<allPos.size()-1; i++){
            double latA = allPos.get(i).getLat();
            double latB = allPos.get(i+1).getLat();
            double lonA = allPos.get(i).getLon();
            double lonB = allPos.get(i+1).getLon();
            totalDist += distanceBetweenInKm(latA, latB, lonA, lonB);
        }
        return totalDist;
    }

    /**
     * This uses the ‘haversine’ formula to calculate the great-circle distance between two points – that is, the shortest distance over the earth’s surface – giving an ‘as-the-crow-flies’
     * distance between the points (ignoring any hills they fly over, of course!).
     *
     * Reference: http://www.movable-type.co.uk/scripts/latlong.html
     * @param lat1 latitude of the first point
     * @param lat2 latitude of the second point
     * @param lon1 longitude of the first point
     * @param lon2 longitude of the second point
     * @return the distance in kilometers of the two given points
     */
    protected Double distanceBetweenInKm(Double lat1, Double lat2, Double lon1, Double lon2){
        if(Objects.equals(lat1, lat2) && Objects.equals(lon1, lon2)){
            return 0.0;
        }
        if(lat1 == null || lat2 == null || lon1 == null || lon2 == null){
            throw new IllegalArgumentException("cannot calculate distance with a null value of latitude and/or longitude");
        }
        if(lat1 == 91 || lat2 == 91 || lon1 == 181 || lon2 == 181){
            throw new IllegalArgumentException("Latitude and/or longitude not available");
        }
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2-lat1);
        double deltaLon = Math.toRadians(lon2-lon1);

        double a = (Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)) +
                    Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2) *
                    Math.cos(lat1Rad) * Math.cos(lat2Rad);
        Double c = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));

        return (double) Math.round((Constants.RADIUS_OF_EARTH_IN_METERS * c)/1000);
    }
    /**
     * Method for getting the biggest element of the tree
     * @return the shipPosition element with the biggest(newest) date
     */
    public ShipPosition biggestElement(){
        return biggestElement(root);
    }

    /**
     * inner recursive method for getting the biggest element of the bst
     * @param node node of the tree to iterate
     * @return the biggest node in the tree
     */
    protected ShipPosition biggestElement(Node<ShipPosition> node){
        if(node == null){
            return null;
        }
        if(node.getRight() == null){
            return node.getElement();
        }
        return biggestElement(node.getRight());
    }

    /**
     * Method which call its private method in order to
     * obtain the list of positional messages of the ship chosen by the user.
     *
     * @param initialDate initial date
     * @param finalDate final date
     *
     * @return the list of positional messages of the ship chosen by the user
     */
    public List<String> getPositionalMessages(Date initialDate, Date finalDate) {
        List<String> listPositionalMessages = new ArrayList<>();

        getPositionalMessages(root, listPositionalMessages, initialDate, finalDate);

        return listPositionalMessages;
    }

    /**
     * Method for returning the positional messages of the ship
     * chosen by the user in the wished period of time.
     *
     * @param node the node of the Positions' Tree
     * @param listPositionalMessages list containing the positional messages
     * @param initialDate the initial Date
     * @param finalDate the final Date
     *
     */
    private void getPositionalMessages(Node<ShipPosition> node,
                                       List<String> listPositionalMessages,
                                       Date initialDate,
                                       Date finalDate) {
        //lançar exceções?

        if(node == null)
            return;

        getPositionalMessages(node.getLeft(), listPositionalMessages, initialDate, finalDate);

        Date currentBaseDateTime = node.getElement().getBaseDateTime();

        if( !(currentBaseDateTime.before(initialDate) || currentBaseDateTime.after(finalDate)) ) {
            listPositionalMessages.add(node.getElement().toString());
        }

        getPositionalMessages(node.getRight(), listPositionalMessages, initialDate, finalDate);

    }

    /**
     * method to get the Base Date Time of a ship by it's MMSI
     * @param shipMMSI ship's MMSI
     * @return Base Date Time
     */
    public Date getShipDate(int shipMMSI){
        Date shipDate = null;
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();

        for (ShipPosition pos : allPos) {
            if (pos.getMMSI() == shipMMSI) {
                shipDate = pos.getBaseDateTime();
            }
        }
        return shipDate;
    }
}
