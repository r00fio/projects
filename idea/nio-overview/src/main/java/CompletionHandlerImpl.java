import java.nio.channels.CompletionHandler;

/**
 * Created by pixel on 3/19/15.
 */
@FunctionalInterface
public interface CompletionHandlerImpl<V, A> extends CompletionHandler<V, A> {

    @Override
    default void failed(Throwable exc, Object attachment) {
        System.out.println();
    }
}
