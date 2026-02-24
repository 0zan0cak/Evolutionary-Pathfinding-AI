
public class Agent {

    public Vector2D location;
    public Vector2D velocity;
    public Vector2D acceleration;
    public DNA dna;
    public int geneCounter = 0;
    public double fitness = 0;

    public boolean complated = false;
    public boolean crashed = false;

    public Agent(double startX, double startY) {
        location = new Vector2D(startX, startY);
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        dna = new DNA();
    }

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public void update(Vector2D target) {
        double dist = Math.sqrt(Math.pow(location.x - target.x, 2) + Math.pow(location.y - target.y, 2));
        if (dist < 16) {
            complated = true;
        }

        //Crash
        if (location.x < 0 || location.x > 800 || location.y < 0 || location.y > 800) {
            crashed = true;
        }

        //Wall - Obstacle
        if (location.x > 200 && location.x < 600 && location.y > 400 && location.y < 420) {
            crashed = true;
        }

        //Wall - Obstacle
        if (location.x > 580 && location.x < 600 && location.y > 500 && location.y < 600) {
            crashed = true;
        }

        if (!complated && !crashed && geneCounter < dna.lifespan) {
            applyForce(dna.genes[geneCounter]);
            geneCounter++;

            velocity.add(acceleration);
            velocity.limit(5.0);
            location.add(velocity);
            acceleration.multiply(0);
        }
    }
}
