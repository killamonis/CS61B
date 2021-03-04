# Build Your Own World Design Document

**Partner 1:** Isaac Echeto

**Partner 2:** Monis Mohiuddin

**Video Walkthrough** https://www.youtube.com/watch?v=OTI5Bobs39M&t=116s
## Classes and Data Structure

**Engine:** Takes in and processes user input.
Uses this input to generate and interact with
the world.

Variables:
* TERenderer ter
* TETile[][] world
* HashSet<Room> rooms
* static final int WIDTH
* static final int HEIGHT

**Room:** A room in the world created by the
Engine.

Variables:
* int leftX
* int rightX
* int upY
* int downY
* int width
* int height
* int centerX
* int centerY

**Hallway:** A hallway that connects two rooms
together, functions very similarly to a Room.

Variables:
* int leftX
* int rightX
* int upY
* int downY
* int width
* int height
* int centerX
* int centerY

## Algorithms

## Persistence
