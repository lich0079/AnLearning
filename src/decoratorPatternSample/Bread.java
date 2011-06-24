package decoratorPatternSample;

public class Bread extends Ingredient {

    private String description;

    public Bread(String desc) {

        this.description = desc;

    }

    public String getDescription() {

        return description;

    }

    public double getCost() {

        return 2.48;

    }

}