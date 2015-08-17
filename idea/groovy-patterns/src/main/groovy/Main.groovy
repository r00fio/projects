public class GameFactory {
    def static factory;
    def static getMessages() {
        factory.messages.newInstance()

    }
}

class GuessMessages {
    def static show() {
        println "You win"
    }
}

public static void main(String[] args) {
    def factory = [messages: GuessMessages]
    GameFactory.factory = factory
    GameFactory.messages.show()

}