package concurrent.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bebe on 3/4/15.
 */
public class TestCompletionStage {
    public static void main(String[] args) {
        CompletionStage<String> future = new CompletableFuture<>();
        final ExecutorService executorService = Executors.newWorkStealingPool();
    }
}
