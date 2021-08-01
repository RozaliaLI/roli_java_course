package roli.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(1, 2);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    int lo = Math.max(4, 0);
    System.out.println(lo);

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
}