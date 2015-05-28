package datastructure;

/**
 * Created by 8lackC on 4/28/15.
 * Allows duplicates
 */
public class SkipList<E extends Comparable<? super E>> {

    private int lvls = 0;

    static class Node {
        Comparable element; // make it private and u will get .access$000 error
        private Object val;
        Node next;
    }

    // implement indexing for fast compare when get a range(start,end)
    static class Lane {
        private Lane right;
        private Lane down;
        private Node node;

        public int compareTo(Comparable o) {
            return node.element.compareTo(o);
        }
    }

    private Lane head = createHead();

    public E get(E e) {
        Lane[] walked = getWalkedLanes(e);

        Lane n = walked[getSize(walked) - 1];

        while (n.down != null) {
            n = n.down;
        }
        Node node = searchNode(e, n);
        return node != null ? (E) node.element : null;
    }

    public void add(E e) {
        Lane[] walked = getWalkedLanes(e);// what if lane already exists
        int size = getSize(walked);
        Node prev = insertAfterLane(e, walked[size - 1].node);
        if (prev == null) return;
        Lane lo = null;
        for (; ; ) {
            if (needToPop()) {
                Lane w = null;
                if (size > 0) {
                    w = walked[--size];
                }
//                if (notNull(w)) {
                    if (w == null ){//|| w.node.element == null && size ==0 ) { // one of the head nodes
                        head = createHead();
                        w = head;
                        lvls++;
                    }
                    Lane hi = new Lane();
                    hi.down = lo;
                    hi.node = prev.next; //set value e to newly created lane
                    hi.right = w.right;
//                    w.right = w.node.element == null ? null : hi;
                    w.right = hi;
                    lo = hi;
//                }

//                if (w.node.element == null ) { // one of the head nodes
//                    head = createHead();
//                    head.node.next = lo == null ? null : lo.node;
//                    head.right = lo;
//                    lvls++;
//                }
            } else break;
        }
    }

    private int getSize(Lane[] lanes) {
        int i = 0;
        for (Lane lane : lanes) {
            if (lane != null) i++;
        }

        return i;
    }

    private Lane createHead() {
        Lane newHead = new Lane();
        newHead.node = new Node();
        newHead.down = head;
        return newHead;
    }

    private Node searchNode(E e, Lane prev) {
        Node result = prev.node;
        Node n = skipWithoutElement(prev.node, e);
        for (; ; ) {
            if (notNull(n)) {
                if (n.element.compareTo(e) == 0) {
                    result = n;
                    break;
                } else {
                    n = n.next;
                }
            } else break;
        }
        return result;
    }

    /**
     * return previouse node after which new node inserted
     *
     * @param e
     * @param prev
     * @return
     */
    private Node insertAfterLane(E e, Node prev) {
        Node result = prev;
        Node n = skipWithoutElement(prev,e);
        for (; ; ) {
            if (notNull(n)) {
                if (n.element.compareTo(e) > 0) {
                    result = prev;
                    break;
                } else if (n.element.compareTo(e) < 0) {
                    prev = n;
                    result = n;
                    n = n.next;
                } else {
//                    prev.next = prev.next.next;// remove old node with same value
//                    result = prev;
//                    result = null;
                    return null;
//                    break;
                }
            } else break;
        }
        insertAfter(result, e);
        return result;
    }

    private Node skipWithoutElement(Node prev, E e) {
        Node n;
        if (prev.element == null) { // means prev == one of the heads we must skip head an jump to next node
            if (prev.next != null && prev.next.element.compareTo(e) > 0) {
                n = null;
            }else n = prev.next;
        } else n = prev;
        return n;
    }

    private Lane[] getWalkedLanes(E e) { // all lanes through which we went, in which lane[lvls] = last lane that we touch in search
        Object[] lanes = new Lane[lvls + 1];
        lanes[0] = head;
        Lane next = head;
        int l = 1;
        for (; ; ) {

            if (notNull(next.right)) {
                int cmp = next.right.compareTo(e);
                if (cmp > 0) {
                    if (!notNull(next.down)) break;
                    next = next.down;
                    lanes[l++] = next;
                } else if (cmp < 0) {
                    next = next.right;
                } else {
                    break;
                }
            } else if (next.down == null) { // we reach the end of levels
                break;
            } else {
                next = next.down; // we reached to end of current level
                lanes[l++] = next;
            }

        }
        lanes[l - 1] = next;
        return (Lane[]) lanes;
    }

    int i = 0;

    private boolean needToPop() {
        i++;
        boolean b = i % 2 == 0;
        return b;
    }

    private void insertAfter(Node n, E e) {

        Node newNode = new Node();
        newNode.element = e;
        newNode.next = n.next;
        n.next = newNode;
    }

    private boolean notNull(Object o) {
        return o != null;
    }
}
