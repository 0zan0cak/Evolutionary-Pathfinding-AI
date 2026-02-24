
import java.util.Random;

public class DNA {

    public Vector2D[] genes;
    int lifespan = 600;
    Random rand = new Random();

    public DNA() {
        genes = new Vector2D[lifespan];
        for (int i = 0; i < lifespan; i++) {
            genes[i] = createRandomGene();
        }
    }

    public DNA(Vector2D[] newGenes) {
        this.genes = newGenes;
    }

    public DNA crossover(DNA partner) {
        Vector2D[] newGenes = new Vector2D[lifespan];
        int midpoint = rand.nextInt(lifespan);

        for (int i = 0; i < lifespan; i++) {
            if (i < midpoint) {
                newGenes[i] = this.genes[i];
            } else {
                newGenes[i] = partner.genes[i];
            }
        }

        return new DNA(newGenes);
    }

    public void mutate(double mutationRate) {
        for (int i = 0; i < lifespan; i++) {
            if (rand.nextDouble() < mutationRate) {
                genes[i] = createRandomGene();
            }
        }
    }

    // Helper func for creating Genes
    private Vector2D createRandomGene() {
        Vector2D newGen = new Vector2D(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1);
        newGen.normalize();

        return newGen;
    }
}
