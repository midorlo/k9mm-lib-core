package com.midorlo.k9.component.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.lang.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * <p>Asynchronous Task Executor that is capable of handling exceptions.</p>
 */
@Slf4j
public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor,
                                                           InitializingBean,
                                                           DisposableBean {

    static final String EXCEPTION_MESSAGE = "Caught exception";


    private final AsyncTaskExecutor executor;

    /**
     * <p>Constructor for ExceptionHandlingAsyncTaskExecutor.</p>
     *
     * @param executor a {@link org.springframework.core.task.AsyncTaskExecutor} object.
     */
    public ExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(@NonNull Runnable task) {
        executor.execute(createWrappedRunnable(task));
    }

    @Override
    public void execute(@NonNull Runnable task, long startTimeout) {
        executor.execute(createWrappedRunnable(task), startTimeout);
    }

    private <T> Callable<T> createCallable(final Callable<T> task) {
        return () -> {
            try {
                return task.call();
            } catch (Exception e) {
                handle(e);
                throw e;
            }
        };
    }

    private Runnable createWrappedRunnable(final Runnable task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                handle(e);
            }
        };
    }

    /**
     * <p>handle.</p>
     *
     * @param e a {@link java.lang.Exception} object.
     */
    protected void handle(Exception e) {
        log.error(EXCEPTION_MESSAGE, e);
    }

    @Override
    @NonNull
    public Future<?> submit(@NonNull Runnable task) {
        return executor.submit(createWrappedRunnable(task));
    }

    @Override
    @NonNull
    public <T> Future<T> submit(@NonNull Callable<T> task) {
        return executor.submit(createCallable(task));
    }

    @Override
    public void destroy() throws Exception {
        if (executor instanceof DisposableBean) {
            DisposableBean bean = (DisposableBean) executor;
            bean.destroy();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (executor instanceof InitializingBean) {
            InitializingBean bean = (InitializingBean) executor;
            bean.afterPropertiesSet();
        }
    }
}
