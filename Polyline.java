import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * This class represents a polyline shape composed of multiple line segments.
 * Each segment is represented by its starting and ending points.
 */
public class Polyline implements Shape {
	// Fields
	private int x1, y1, x2, y2;        // Coordinates of the polyline
	private Color color;               // Color of the polyline
	private ArrayList<Segment> overallList;  // List of line segments forming the polyline

	/**
	 * Constructs a Polyline with a single point and a color.
	 * @param x1 The x-coordinate of the point
	 * @param y1 The y-coordinate of the point
	 * @param color The color of the polyline
	 */
	public Polyline(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;    // Start and end point are the same initially
		this.y1 = y1; this.y2 = y1;    // Start and end point are the same initially
		this.color = color;
	}

	/**
	 * Constructs a Polyline from a list of segments and a color.
	 * @param givenSegments The list of segments forming the polyline
	 * @param c The color of the polyline
	 */
	public Polyline(ArrayList<Segment> givenSegments, Color c) {
		overallList = givenSegments;
		color = c;
	}

	/**
	 * Moves the polyline by a given amount in the x and y directions.
	 * @param dx The change in x-coordinate
	 * @param dy The change in y-coordinate
	 */
	@Override
	public void moveBy(int dx, int dy) {
		// Iterate through each segment of the polyline and move it by the specified amount
		for (Segment segment : overallList) {
			segment.moveBy(dx, dy);
		}
	}

	/**
	 * Returns the color of the polyline.
	 * @return The color of the polyline
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the polyline.
	 * @param color The color to set
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Checks if a point is contained within the polyline.
	 * @param x The x-coordinate of the point
	 * @param y The y-coordinate of the point
	 * @return True if the point is contained in the polyline, otherwise false
	 */
	@Override
	public boolean contains(int x, int y) {
		// Check if the point (x, y) is contained in any of the segments
		for (Segment segment : overallList) {
			if (segment.contains(x, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Draws the polyline using the given graphics context.
	 * @param g The graphics context to draw on
	 */
	@Override
	public void draw(Graphics g) {
		// Set the color and draw each segment
		g.setColor(color);
		for (Segment segment : overallList) {
			segment.draw(g);
		}
	}

	/**
	 * Returns a string representation of the polyline.
	 * @return A string representing the polyline
	 */
	@Override
	public String toString() {
		return "polyline " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.getRGB();
	}
}
