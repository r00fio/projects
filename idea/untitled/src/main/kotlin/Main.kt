/**
 * Created by bebe on 12/13/14.
 */
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.net.URL
import java.net.URLConnection
import java.net.HttpURLConnection
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.ArrayList
import java.util.concurrent.Future
import java.io.InputStream
import org.hibernate.cfg.Configuration
import unit.City
import java.io.Serializable
import com.darkprograms.speech.microphone.Microphone

WebServlet(name = "Hello", value = array("/"))
public class HomeController : HttpServlet() {

    override fun doGet(req
                       : HttpServletRequest?, resp: HttpServletResponse?) {

        val factory = Configuration().configure().buildSessionFactory()
        val session = factory.openSession()
        val transaction = session.beginTransaction()

//        val city: City = session.get(javaClass<City>(), 3 as Serializable) as City
//        resp?.getWriter()?.write(city.toString())

    }
}

fun main(args: Array<String>) {
    Clazz.ambientListeningLoop()
}

//fun main(args: Array<String>) {
//        val factory = Configuration().configure().buildSessionFactory()
//        val session = factory.openSession()
//        val transaction = session.beginTransaction()
//}

