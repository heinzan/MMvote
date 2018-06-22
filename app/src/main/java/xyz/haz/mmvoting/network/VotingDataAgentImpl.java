package xyz.haz.mmvoting.network;

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

public class VotingDataAgentImpl implements VotingDataAgent {
    private static VotingDataAgentImpl objInstance;

    private VotingApi mVotingApi;

    private VotingDataAgentImpl(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        mVotingApi = retrofit.create(VotingApi.class);

    }

    public static VotingDataAgentImpl getInstance(){
        if(objInstance == null){
            objInstance = new VotingDataAgentImpl();
        }
        return  objInstance;
    }


    @Override
    public void loadMatch() {

       Call<MatchListResponse> loadMatch =  mVotingApi.loadMatch();

       loadMatch.enqueue(new Callback<MatchListResponse>() {
           @Override
           public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
               MatchListResponse matchListResponse = response.body();
               if(matchListResponse != null && matchListResponse.getMatchList().size() > 0){

                   DataEvent.MatchLoadEvent matchLoadEvent= new DataEvent.MatchLoadEvent(matchListResponse.getMatchList());
                   EventBus.getDefault().post(matchLoadEvent);

               }else {
                   DataEvent.ErrorInvokeEvent errorInvokeEvent = new DataEvent.ErrorInvokeEvent("No Data Found.Please Try Again");
                   EventBus.getDefault().post(errorInvokeEvent);
               }

           }

           @Override
           public void onFailure(Call<MatchListResponse> call, Throwable t) {
               DataEvent.ErrorInvokeEvent errorEvent = new DataEvent.ErrorInvokeEvent(t.getMessage());
               EventBus.getDefault().post(errorEvent);

           }
       });

    }
}
