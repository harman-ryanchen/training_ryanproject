package designPatern.AigeStudio;

import java.util.List;

/**
 * Created by ryan on 4/5/16.
 */
public class StandAloneChannelMatch extends ChannelMatch {
    @Override
    public boolean chackSpeakersMatch(List<Speaker> speakers) {
        return false;
    }
}
