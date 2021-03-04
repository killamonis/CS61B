package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSites;
    private boolean[] sites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufBackWash;
    private int sideLength;
    private int topIndex;
    private int bottomIndex;

    /**
     * create N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.openSites = 0;
        this.sideLength = N;
        this.sites = new boolean[N * N];
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.ufBackWash = new WeightedQuickUnionUF(N * N + 1);
        this.topIndex = N * N;
        this.bottomIndex = N * N + 1;
    }

    /**
     * Converts a 2D array entry into a 1D array entry.
     */
    private int xyTo1D(int row, int col) {
        return row * sideLength + col;
    }

    /**
     * Connects the input site to its open neighbors.
     */
    private void checkNeighbors(int row, int col) {
        int current = xyTo1D(row, col);
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            int left = xyTo1D(row, col - 1);
            uf.union(current, left);
            ufBackWash.union(current, left);
        }
        if (col + 1 < sideLength && isOpen(row, col + 1)) {
            int right = xyTo1D(row, col + 1);
            uf.union(current, right);
            ufBackWash.union(current, right);
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            int bottom = xyTo1D(row - 1, col);
            uf.union(current, bottom);
            ufBackWash.union(current, bottom);
        }
        if (row + 1 < sideLength && isOpen(row + 1, col)) {
            int top = xyTo1D(row + 1, col);
            uf.union(current, top);
            ufBackWash.union(current, top);
        }
    }
    /**
     * open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength) {
            throw new IndexOutOfBoundsException();
        }
        if (this.isOpen(row, col)) {
            return;
        }
        int index = xyTo1D(row, col);
        sites[index] = true;
        checkNeighbors(row, col);
        openSites++;
        if (row == 0) {
            uf.union(index, topIndex);
            ufBackWash.union(index, topIndex);
        }
        if (row == sideLength - 1) {
            uf.union(index, bottomIndex);
        }
    }

    /**
     * is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return sites[index];
    }

    /**
     * is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength) {
            throw new IndexOutOfBoundsException();
        }
        int current = xyTo1D(row, col);
        return ufBackWash.connected(current, topIndex);
    }

    /**
     * number of open sites.
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        if (sideLength == 1 && !isOpen(0, 0)) {
            return false;
        }
        return uf.connected(topIndex, bottomIndex);
    }

    /**
     * use for unit testing.
     */
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(2, 4);
        test.open(3, 4);
        System.out.println(test.isOpen(3, 4));
        System.out.println(test.isFull(3, 4));
        System.out.println(test.numberOfOpenSites());
        test.open(2, 3);
        test.open(1, 3);
        test.open(1, 2);
        test.open(1, 0);
        test.open(0, 0);
        System.out.println(test.isFull(1, 0));
        System.out.println(test.isFull(1, 3));
        System.out.println(test.numberOfOpenSites());
        test.open(4, 4);
        System.out.println(test.isFull(4, 4));
        test.open(4, 0);
        test.open(4, 1);
        test.open(1, 1);
        System.out.println(test.isFull(4, 4));
        System.out.println(test.percolates());
        System.out.println(test.isFull(4, 0));
        Percolation test2 = new Percolation(2);
        System.out.println(test2.percolates());
    }
}
