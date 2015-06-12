class Tree [Ordered]{
  private final val root: BiNode = new BiNode(null,null,null,null,null)



  def put(key: Ordered, value: Ordered): Unit = {
//    val x : BiNode = find(key, root)
  }
}

class BiNode(var left: BiNode
             , var mid: BiNode
             , var right: BiNode
             , var keyLeft: Ordered
             , var keyRight: Ordered) {

}

