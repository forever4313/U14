package online.u148.domain.interactor;

import online.u148.domain.executor.PostExecutionThread;
import online.u148.domain.executor.ThreadExecutor;

/**
 * Created by Kevin Dong on 2016/4/20.
 */
public abstract class DefaultRxUseCase extends UseCase {



    protected DefaultRxUseCase(ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread){
        super(threadExecutor,postExecutionThread);
    }

    protected abstract RequestParameter buildParameter();



}
