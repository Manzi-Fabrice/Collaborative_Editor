import java.awt.Color;
import java.awt.Graphics;

/**
 * An ellipse-shaped Shape defined by two corners.
 * The corners are represented by the upper-left (x1, y1) and lower-right (x2, y2) points.
 * The color of the ellipse is also specified.
 * This class provides methods to check if a point is contained within the ellipse,
 * move the ellipse by a specified amount, and draw the ellipse.
 */
public class Ellipse implements Shape {
	private int x1, y1, x2, y2; // Coordinates of the upper-left and lower-right corners
	private Color color; // Color of the ellipse

	/**
	 * Constructs an "empty" ellipse with only one point set so far.
	 * The initial point (x1, y1) and the color of the ellipse are specified.
	 * @param x1 The x-coordinate of the initial point
	 * @param y1 The y-coordinate of the initial point
	 * @param color The color of the ellipse
	 */
	public Ellipse(int x1, int y1, Color color) {
		this.x1 = x1;
		this.x2 = x1;
		this.y1 = y1;
		this.y2 = y1;
		this.color = color;
	}

	/**
	 * Constructs an ellipse defined by two corners.
	 * The upper-left corner is represented by (x1, y1) and the lower-right corner by (x2, y2).
	 * The color of the ellipse is also specified.
	 * @param x1 The x-coordinate of the upper-left corner
	 * @param y1 The y-coordinate of the upper-left corner
	 * @param x2 The x-coordinate of the lower-right corner
	 * @param y2 The y-coordinate of the lower-right corner
	 * @param color The color of the ellipse
	 */
	public Ellipse(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2); // Set corners of the ellipse
		this.color = color;
	}

	/**
	 * Redefines the ellipse based on new corners.
	 * Ensures that the upper-left and lower-right corners are correctly set.
	 * @param x1 The x-coordinate of the upper-left corner
	 * @param y1 The y-coordinate of the upper-left corner
	 * @param x2 The x-coordinate of the lower-right corner
	 * @param y2 The y-coordinate of the lower-right corner
	 */
	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper-left and lower-right corners
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * Checks if the specified point (x, y) is contained within the ellipse.
	 * @param x The x-coordinate of the point
	 * @param y The y-coordinate of the point
	 * @return true if the point is contained within the ellipse, false otherwise
	 */
	@Override
	public boolean contains(int x, int y) {
		double a = (x2 - x1) / 2.0; // Semi-major axis
		double b = (y2 - y1) / 2.0; // Semi-minor axis
		double dx = x - (x1 + a); // Horizontal distance from center
		double dy = y - (y1 + b); // Vertical distance from center

		// Apply the standard geometry formula for ellipse
		return Math.pow(dx / a, 2) + Math.pow(dy / b, 2) <= 1;
	}

	/**
	 * Moves the ellipse by the specified amount in the x and y directions.
	 * @param dx The change in the x-coordinate
	 * @param dy The change in the y-coordinate
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	/**
	 * Gets the color of the ellipse.
	 * @return The color of the ellipse
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the ellipse.
	 * @param color The color to set
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Draws the ellipse using the specified graphics context.
	 * @param g The graphics context to draw on
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x1, y1, x2 - x1, y2 - y1); // Draw filled ellipse
	}

	/**
	 * Returns a string representation of the ellipse.
	 * @return A string representing the ellipse with its coordinates and color
	 */
	@Override
	public String toString() {
		return "ellipse " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.getRGB();
	}
}
