package roli.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(1,2);
    System.out.println("Расстояние между двумя точками с координатами (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") равно " + p1.distance(p2));
  }
}
