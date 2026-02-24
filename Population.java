
import java.util.ArrayList;
import java.util.Random;

public class Population {

    public Agent[] agents;
    public ArrayList<Agent> matingPool;
    public Vector2D target;
    public int generations;
    public double mutationRate = 0.02;
    Random rand = new Random();

    public Population(int popSize, double targetX, double targetY) {
        agents = new Agent[popSize];
        target = new Vector2D(targetX, targetY);
        generations = 1;
        matingPool = new ArrayList<>();

        for (int i = 0; i < popSize; i++) {
            agents[i] = new Agent(400, 700);
        }
    }

    public void calculateFitness() {
        double maxFitness = 0;

        for (Agent a : agents) {
            double distance = Math.sqrt(Math.pow(target.x - a.location.x, 2) + Math.pow(target.y - a.location.y, 2));
            a.fitness = 1 / (distance + 1);

            if (a.complated) {
                a.fitness *= 10;
            }
            if (a.crashed) {
                a.fitness /= 10;
            }

            if (a.fitness > maxFitness) {
                maxFitness = a.fitness;
            }
        }

        matingPool.clear();
        for (Agent a : agents) {
            double normalizedFitness = a.fitness / maxFitness;
            int n = (int) (normalizedFitness * 100);

            for (int i = 0; i < n; i++) {
                matingPool.add(a);
            }
        }
    }

    public void selection() {
        Agent[] newAgents = new Agent[agents.length];

        for (int i = 0; i < agents.length; i++) {
            Agent ParentA = matingPool.get(rand.nextInt(matingPool.size()));
            Agent ParentB = matingPool.get(rand.nextInt(matingPool.size()));

            DNA newDna = ParentA.dna.crossover(ParentB.dna);
            newDna.mutate(mutationRate);

            newAgents[i] = new Agent(400, 700);
            newAgents[i].dna = newDna;
        }
        agents = newAgents;
        generations++;
    }

    public void runPop() {
        for (Agent a : agents) {
            a.update(target);
        }
    }
}
