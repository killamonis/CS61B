package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double totalWeight;
    private int numExplored;
    private double exploreTime;
    private LinkedList<Vertex> solution;

    /**
     * Performs the A* search with the use of a MinHeapPQ.
     * Each entry's priority is dictated by the distance
     * traveled and heuristic (estimated distance to goal)
     * distTo calculates total distance traveled at
     *  that vertex
     * edgeTo maps vertices connected by an edge
     * @param input the Graph being searched
     * @param start the starting vertex of our A* search
     * @param end the goal vertex of our A* search
     * @param timeout the upper time limit of our A* search
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end,
                       double timeout) {
        solution = new LinkedList<>();
        DoubleMapPQ<Vertex> queue = new DoubleMapPQ<>();
        HashMap<Vertex, Double> distTo = new HashMap<>();
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
        totalWeight = 0.0;
        numExplored = 0;
        exploreTime = 0.0;
        Stopwatch sw = new Stopwatch();

        distTo.put(start, 0.0);
        queue.add(start, input.estimatedDistanceToGoal(start, end));
        while (queue.size() > 0 && exploreTime < timeout
                && !(queue.getSmallest().equals(end))) {
            Vertex v = queue.removeSmallest();
            numExplored++;

            for (WeightedEdge<Vertex> neighborEdge: input.neighbors(v)) {
                Vertex neighbor = neighborEdge.to();
                double weight = neighborEdge.weight();
                double heuristic = input.estimatedDistanceToGoal(neighbor, end);
                double vDist = distTo.get(v);
                if (!distTo.containsKey(neighbor)
                        || (vDist + weight) < distTo.get(neighbor)) {
                    distTo.put(neighbor, vDist + weight);
                    edgeTo.put(neighbor, v);
                    if (queue.contains(neighbor)) {
                        queue.changePriority(neighbor, distTo.get(neighbor)
                                + heuristic);
                    } else {
                        queue.add(neighbor, distTo.get(neighbor) + heuristic);
                    }
                }
                exploreTime += sw.elapsedTime();
            }
        }

        if (queue.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
        } else if (queue.getSmallest().equals(end)) {
            outcome = SolverOutcome.SOLVED;
            totalWeight = distTo.get(end);
            Vertex newEnd = queue.removeSmallest();
            while (newEnd != start) {
                solution.addFirst(newEnd);
                newEnd = edgeTo.get(newEnd);
            }
            solution.addFirst(start);
        } else {
            outcome = SolverOutcome.TIMEOUT;
        }
    }

    /**
     * @return the outcome of the A* search
     */
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * @return the path taken by the A* search
     */
    public List<Vertex> solution() {
        return solution;
    }

    /**
     * @return the edge weights of our path
     */
    public double solutionWeight() {
        return totalWeight;
    }

    /**
     * @return the number of vertices visited on the A* path
     */
    public int numStatesExplored() {
        return numExplored;
    }

    /**
     * @return the time the A* search takes to get to an outcome
     */
    public double explorationTime() {
        return exploreTime;
    }
}
