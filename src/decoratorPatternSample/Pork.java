package decoratorPatternSample;

public class Pork extends Decorator{

         public Pork(Ingredient igd){

                   super(igd);

         }

         public String getDescription(){

                   String base = ingredient.getDescription();

                   return base +"\n"+"Decrocated with Pork !";

         }

         public double getCost(){

                   double basePrice = ingredient.getCost();

                   double porkPrice = 1.8;

                   return        basePrice + porkPrice ;

         }

}
