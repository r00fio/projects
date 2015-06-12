/**
 * Created by 8lackC on 5/30/15.
 */
object Product {
  //x*x *(x + 1)(x+1)*(x + 2)(x+2)
  def mapReduce(map: Int => Int, reduce: (Int, Int) => Int)(a: Int, b: Int): Int = {
    require(a>0, "blablba")
    if (a > b) return 1 // or insert else and remove return
    reduce(map(a), mapReduce(map, reduce)(a + 1, b))
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y)(a, b)

  def main(args: Array[String]) {

    System.out.println(product(x => x * x)(0, 4))
  }

}
