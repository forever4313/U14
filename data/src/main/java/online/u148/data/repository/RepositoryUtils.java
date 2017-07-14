package online.u148.data.repository;

import online.u148.common.utils.GsonUtil;
import online.u148.common.utils.Logger;
import online.u148.data.U148Response;
import online.u148.data.exception.U148Exception;
import rx.Observable;
import rx.functions.Func1;


/**
 * Created by Kevin Dong on 2016/5/15.
 */
public class RepositoryUtils {

    public static <T> Observable<T> extractData(Observable<U148Response> observable, final Class<T> clazz) {
        return observable.flatMap(new Func1<U148Response, Observable<? extends T>>() {
            @Override
            public Observable<? extends  T> call(U148Response response) {
                if (response == null) {
                    Logger.log(Logger.SCOPE.NETWORK,"response is null");
                    return Observable.error(U148Exception.networkError("response is null"));
                } else if (response.getCode() == U148Response.CODE_OK) {
                    return Observable.just(GsonUtil.fromGson(response.getDatas(), clazz));
                } else {
                    Logger.log(Logger.SCOPE.NETWORK,response.getCode()+"##"+response.getMsg());
                    return Observable.error(U148Exception.serverResponseError(response));
                }
            }
        });
    }
}
