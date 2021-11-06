package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.shared.Constants;

import java.time.LocalDate;
import java.util.*;

public class PositionsBST extends BST<ShipPosition> {
    public void createBstShipPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ShipPosition> getPositionalMessages(Date initialDate, Date finalDate) {
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
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        if(allPos == null){
            return null;
        }
        double max = allPos.get(0).getSog();
        for(ShipPosition pos : allPos){
            if(pos.getSog() > max)
                max=pos.getSog();
        }
        return max;
    }

    public Double getMeanCog(){
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        if(allPos == null){
            throw new IllegalArgumentException("List is empty");
        }
        double mean=0;
        for(ShipPosition pos : allPos){
            mean += pos.getCog();
        }
        return (mean / (double) allPos.size());
    }

    public Double getMeanSog(){
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        if(allPos == null){
            throw new IllegalArgumentException("List is empty");
        }
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
        ShipPosition start = smallestElement();
        ShipPosition end = biggestElement();
        return distanceBetweenInKm(start.getLat(), end.getLat(), start.getLon(), end.getLon());
    }

    public Double getTotalDistance(){
        List<ShipPosition> allPos = (List<ShipPosition>) inOrder();
        for(ShipPosition p: allPos){
            System.out.println(p.getBaseDateTime());
        }
        return  null;
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

    private void getPositionalMessages(Node<ShipPosition> node,
                                       List<String> listPositionalMessages,
                                       Date initialDate,
                                       Date finalDate) {
        /*if(node == null)
            return;

        getPositionalMessages(node.getLeft(), listPositionalMessages, initialDate, finalDate);

        Date currentBaseDateTime = node.getElement().getBaseDateTime();

        if( !(currentBaseDateTime.before(initialDate) || currentBaseDateTime.after(finalDate)) ) {
            listPositionalMessages.add(node.getElement().toString());
        }

        getPositionalMessages(node.getRight(), listPositionalMessages, initialDate, finalDate);
        
         */


        //criar e retornar Map só no controller

        //throw new UnsupportedOperationException("Not supported yet.");


    }
}
