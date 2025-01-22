public class Bill
{
   public static void main(String[] args)
   {
      double taxRate = 8.6;
      double subtotal = 59.00;
      double tipPercentage = 20;
      System.out.println(subtotal, (subtotal * taxRate / 100), subtotal / 100);
      System.out.println(subtotal + (subtotal * taxRate / 100) + (subtotal * tipPercentage / 100));

   }
}