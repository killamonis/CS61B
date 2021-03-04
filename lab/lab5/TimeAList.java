import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    /**
     * Compiles the time it takes for an AList to add N number of items
     */
    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        ArrayList<Integer> Ns = new ArrayList<>(Arrays.asList(1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000));
        ArrayList<Double> times = new ArrayList<>();
        AList<Integer> test = new AList<>();
        double time;
        for (int i = 0; i < Ns.size(); i++) {
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < Ns.get(i); j++ ) {
                test.addLast(j);
            }
            time = sw.elapsedTime();
            times.add(time);
        }
        printTimingTable(Ns, times, Ns);
    }


}
