package roli.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");

    double k = 5;
    System.out.println("Площадь квадрата со стороной " + k + " = " + area(k));

    double a = 1;
    double b = 2;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b) );
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double l){
    return l * l;
  }

  public static double area(double a, double b){
    return a * b;
  }
}