package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;

public class KDTreeTest {

    @Test
    public void testNearest() {
        List<Point> points = generatePoints(2000);
        KDTree kd = new KDTree(points);
        NaivePointSet np = new NaivePointSet(points);
        Point p = randomPoint(points);
        Point kdPoint = kd.nearest(p.getX(), p.getY());
        Point naivePoint = np.nearest(p.getX(), p.getY());
        assertEquals(kdPoint, naivePoint);
    }

    private List<Point> generatePoints(int x) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            double random1 = StdRandom.uniform(-1000, 1000);
            double random2 = StdRandom.uniform(-1000, 1000);
            points.add(new Point(random1, random2));
        }
        return points;
    }

    private Point randomPoint(List<Point> points) {
        int index = StdRandom.uniform(0, points.size());
        Point check = points.get(index);
        return check;
    }

    @Test
    public void constructorTiming() {
        List<Point> points = generatePoints(1000000);
        Stopwatch sw = new Stopwatch();
        KDTree kd = new KDTree(points);
        double kdTime = sw.elapsedTime();

        Stopwatch swNaive = new Stopwatch();
        NaivePointSet np = new NaivePointSet(points);
        double naiveTime = swNaive.elapsedTime();

        System.out.println("KD constructor Time: " + kdTime);
        System.out.println("Naive constructor Time: " + naiveTime);

    }

    @Test
    public void nearestTiming() {
        List<Point> points = generatePoints(500);
        KDTree kd = new KDTree(points);
        NaivePointSet np = new NaivePointSet(points);
        double nearestKD = 0;
        double nearestNaive = 0;
        Stopwatch kdSW = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            Point p = randomPoint(points);
            kd.nearest(p.getX(), p.getY());
        }
        nearestKD = kdSW.elapsedTime();

        Stopwatch naiveSW = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            Point p = randomPoint(points);
            np.nearest(p.getX(), p.getY());
        }
        nearestNaive = naiveSW.elapsedTime();

        System.out.println("KD nearest time: " + nearestKD);
        System.out.println("Naive nearest time: " + nearestNaive);

    }

}
