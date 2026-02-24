
import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {

    int width = 800;
    int height = 800;
    int lifeCycle = 0;
    int lifespan = 600;

    Population population;

    public Main() {
        population = new Population(300, 400, 50);

        Timer timer = new Timer(16, e -> {
            population.runPop();
            lifeCycle++;

            if (lifeCycle >= lifespan) {
                population.calculateFitness();
                population.selection();
                lifeCycle = 0;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        //Target
        g.setColor(Color.RED);
        g.fillOval((int) population.target.x - 12, (int) population.target.y - 12, 24, 24);

        //Agents
        g.setColor(new Color(255, 255, 255, 150));
        for (Agent a : population.agents) {
            //g.fillOval((int) a.location.x, (int) a.location.y, 8, 8);

            //Polygon
            /* int r = (int) Math.sqrt(3) * 9;
            int[] xPoints = {
                (int) a.location.x, // Üst
                (int) (a.location.x - r * Math.sqrt(3) / 2), // Sol Alt
                (int) (a.location.x + r * Math.sqrt(3) / 2) // Sağ Alt
            };
            int[] yPoints = {
                (int) (a.location.y + r), // Üst
                (int) (a.location.y - r * 0.5), // Sol Alt
                (int) (a.location.y - r * 0.5) // Sağ Alt
            };
            g.fillPolygon(xPoints, yPoints, 3); */
            //2d Polygon
            Graphics2D g2d = (Graphics2D) g.create();

            double angle = Math.atan2(a.velocity.y, a.velocity.x);

            g2d.translate(a.location.x, a.location.y);
            g2d.rotate(angle);

            int r = 6;
            int[] xPoints = {r * 2, -r, -r}; // Sivri uç sağda (r*2), arka iki köşe solda (-r)
            int[] yPoints = {0, -r, r};      // Sivri uç ortada (0), arka köşeler üst ve altta

            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.dispose();
        }

        //Obstacle
        g.setColor(Color.BLUE);
        g.fillRect(200, 400, 400, 20);

        //Obstacle
        g.setColor(Color.GREEN);
        g.fillRect(580, 500, 20, 100);

        g.setColor(Color.WHITE);
        g.drawString("Generation: " + population.generations, 20, 30);
        g.drawString("LifeCycle: " + lifeCycle + " / " + lifespan, 20, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Evolutionary AI - OzanOcak");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
