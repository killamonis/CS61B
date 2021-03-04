package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

	private int w;
	private int h;
	private TETile[][] world;

	public HexWorld(int width, int height) {
		this.w = width;
		this.h = height;

		// initialize world and tiles
		world = new TETile[width][height];
		for (int x = 0; x < width; x += 1) {
			for (int y = 0; y < height; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
	}

	public void addHexagon(TETile tile, int s, int xCorner, int yCorner) {
		int rowSize = s;
		int yDiff = (2 * s) - 1;

		for (int start = s - 1; start >= 0; start--) {
			for (int x = start; x < start + rowSize; x++) {

				if ((xCorner + x >= 0) && (xCorner + x < w)) {
					if ((yCorner < h) && (yCorner >= 0)) {
						world[xCorner + x][yCorner] = tile;
					}
					if ((yCorner - yDiff < h) && (yCorner - yDiff >= 0)) {
						world[xCorner + x][yCorner - yDiff] = tile;
					}
				}

			}
			yCorner --;
			rowSize += 2;
			yDiff -= 2;
		}

		return;
	}

	public void tesselate(int hexSize, int tesRadius) {
		int startingX = (w / 2) - 1;
		int startingY = h - 1;
		int hexSizeY = hexSize * 2;
		int tesWidth = 1;

		int yDiff = hexSizeY * (tesRadius - 1) * 2;

		tesRadius += tesRadius - 1;

		for (int k = 0; k < tesRadius; k++) {
			for (int i = 0; i < tesWidth; i++) {
				addHexagon(randomTile(), hexSize, startingX + (i * ((hexSize * 4) - 2)), startingY);
				addHexagon(randomTile(), hexSize, startingX + (i * ((hexSize * 4) - 2)), startingY - yDiff);
			}
			startingX -= (2 * hexSize) - 1;
			startingY -= hexSize;
			tesWidth += 1;
			yDiff -= 2 * hexSize;
		}

	}

	private static TETile randomTile() {
		Random RANDOM = new Random();
		int tileNum = RANDOM.nextInt(7);
		switch (tileNum) {
			case 0: return Tileset.WALL;
			case 1: return Tileset.MOUNTAIN;
			case 2: return Tileset.GRASS;
			case 3: return Tileset.LOCKED_DOOR;
			case 4: return Tileset.FLOWER;
			case 5: return Tileset.TREE;
			case 6: return Tileset.SAND;
			default: return Tileset.WALL;
		}
	}

	public TETile[][] tiles() {
		return world;
	}

	public static void main(String[] args) {
		int WIDTH = 90;
		int HEIGHT = 60;
		// initialize the tile rendering engine with a window of size WIDTH x HEIGHT
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		// initialize HexWorld
		HexWorld world = new HexWorld(WIDTH, HEIGHT);

		// fills in a block 14 tiles wide by 4 tiles tall
		world.tesselate(3, 3);

		// draws the world to the screen
		ter.renderFrame(world.tiles());
	}

}
