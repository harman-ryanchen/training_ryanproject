package designPatern.AigeStudio;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式之——责任链模式
 * Created by ryan on 4/5/16.
 */
public class SpeakerMatchUtil {
    List<Speaker> speakers;
    private Adapt51ChannelMatch adapt51ChannelMatch;
    private StandAloneChannelMatch standAloneChannelMatch;
    public SpeakerMatchUtil(List<Speaker> speakers) {
        this.speakers = speakers;
        adapt51ChannelMatch = new Adapt51ChannelMatch();
        standAloneChannelMatch = new StandAloneChannelMatch();
        standAloneChannelMatch.setSuperChannel(adapt51ChannelMatch);
    }

    public ChannelMatch whatChannelMatch(){
        return standAloneChannelMatch.handleSpeakersIfMatch(speakers);
    }
}
