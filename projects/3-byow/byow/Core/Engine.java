package byow.Core;

//import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;

import java.util.Random;

public class Engine {
    //TERenderer ter = new TERenderer();
    private TETile[][] world;
    private ArrayList<Room> rooms;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * initializes for a new Engine() object
     */
    public Engine() {
        initialize();
    }

    /**
     * initializes the world TETile[][], TERenderer, and rooms list
     */
    private void initialize() {
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        //ter.initialize(WIDTH, HEIGHT);
        rooms = new ArrayList<>();
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     *
     * @source for iterating through a string and Char static methods, StackOverflow
     */
    public TETile[][] interactWithInputString(String input) {
        boolean searchingSeed = false;
        int seed = 0;
        for (char c : input.toCharArray()) {
            if (Character.toLowerCase(c) == 'n') {
                initialize();
                searchingSeed = true;
            }
            if ((searchingSeed) && Character.isDigit(c)) {
                seed *= 10;
                seed += (int) (c - '0');
            }
            if ((searchingSeed) && (Character.toLowerCase(c) == 's')) {
                searchingSeed = false;
                buildSeedWorld(seed);
            }
        }


        //ter.renderFrame(world);
        return world;
    }

    private class Room {
        private int leftX;
        private int rightX;
        private int upY;
        private int downY;
        private int width;
        private int height;
        private int centerX;
        private int centerY;

        private Room(int xLeft, int yDown, int w, int h) {
            leftX = xLeft;
            width = w;
            rightX = leftX + width + 1;
            downY = yDown;
            height = h;
            upY = downY + height + 1;
            centerX = ((rightX - leftX) / 2) + leftX;
            centerY = ((upY - downY) / 2) + downY;
        }

        /**
         * Implements this Room into the world and adds to HashSet rooms
         */
        private void build() {
            buildWall(leftX, rightX, downY, upY);
            buildFloor(leftX + 1, rightX - 1, downY + 1, upY - 1);
            rooms.add(this);
        }

        /**
         * Checks all points in this Room to see if anything already exists there
         * @return true if it does overlap
         */
        private boolean overlaps() {
            for (int x = leftX; x <= rightX; x++) {
                for (int y = downY; y <= upY; y++) {
                    if (!isNothing(x, y)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Compares two rooms to see which has the lowest x-value
         * @param rm1
         * @param rm2
         * @return room with lowest x-value
         */
        private Room xMin(Room rm1, Room rm2) {
            if (rm1.centerX < rm2.centerX) {
                return rm1;
            }
            return rm2;
        }

        /**
         * Compares two rooms to see which has the highest x-value
         * @param rm1
         * @param rm2
         * @return room with highest x-value
         */
        private Room xMax(Room rm1, Room rm2) {
            if (rm1.centerX < rm2.centerX) {
                return rm2;
            }
            return rm1;
        }

        /**
         * Compares two rooms to see which has the lowest y-value
         * @param rm1
         * @param rm2
         * @return room with lowest y-value
         */
        private Room yMin(Room rm1, Room rm2) {
            if (rm1.centerY < rm2.centerY) {
                return rm1;
            }
            return rm2;
        }

        /**
         * Compares two rooms to see which has the highest y-value
         * @param rm1
         * @param rm2
         * @return room with highest y-value
         */
        private Room yMax(Room rm1, Room rm2) {
            if (rm1.centerY < rm2.centerY) {
                return rm2;
            }
            return rm1;
        }

        /**
         * Builds a hallway from this Room to a given other room
         * @param other
         */
        private void connect(Room other) {
            if (other == null) {
                return;
            }

            Room bottom = yMin(this, other);
            Room top = yMax(this, other);
            Room left = xMin(this, other);
            Room right = xMax(this, other);

            Hallway hwVertical = new Hallway(bottom.centerX, bottom.centerY, 1,
                    Math.abs(top.centerY - bottom.centerY));
            hwVertical.build();

            Hallway hwHorizontal = new Hallway(left.centerX, top.centerY,
                    Math.abs(right.centerX - left.centerX + 1), 1);
            hwHorizontal.build();
        }
    }

    private class Hallway {
        private int leftX;
        private int rightX;
        private int upY;
        private int downY;
        private int width;
        private int height;
        private int centerX;
        private int centerY;

        private Hallway(int xLeft, int yDown, int w, int h) {
            leftX = xLeft;
            width = w;
            rightX = leftX + width + 1;
            downY = yDown;
            height = h;
            upY = downY + height + 1;
            centerX = ((rightX - leftX) / 2) + leftX;
            centerY = ((upY - downY) / 2) + downY;
        }

        private void build() {
            buildWall(leftX, rightX, downY, upY);
            buildFloor(leftX + 1, rightX - 1, downY + 1, upY - 1);
        }
    }

    /**
     * Reads a given seed and builds a world from it
     * @param seed
     */
    private void buildSeedWorld(int seed) {
        Random random = new Random(seed);
        int numOfRooms = RandomUtils.uniform(random, 15, 25);
        int xC;
        int yC;
        int w;
        int h;
        Room rm;
        while (numOfRooms > 0) {
            w = RandomUtils.uniform(random, 2, 9);
            h = RandomUtils.uniform(random, 2, 9);
            xC = RandomUtils.uniform(random, 0, WIDTH - 2 - w);
            yC = RandomUtils.uniform(random, 0, HEIGHT - 2 - h);

            rm = new Room(xC, yC, w, h);
            if (!rm.overlaps()) {
                rm.build();
                numOfRooms -= 1;
            }
        }
        buildHallways();

    }

    /**
     * Builds hallways connecting all of the rooms in the world
     */
    private void buildHallways() {
        Room temp = null;
        for (Room room : rooms) {
            room.connect(temp);
            temp = room;
        }
    }

    /**
     * Builds a block of wall at the given points
     * @param xStart left-most x
     * @param xEnd right-most x
     * @param yStart lowest y
     * @param yEnd highest y
     */
    private void buildWall(int xStart, int xEnd, int yStart, int yEnd) {
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                if (!isFloor(x, y)) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }

    /**
     * Builds a block of floor at the given points
     * @param xStart left-most x
     * @param xEnd right-most x
     * @param yStart lowest y
     * @param yEnd highest y
     */
    private void buildFloor(int xStart, int xEnd, int yStart, int yEnd) {
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    /**
     * Checks a given point to see if there is a NOTHING tile there
     * @param x
     * @param y
     * @return true if there is nothing there
     */
    private boolean isNothing(int x, int y) {
        return world[x][y].equals(Tileset.NOTHING);
    }

    /**
     * Check if a given point is a floor tile
     * @param x
     * @param y
     * @return true if the given point is a floor
     */
    private boolean isFloor(int x, int y) {
        return world[x][y].equals(Tileset.FLOOR);
    }

    /**
     * Check if a given point is a wall tile
     * @param x
     * @param y
     * @return true if the given point is a wall
     */
    private boolean isWall(int x, int y) {
        return world[x][y].equals(Tileset.WALL);
    }

}
