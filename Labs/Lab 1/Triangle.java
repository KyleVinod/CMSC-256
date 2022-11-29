package cmsc256;

/** 
  Determines if three doubles can be sides of triangle. 
*/
public class Triangle  {

   /** Length of side 1. */
   private double sideA;

   /** Length of side 2. */
   private double sideB;

   /** Length of side 3. */
   private double sideC;

   /**
    * Creates a Triangle.
    *
    * @param aIn length of side 1.
    * @param bIn length of side 2.
    * @param cIn length of side 3.
    */
   public Triangle(double aIn, double bIn, double cIn) {
      
      if (aIn <= 0 || bIn <= 0 || cIn <= 0) {
         throw new IllegalArgumentException("Sides: " + aIn + " " + bIn + " " + cIn
            + " do not make up a triangle with one or more sides equal 0");       //One or more of the sides equals 0
      }
     
      if ((aIn >= bIn + cIn) || (bIn >= aIn + cIn) || (cIn >= aIn + bIn)) {
         throw new IllegalArgumentException("Sides: "
            + aIn + " " + bIn + " " + cIn
            + " do not make up a triangle with one or more sides are bigger than two sides added");      //One or more of the sides are bigger than two sides added
      }
     
      sideA = aIn;
      sideB = bIn;
      sideC = cIn;

      String result = classify();
   }

   /**
    *  Classifies a triangle based on the lengths of the three sides.
    *  The classifications are either: "equilateral", "scalene", "isosceles", 
    *  or "not a triangle". The returned string must match one of these.Tri
    *
    * @return the triangle classification.
    */
   public String classify() {
      String result = "";
      if ((sideA != sideB) && (sideA != sideC) && (sideB != sideC)) {
         result = "scalene";
      }
      else if ((sideA == sideB) && (sideA == sideC)) {
         result = "equilateral";
      }
      else if ((sideA == sideB) || (sideA == sideC) || (sideB == sideC)) {
         result = "isosceles";
      }
      else {
         result = "not a triangle";
      }
      //TODO:  implement this method according to the specification in the comment block
      return result;
   }

}
