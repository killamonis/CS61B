package bearmaps;
import java.util.*;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    /**
     * Returns the nearest point to the given coordinates.
     */
    @Override
    public Point nearest(double x, double y) {
        double lowest = 10000000;
        double current = 0;
        Point reference = new Point(x, y);
        Point lowestp = new Point(0, 0);
        for (Point p: points) {
            current = p.distance(p, reference);
            if (current < lowest) {
                lowest = current;
                lowestp = p;
            }
        }
        return lowestp;
    }

    /**
     * Test.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);

        NaivePointSet nn = new NaivePointSet(points);
        Point ret = nn.nearest(3.0, 4.0);
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
