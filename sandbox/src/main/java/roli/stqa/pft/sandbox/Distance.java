package roli.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(1,-2);
    System.out.println("Расстояние между двумя точками с координатами (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") равно " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(Math.abs(p2.x - p1.x), 2) + Math.pow(Math.abs(p2.y - p1.y), 2));
  }
}
