/**
 * Created by 8lackC on 5/27/15.
 */
object TailRecursionSum {
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a, 0)
  }

  def main(args: Array[String]) {
    System.out.print(sum((x: Int) => x, 4, 10))
  }

}
