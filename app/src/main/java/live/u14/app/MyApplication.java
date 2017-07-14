package live.u14.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import com.youku.cloud.player.YoukuPlayerConfig;
import live.u14.BuildConfig;
import live.u14.app.di.components.DaggerApplicationComponent;
import live.u14.app.di.components.DaggerNetworkComponent;
import online.u148.common.utils.ApplicationUtil;
import online.u148.common.utils.GsonUtil;
import online.u148.common.utils.Logger;
import live.u14.app.di.components.ApplicationComponent;
import live.u14.app.di.components.NetworkComponent;
import live.u14.app.di.modules.AppModule;
import live.u14.app.di.modules.NetworkModule;
import online.u148.data.U148Response;
import online.u148.data.U148ResponseDeserializer;
import online.u148.data.net.RestApi;


/**
 * @author: Kevin Dong
 * @date:2016/3/31
 * @email:dongkai@nodescm.com
 */
public class MyApplication extends Application {

    private static ApplicationComponent appComponent;
    public static final String CLIENT_ID_WITH_AD = "4ab34377395e3bbb";
    public static final String CLIENT_SECRET_WITH_AD = "68af9b14f0e1f18ceb3667e16e603092";
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        initSubComponents();
        ApplicationUtil.init(this);
        if(BuildConfig.DEBUG){
            Logger.initDebugLog();
        }
        GsonUtil.registerTypeAdapter(U148Response.class, new U148ResponseDeserializer());
        updateApp();
        YoukuPlayerConfig.setLog(true);
        /**设置client_id和client_secret*/
        YoukuPlayerConfig.setClientIdAndSecret(CLIENT_ID_WITH_AD,CLIENT_SECRET_WITH_AD);
        YoukuPlayerConfig.onInitial(this);

    }

    private void initializeInjector() {
        this.appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();


    }

    private void initSubComponents(){


    }
    private NetworkComponent initNetworkComponent() {

        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.DEBUG? RestApi.BASE_URL_TEST:RestApi.BASE_URL_PRD))
                .build();
    }
    private void updateApp(){
        SharedPreferences sp = getSharedPreferences("app", Activity.MODE_PRIVATE);
//        String phone = sp.getString("phone","");
//        String token = sp.getString("token","");
//        if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(token)){
//            UserInfo info = new UserInfo();
//            info.setMobile(phone);
//            info.setAccessToken(token);
//            AccountConfig.setUserInfo(info);
//            sp.edit().putString("phone","").putString("token","").commit();
//
//        }

    }


    public static ApplicationComponent getApplicationComponent() {
        return appComponent;
    }
}
