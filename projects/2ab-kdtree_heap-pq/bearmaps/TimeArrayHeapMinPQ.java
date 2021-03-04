package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeArrayHeapMinPQ {
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
        timeArrayHeapConstruction(31250);
    }

    public static void timeArrayHeapConstruction(int ops) {
        ArrayList<Integer> Ns = new ArrayList<>(Arrays.asList(31250, 62500, 125000, 250000, 500000, 1000000, 2000000, 4000000));
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> opCount = new ArrayList<>(Arrays.asList(ops, ops, ops, ops, ops, ops, ops, ops));
        double time;
        for (int i = 0; i < Ns.size(); i++) {
            ArrayHeapMinPQ<Integer> amh = new ArrayHeapMinPQ<>();
            for (int j = 0; j < Ns.get(i); j++) {
                amh.add(j, j-1);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < ops; j++) {
                amh.changePriority(getRandomItem(Ns.get(i)), getRandomPriority());
            }
            time = sw.elapsedTime();
            times.add(time);
        }
        printTimingTable(Ns, times, opCount);
    }

    public static int getRandomItem(int size) {
        return StdRandom.uniform(size);
    }

    public static double getRandomPriority() {
        return StdRandom.uniform(-100000, 100000);
    }
}
