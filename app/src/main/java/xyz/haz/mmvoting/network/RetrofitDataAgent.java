package xyz.haz.mmvoting.network;

import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.haz.mmvoting.events.DataEvent;
import xyz.haz.mmvoting.network.responses.MatchListResponse;
import xyz.haz.mmvoting.utils.AppConstants;

/**
 * Created by haz on 04/12/2017.
 */

public class RetrofitDataAgent implements VotingDataAgent {
    private static RetrofitDataAgent objInstance;

    private final VotingApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(VotingApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }



    @Override
    public void loadMatch() {
        Call<MatchListResponse> loadMatchCall=theApi.loadMatch();

        loadMatchCall.enqueue(new Callback<MatchListResponse>() {
            @Override
            public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
                MatchListResponse matchListResponse= response.body();

                if (matchListResponse == null){
                    EventBus.getDefault().post(new DataEvent.MatchLoadEmptyEvent());
                }else {
                    EventBus.getDefault().post(new DataEvent.MatchLoadEvent(matchListResponse.getMatchList()));

                }



            }

            @Override
            public void onFailure(Call<MatchListResponse> call, Throwable t) {
                Log.d("Network Fail","dddd");
            }
        });

    }
}
