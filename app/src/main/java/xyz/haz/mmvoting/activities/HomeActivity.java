package xyz.haz.mmvoting.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.haz.mmvoting.R;
import xyz.haz.mmvoting.adapters.HomeAdapter;
import xyz.haz.mmvoting.controllers.ControllerMatchItem;
import xyz.haz.mmvoting.network.RetrofitDataAgent;

public class HomeActivity extends BaseActivity implements ControllerMatchItem{

    @BindView(R.id.rvMain)
    RecyclerView rvMain;

    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this,this);
        RetrofitDataAgent.getInstance().loadMatch();

        mHomeAdapter=new HomeAdapter(getApplicationContext(),this);
        rvMain.setAdapter(mHomeAdapter);

        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        rvMain.setLayoutManager(llm);


    }

    @Override
    public void onTapMatch(int matchId) {

    }

}
