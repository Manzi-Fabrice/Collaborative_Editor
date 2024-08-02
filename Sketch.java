import java.util.TreeMap;
import java.awt.Color;
import java.awt.Graphics;

public class Sketch {
    TreeMap<Integer, Shape> idShapes;
    int ID = 0;

    /**
     * Constructs a Sketch object.
     * Initializes the TreeMap to store shapes.
     */
    public Sketch() {
        this.idShapes = new TreeMap<Integer, Shape>();
    }

    /**
     * Checks if a point is contained within any shape in the sketch.
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     * @return The ID of the shape containing the point, or -1 if no shape contains the point
     */
    public synchronized int contains(int x, int y) {
        for (int ID : idShapes.descendingKeySet()) {
            if (idShapes.get(ID).contains(x, y)) {
                return ID;
            }
        }
        return -1;
    }

    /**
     * Adds a shape to the sketch.
     * @param s The shape to add
     */
    public synchronized void add(Shape s) {
        idShapes.put(ID, s);
        ID++;
    }

    /**
     * Recolors a shape in the sketch.
     * @param s The shape to recolor
     * @param c The color to set
     */
    public synchronized void recolor(Shape s, Color c) {
        idShapes.get(s).setColor(c);
    }

    /**
     * Deletes a shape from the sketch.
     * @param s The shape to delete
     */
    public synchronized void delete(Shape s) {
        idShapes.remove(s);
    }

    /**
     * Moves a shape in the sketch.
     * @param s The shape to move
     * @param dx The change in x-coordinate
     * @param dy The change in y-coordinate
     */
    public synchronized void move(Shape s, int dx, int dy) {
        idShapes.get(s).moveBy(dx, dy);
    }

    /**
     * Draws all shapes in the sketch using the given graphics context.
     * @param g The graphics context to draw on
     */
    public synchronized void draw(Graphics g) {
        for (int each : idShapes.keySet()) {
            idShapes.get(each).draw(g);
        }
    }

    /**
     * Returns a string representation of the sketch.
     * @return A string representing the sketch
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sketch:\n");
        for (int each : idShapes.keySet()) {
            sb.append(idShapes.get(each).toString()).append("\n");
        }
        return sb.toString();
    }
}
