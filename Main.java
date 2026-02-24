
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
            g.fillOval((int) a.location.x, (int) a.location.y, 8, 8);
        }

        //Obstacle
        g.setColor(Color.BLUE);
        g.fillRect(200, 400, 400, 20);

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
