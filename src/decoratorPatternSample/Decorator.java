package decoratorPatternSample;

public abstract class Decorator extends Ingredient {

     Ingredient ingredient ;

     public Decorator(Ingredient igd){

              this.ingredient = igd;      

     }        

     public abstract String getDescription();

     public abstract double getCost();

}
