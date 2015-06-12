///**
// * Created by 8lackC on 5/30/15.
// */
//
//object Impl{
//  val t1 = new NonEmpty(3, new Empty, new Empty)
//}
//
//abstract class IntSet {
//  def contains(e: Int) : Boolean
//  def incl(e: Int) : IntSet
//}
//
//class Empty extends IntSet{
//  override def contains(e: Int): Boolean = false
//
//  override def incl(e: Int): IntSet = new NonEmpty(e, new Empty, new Empty)
//}
//
//class NonEmpty(element: Int, left: IntSet, right: IntSet) extends IntSet{
//  override def contains(e: Int): Boolean = {
//    if (e < element) left contains e
//    else if (e > element) right contains e
//    else true
//  }
//
//  override def incl(e: Int): IntSet = {
//    if (e < element) new NonEmpty(element, left incl e, right)
//    else if (e > element) new NonEmpty(element, left, right incl e)
//    else this
//  }
//}
