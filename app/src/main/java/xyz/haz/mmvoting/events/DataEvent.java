package xyz.haz.mmvoting.events;

import java.util.List;

import xyz.haz.mmvoting.data.vo.MatchVO;

/**
 * Created by haz on 22/11/2017.
 */

public class DataEvent {

    public static class EmptyResponseEvent{

    }

    public static class ErrorInvokeEvent{
        private String errorMsg;

        public String getErrorMsg() {
            return errorMsg;
        }

        public ErrorInvokeEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
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
