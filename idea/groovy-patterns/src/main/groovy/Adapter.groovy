/**
 * Created by air on 01/08/15.
 */

interface RoundThing {
    def getRadius()
}
class SquarePeg{
    def width
}
class RoundHole {
    def radius
    def pegFits(peg) {
        peg.radius <= radius
    }
    String toString() { "RoundHole with radius $radius" }
}

public static void main(String[] args) {
    def adapter = {peg -> [getRadius : {Math.sqrt(((p.width / 2) ** 2) * 2)}]}
    def peg = new SquarePeg(width: 4.0)
    if (new RoundHole(radius: 6.0).pegFits(adapter(peg))) {
        println "Fits"
    }
}