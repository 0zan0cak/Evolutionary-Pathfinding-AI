
public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void multiply(double n) {
        this.x *= n;
        this.y *= n;
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void normalize() {
        double m = magnitude();
        if (m > 0) {
            this.x /= m;
            this.y /= m;
        }
    }

    public void limit(double max) {
        if (magnitude() > max) {
            normalize();
            multiply(max);
        }
    }
}
