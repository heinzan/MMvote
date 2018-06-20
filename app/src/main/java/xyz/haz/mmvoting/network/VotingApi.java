package xyz.haz.mmvoting.network;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.haz.mmvoting.network.responses.MatchListResponse;
import xyz.haz.mmvoting.utils.AppConstants;

/**
 * Created by haz on 04/12/2017.
 */

public interface VotingApi {

    @GET(AppConstants.API_GET_MATCH_LIST)
    Call<MatchListResponse> loadMatch();
}
