package xyz.haz.mmvoting.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.haz.mmvoting.R;
import xyz.haz.mmvoting.adapters.HomeAdapter;
import xyz.haz.mmvoting.controllers.ControllerMatchItem;
import xyz.haz.mmvoting.events.DataEvent;
import xyz.haz.mmvoting.network.VotingDataAgentImpl;

public class HomeActivity extends BaseActivity implements ControllerMatchItem{

    @BindView(R.id.rvMain)
    RecyclerView rvMain;

    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this,this);
        VotingDataAgentImpl.getInstance().loadMatch();

        mHomeAdapter=new HomeAdapter(getApplicationContext(),this);
        rvMain.setAdapter(mHomeAdapter);

        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        rvMain.setLayoutManager(llm);


    }

    @Override
    public void onTapMatch(int matchId) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void matchDataLoaded(DataEvent.MatchLoadEvent matchLoadEvent){
        mHomeAdapter.appendNewData(matchLoadEvent.getMatchList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErroeMsg(DataEvent.ErrorInvokeEvent event){
        Snackbar.make(rvMain , event.getErrorMsg() , Snackbar.LENGTH_LONG).show();

    }

}
