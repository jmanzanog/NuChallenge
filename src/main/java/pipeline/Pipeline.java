package pipeline;

/**
 * Pipeline class that initially sets the current handler.
 * Processed output of the initial
 * handler is then passed as the input to the next stage handlers.
 *
 * @param <I> the type of the input for the first stage handler
 * @param <O> the final stage handler's output type
 */
public final class Pipeline<I, O> {

    private final Handler<I, O> currentHandler;

    public Pipeline(Handler<I, O> currentHandler) {
        this.currentHandler = currentHandler;
    }

    public <T> Pipeline<I, T> addHandler(Handler<O, T> nextHandler) {
        return new Pipeline<>(input -> nextHandler.process(currentHandler.process(input)));
    }

    public O execute(I input) {
        return currentHandler.process(input);
    }
}