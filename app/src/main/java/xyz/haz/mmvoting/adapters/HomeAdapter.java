package xyz.haz.mmvoting.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.haz.mmvoting.R;
import xyz.haz.mmvoting.controllers.ControllerMatchItem;
import xyz.haz.mmvoting.data.vo.MatchVO;
import xyz.haz.mmvoting.viewholders.HomeViewHolder;

/**
 * Created by haz on 13/11/2017.
 */

public class HomeAdapter extends BaseRecyclerAdapter<HomeViewHolder,MatchVO> {

    private ControllerMatchItem mcontrollerMatchItem;

    public HomeAdapter(Context context,ControllerMatchItem controllerMatchItem) {
        super(context);
        mcontrollerMatchItem=controllerMatchItem;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View homeView=mLayoutInflater.inflate(R.layout.view_match,parent,false);
        return new HomeViewHolder(homeView,mcontrollerMatchItem);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bind(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
