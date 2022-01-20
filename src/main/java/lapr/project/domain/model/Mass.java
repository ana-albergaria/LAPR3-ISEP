package lapr.project.domain.model;

public class Mass {
    /**
     * Distance from the origin until the center of mass
     */
    private double x;
    /**
     * The length of the mass
     */
    private double length;
    /**
     * The width of the mass
     */
    private double width;

    public Mass(double x, double length, double width) {
        this.x = x;
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }

    public double getX() {
        return x;
    }
}
