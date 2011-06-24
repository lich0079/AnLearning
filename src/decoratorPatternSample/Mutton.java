package decoratorPatternSample;

public class Mutton extends Decorator{

         public Mutton(Ingredient igd){

                   super(igd);

         }

         public String getDescription(){

                   String base = ingredient.getDescription();

                   return base +"\n"+"Decrocated with Mutton !";

         }

         public double getCost(){

                   double basePrice = ingredient.getCost();

                   double muttonPrice = 2.3;

                   return        basePrice + muttonPrice ;

         }

}
