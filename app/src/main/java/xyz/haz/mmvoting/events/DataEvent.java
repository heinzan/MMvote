package xyz.haz.mmvoting.events;

import java.util.List;

import xyz.haz.mmvoting.data.vo.MatchVO;

/**
 * Created by haz on 22/11/2017.
 */

public class DataEvent {
    public static class MatchLoadEvent{

        private List<MatchVO> matchList;

        public List<MatchVO> getMatchList() {
            return matchList;
        }

        public MatchLoadEvent(List<MatchVO> matchList){
            this.matchList = matchList;

        }

    }

    public static class MatchLoadEmptyEvent{


    }
}
