/**
 * Created by 8lackC on 5/30/15.
 */

object Impl {
  val t1 = new NonEmpty(3, new Empty, new Empty)
  val t2 = t1.incl(4)
  val t3 = t2.incl(2)
  val t4 = new NonEmpty(6, new Empty, new Empty)
  val t5 = t4.incl(1)
  val t6 = t5.incl(0)
  t3.union(t6)
}

abstract class IntSet {
  def union(that: IntSet): IntSet

  def contains(e: Int): Boolean

  def incl(e: Int): IntSet
}


class Empty extends IntSet {
  override def union(that: IntSet): IntSet = that

  override def contains(e: Int): Boolean = false

  override def incl(e: Int): IntSet = new NonEmpty(e, new Empty, new Empty)

  override def toString = "."
}

class NonEmpty(element: Int, left: IntSet, right: IntSet) extends IntSet {

  override def union(that: IntSet): IntSet = {
    ((left.union(right).union(that)).incl(element))
  }

  override def contains(e: Int): Boolean = {
    if (e < element) left contains e
    else if (e > element) right contains e
    else true
  }

  override def incl(e: Int): IntSet = {
    if (e < element) new NonEmpty(element, left incl e, right)
    else if (e > element) new NonEmpty(element, left, right incl e)
    else this
  }

  override def toString = "{" + left + " " + element + " " + right + "}"
}
