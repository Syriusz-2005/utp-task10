import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyFuture<T> extends FutureTask<T> {
    /**
     * Creates a {@code FutureTask} that will, upon running, execute the     * given {@code Callable}.     *     * @param callable the callable task     * @throws NullPointerException if the callable is null
     */
    public MyFuture(Callable<T> callable) {
        super(callable);
    }

    @Override
    public String toString() {
        return "Własna nazwa";
    }
}