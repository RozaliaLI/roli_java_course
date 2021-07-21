package roli.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void test1(){
    Point a = new Point(1,1);
    Point b = new Point(2,1);
    Assert.assertEquals(a.distance(b), 1);
  }
  @Test
  public void test2(){
    Point a = new Point(-1,-3);
    Point b = new Point(-1,-1);
    Assert.assertEquals(a.distance(b), 2);
  }
  @Test
  public void test3(){
    Point a = new Point(0,0);
    Point b = new Point(0,0);
    Assert.assertEquals(a.distance(b), 0);
  }
  @Test
  public void test4(){
    Point a = new Point(0.5, 0.5);
    Point b = new Point(1.7,0.5);
    Assert.assertEquals(a.distance(b), 1.2);
  }
}
