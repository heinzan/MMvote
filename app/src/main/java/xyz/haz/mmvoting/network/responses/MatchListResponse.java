package xyz.haz.mmvoting.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.haz.mmvoting.data.vo.MatchVO;

/**
 * Created by haz on 04/12/2017.
 */

public class MatchListResponse {

/*    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;*/

    @SerializedName("Matches")
    private ArrayList<MatchVO> matchList;

/*    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }*/

    public ArrayList<MatchVO> getMatchList() {
        if(matchList == null){
            matchList = new ArrayList<>();
        }
        return matchList;
    }


}
