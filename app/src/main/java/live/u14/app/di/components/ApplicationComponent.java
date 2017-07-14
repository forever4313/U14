package live.u14.app.di.components;

import android.content.Context;
import dagger.Component;
import live.u14.app.MyApplication;
import live.u14.app.di.modules.AppModule;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface ApplicationComponent {
    //Exposed to sub-graphs.
    MyApplication getApplication();
    Context context();

}
