package live.u14.app.di.modules;

import dagger.Module;
import dagger.Provides;
import live.u14.app.di.scope.DataScope;
import online.u148.data.interactor.UIThread;
import online.u148.domain.executor.JobExecutor;
import online.u148.domain.executor.PostExecutionThread;
import online.u148.domain.executor.ThreadExecutor;

/**
 * Created by Kevin Dong on 2016/12/20.
 */
@Module
public class ExecutorModule {

    @Provides
    @DataScope
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @DataScope
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
