package will.tw.carepets.api;

/**
 * Created by william on 2016/12/21.
 */

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import will.tw.carepets.BuildConfig;
import will.tw.carepets.model.PetReport;

public class PetApi {
    private static final String BASE_URL = "http://data.taipei/";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_RID = "rid";
    private static final String SCOPE = "resourceAquire";
    private static final String RID ="f4a75ba9-7721-4363-884d-c3820b0b917c";
    private static final String Q = "q";

    private static WeatherService sService;

    private static synchronized WeatherService getService() {
        if (sService == null) {
            final GsonConverterFactory converterFactory = GsonConverterFactory.create();
            final RxJavaCallAdapterFactory callAdapterFactory = RxJavaCallAdapterFactory.create();

            OkHttpClient httpClient = new OkHttpClient();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
            }

            final Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(httpClient)
                            .addConverterFactory(converterFactory)
                            .addCallAdapterFactory(callAdapterFactory)
                            .build();
            sService = retrofit.create(WeatherService.class);
        }
        return sService;
    }

    public static Observable<PetReport> findReportByCity(String type) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(KEY_SCOPE, SCOPE);
        parameters.put(KEY_RID, RID);
        parameters.put(Q,type);
        return getService().findReport(parameters);
    }

    private interface WeatherService {
        @GET("opendata/datalist/apiAccess")
        Observable<PetReport> findReport(@QueryMap Map<String, String> parameters );
    }
}
