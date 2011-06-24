package decoratorPatternSample;

public class DecoratorTest {

    public static void main(String[] args) {

        Ingredient compound = new Mutton(new Celery(new Bread("Master24's Bread")));

        compound.printDescription();

        compound = new Celery(new GreenGrocery(new Bread("Bread with milk")));

        compound.printDescription();

        compound = new Mutton(new Pork(new Bread("Bread with cheese")));

        compound.printDescription();

    }

}
