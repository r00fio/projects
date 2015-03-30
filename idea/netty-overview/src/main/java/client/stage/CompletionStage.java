package client.stage;

import java.nio.channels.CompletionHandler;

/**
 * Extended to support lambda and default method and provide
 * default functionality
 *
 * @param <V>
 * @param <A>
 */
public interface CompletionStage<V, A> extends CompletionHandler<V, A> {

    @Override
    default void failed(Throwable exc, A attachment) {
        System.out.println(exc);
    }
}