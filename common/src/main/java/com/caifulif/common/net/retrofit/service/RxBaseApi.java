package com.caifulif.common.net.retrofit.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.caifulif.common.net.retrofit.factory.RxServiceFactory;
import com.caifulif.common.net.retrofit.func.RetryWhenNetworkException;
import com.caifulif.common.net.retrofit.subscriber.HttpResultSubscriber;
import com.caifulif.common.net.retrofit.transformer.TransformUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import constants.CommonServerConstant;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by 皓 on 2016/8/30.
 */
public class RxBaseApi {
    /**
     * 调用RxService 的方法  返回相应的Observeble
     * 例如
     * 1.获取RxService实例
     * RXServiceXX rXServiceXX =
     * RxServiceFactory.createServiceFrom(RXServiceXX.class, host);
     * 2.获取Observeble
     * public Observable<List<News>> getNcuNews(int howmany, int fromwhere){
     * return rXServiceXX.getNcuNews(howmany, fromwhere)
     * .subscribeOn(Schedulers.io())//在io线程进行数据获取
     * .observeOn(AndroidSchedulers.mainThread());//在UI线程使用返回的数据
     * }
     */

    private RxBaseService apiService;
    private static RxBaseApi rxBaseApi;


    private RxBaseApi(Context context, String prefixUrl, Map<String, String> headers) {
        apiService = RxServiceFactory.getInstance().createRxServiceFrom(context, RxBaseService.class, prefixUrl, headers);
    }

    private static String lastPrefixUrl = "";

    public static RxBaseApi getDefault(@NonNull Context context, @NonNull String prefixUrl, Map<String, String> headers) {

        if (!lastPrefixUrl.equals(prefixUrl) || rxBaseApi == null) {
            lastPrefixUrl = prefixUrl;
            rxBaseApi = new RxBaseApi(context, prefixUrl, headers);
        }
        return rxBaseApi;
    }

    /**
     * 执行get请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executeGet(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        return createGetObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createGetObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, final Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executeGet(url, request)
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBaseBody) {
                        T resultData = null;
                        //返回的服务器对象
                        if (responseBaseBody != null) {
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                            }
                                try {
                                    return (T)new String(responseBaseBody.bytes());
                                } catch (Throwable t) {
                                    Logger.i(resultData.toString());
                                }
                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers())
                .retryWhen(new RetryWhenNetworkException());
    }


    /**
     * 执行post请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executePost(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        return createPostObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createPostObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executePost(url, request)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBaseBody) {

                        T resultData = null;
                        //返回的服务器对象
                        if (responseBaseBody != null) {
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                            }
                            try {
                                return (T)new String(responseBaseBody.bytes());
                            } catch (Throwable t) {
                            }
                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    /**
     * 执行post请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executePostList(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        return createPostStringObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createPostStringObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executePost(url, request)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBaseBody) {

                        T resultData = null;
                        //返回的服务器对象
                        if (responseBaseBody != null) {
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                            }
                            try {
                                return (T)new String(responseBaseBody.bytes());
                            } catch (Throwable t) {
                                Logger.i(resultData.toString());
                            }
                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }


    /**
     * 上传多个文件
     */
    public <T> Subscription uploadMulFiles(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, RequestBody> maps) {
        return createUploadMulFileObservable(context, subscriber, resultType, url, maps)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createUploadMulFileObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, RequestBody> maps) {
        subscriber.setContext(context);
        return apiService.uploadFiles(url, maps)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBaseBody) {
                        T resultData = null;
                        //返回的服务器对象
                        if (responseBaseBody != null) {
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                            }
                            try {
                                resultData = (T)new String(responseBaseBody.bytes());
                            } catch (Throwable t) {
                                Logger.i(resultData.toString());
                            }
                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    public <T> Subscription downloadFile(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, final String filePath) {

        return createDownloadFileObservable(context,subscriber,url, filePath)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createDownloadFileObservable(final Context context, final HttpResultSubscriber subscriber, String url, final String filePath) {
        subscriber.setContext(context);
        return apiService.downloadFile(url)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBody) {
                        try {
                            if (subscriber != null) {
                                subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                            }
                            
                            InputStream inputStream = responseBody.byteStream();

                            String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
                            File dir = new File(dirPath);
                            dir.mkdirs();
                            File file = new File(filePath);
                            file.createNewFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            int len;
                            byte[] bytes = new byte[1024];
                            while ((len = inputStream.read(bytes)) != -1) {
                                fos.write(bytes, 0, len);
                            }
                            fos.flush();
                            fos.close();
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    public Subscription downloadFile2(final Context context, final HttpResultSubscriber subscriber, String url) {
        subscriber.setContext(context);
        return apiService.downloadFile(url)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        if (subscriber != null) {
                            subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                        }
                        InputStream inputStream = responseBody.byteStream();
                        return inputStream;
                    }
                })
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(subscriber);
    }

    /**
     * zip
     */
    public <T, K, M> Subscription zip(Observable<T> o1, Observable<K> o2, final HttpResultSubscriber subscriber, final IParseData<T, K, M> parseDataListener) {
        return Observable.zip(o1, o2, new Func2<T, K, M>() {
            @Override
            public M call(T t, K k) {
                if (subscriber != null)
                    subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                return parseDataListener.parseData(t, k);
            }
        }).subscribe(subscriber);
    }

    public interface IParseData<T, K, M> {
        M parseData(T t, K k);
    }
}
