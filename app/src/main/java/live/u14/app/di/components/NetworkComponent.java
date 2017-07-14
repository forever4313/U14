package live.u14.app.di.components;

import dagger.Component;
import live.u14.app.di.modules.NetworkModule;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by Kevin Dong on 2016/12/20.
 */

@Singleton
@Component(
        modules = {
                NetworkModule.class
        }
)
public interface NetworkComponent {

    Retrofit retrofit();

}
