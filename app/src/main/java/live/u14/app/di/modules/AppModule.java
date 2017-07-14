package live.u14.app.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import live.u14.app.MyApplication;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */

@Module
public class AppModule {

    private Context appContext;
    private MyApplication application;
    public AppModule(@NonNull MyApplication application) {
        this.application = application;
        this.appContext = application;
    }

    @Singleton
    @Provides
    MyApplication provideApplication(){
        return this.application;
    }


    @Singleton
    @Provides
    Context provideContext() {
        return this.appContext;
    }




}
