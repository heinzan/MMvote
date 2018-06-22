package xyz.haz.mmvoting.data.model;

import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import xyz.haz.mmvoting.data.vo.MatchVO;
import xyz.haz.mmvoting.events.DataEvent;

/**
 * Created by haz on 26/12/2017.
 */

public class MatchModel {

    private static MatchModel objInstance;

    private List<MatchVO> mMatch;
    private MatchModel(){
        EventBus.getDefault().register(this);
        mMatch = new ArrayList<>();

    }
    public static MatchModel getInstance(){
        if(objInstance == null){
            objInstance = new MatchModel();
        }
        return  objInstance;
    }

    @Subscribe
    public void matchDataLoaded(DataEvent.MatchLoadEvent matchLoadEvent){
        mMatch.addAll(matchLoadEvent.getMatchList());
    }

}
