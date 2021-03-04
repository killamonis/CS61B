package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percolationRatio;
    private int sideLength;
    private int experiments;

    /**
     * perform T independent experiments on an N-by-N grid.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.experiments = T;
        this.sideLength = N;
        this.percolationRatio = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolate = pf.make(N);
            double sitesOpened = 0.0;
            while (!percolate.percolates()) {
                int row = StdRandom.uniform(0, sideLength);
                int col = StdRandom.uniform(0, sideLength);
                if (!percolate.isOpen(row, col)) {
                    percolate.open(row, col);
                    sitesOpened = sitesOpened + 1.0;
                }
            }
            percolationRatio[i] = (sitesOpened / (sideLength * sideLength));
        }
    }

    /**
     * sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(this.percolationRatio);
    }

    /**
     * sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(this.percolationRatio);
    }

    /**
     * low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experiments));
    }

    /**
     * high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experiments));
    }
}
