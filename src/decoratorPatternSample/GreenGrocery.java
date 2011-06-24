package decoratorPatternSample;

public class GreenGrocery extends Decorator{

         public GreenGrocery (Ingredient igd){

                   super(igd);

         }

         public String getDescription(){

                   String base = ingredient.getDescription();

                   return base +"\n"+"Decrocated with GreenGrocery  !";

         }

         public double getCost(){

                   double basePrice = ingredient.getCost();

                   double greenGroceryPrice = 0.4;

                   return        basePrice + greenGroceryPrice ;

         }

}
