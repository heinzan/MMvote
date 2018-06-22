package xyz.haz.mmvoting.viewholders;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import xyz.haz.mmvoting.R;
import xyz.haz.mmvoting.controllers.ControllerMatchItem;
import xyz.haz.mmvoting.data.vo.MatchVO;

/**
 * Created by haz on 18/11/2017.
 */

public class HomeViewHolder extends BaseViewHolder<MatchVO> {

    @BindView(R.id.crdMain)
    CardView crdMain;

    @BindView(R.id.img_category)
    ImageView imgCategory;

    private ControllerMatchItem controllerMatchItem;
    private MatchVO matchVO;


    public HomeViewHolder(View itemView, ControllerMatchItem mcontrollerMatchItem) {
        super(itemView);
        controllerMatchItem=mcontrollerMatchItem;
    }

    @Override
    public void bind(MatchVO data) {

        Picasso.get().load(data.getPhotos()).into(imgCategory);

        }
}
