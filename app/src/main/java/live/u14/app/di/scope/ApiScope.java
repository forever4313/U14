package live.u14.app.di.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Denis Kholevinsky
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiScope {
}
