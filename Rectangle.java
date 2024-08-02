import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 */
public class Rectangle implements Shape {
	// Fields
	private int x1, y1, x2, y2; // Coordinates of the upper-left and lower-right corners
	private Color color; // Color of the rectangle

	/**
	 * Constructs a rectangle with specified upper-left corner, width, height, and color.
	 * @param x1 The x-coordinate of the upper-left corner
	 * @param x2 The x-coordinate of the lower-right corner
	 * @param color The color of the rectangle
	 */
	public Rectangle(int x1, int x2, Color color) {
		this.x1 = x1;
		this.x2 = x2;
		this.color = color;
	}

	/**
	 * Constructs a rectangle with specified upper-left and lower-right corners and color.
	 * @param x1 The x-coordinate of the upper-left corner
	 * @param y1 The y-coordinate of the upper-left corner
	 * @param x2 The x-coordinate of the lower-right corner
	 * @param y2 The y-coordinate of the lower-right corner
	 * @param color The color of the rectangle
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		// Ensuring x1 is less than or equal to x2 and y1 is less than or equal to y2
		this.x1 = Math.min(x1, x2);
		this.x2 = Math.max(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.y2 = Math.max(y1, y2);
		this.color = color;
	}

	/**
	 * Moves the rectangle by a specified amount in the x and y directions.
	 * @param dx The amount to move in the x direction
	 * @param dy The amount to move in the y direction
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx;
		x2 += dx;
		y1 += dy;
		y2 += dy;
	}

	/**
	 * Returns the color of the rectangle.
	 * @return The color of the rectangle
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the rectangle.
	 * @param color The color to set
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Checks if a point is contained within the rectangle.
	 * @param x The x-coordinate of the point
	 * @param y The y-coordinate of the point
	 * @return True if the point is contained in the rectangle, otherwise false
	 */
	@Override
	public boolean contains(int x, int y) {
		return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}

	/**
	 * Draws the rectangle using the given graphics context.
	 * @param g The graphics context to draw on
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	/**
	 * Returns a string representation of the rectangle.
	 * @return A string representing the rectangle
	 */
	@Override
	public String toString() {
		return "rectangle " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.getRGB();
	}
}
