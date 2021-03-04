package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;
import edu.princeton.cs.algs4.TrieST;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private List<Point> points;
    private HashMap<Point, Node> nodePoint;
    private TrieST<HashSet<Node>> trie;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);

        List<Node> nodes = this.getNodes();
        this.points = new ArrayList<>();
        this.nodePoint = new HashMap<>();
        this.trie = new TrieST();
        for (Node n: nodes) {
            if (neighbors(n.id()).size() > 0) {
                Point p = new Point(n.lon(), n.lat());
                nodePoint.put(p, n);
                points.add(p);
            }
            String name = n.name();
            if (name != null) {
                String cleanName = cleanString(name);
                if (!trie.contains(cleanName)) {
                    trie.put(cleanName, new HashSet<Node>());
                }
                trie.get(cleanName).add(n);
            }

        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        WeirdPointSet wps = new WeirdPointSet(points);
        Point nearestP = wps.nearest(lon, lat);
        Node nearestN = nodePoint.get(nearestP);
        return nearestN.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> locations = new LinkedList();
        Iterable<String> prefixFound = trie.keysWithPrefix(cleanString(prefix));
        for (String s: prefixFound) {
            HashSet<Node> nodes = trie.get(s);
            if (nodes != null) {
                for (Node n: nodes) {
                    locations.add(n.name());
                }
            }
        }
        return locations;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> locs = new LinkedList<>();
        String cleanName = cleanString(locationName);
        if (trie.contains(cleanName)) {
            for (Node n: trie.get(cleanName)) {
                Map<String, Object> locInfo = new HashMap<>();
                locInfo.put("lat", n.lat());
                locInfo.put("lon", n.lon());
                locInfo.put("name", n.name());
                locInfo.put("id", n.id());
                locs.add(locInfo);
            }
        }
        return locs;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
