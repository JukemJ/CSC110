public class Card
{
   String rank = "J";
   String suit = "Hearts";

  public static void main(String[] args){
    System.out.println();                   //start on a new line
    Card card = new Card();
    System.out.println(card.rank);
    System.out.println(card.suit);
  }
}


