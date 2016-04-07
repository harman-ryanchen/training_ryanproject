package designPatern.AigeStudio;

import java.util.List;

/**
 *
 * Created by ryan on 4/5/16.
 */
public abstract class ChannelMatch {
    private ChannelMatch superChannel;

    /**
     * define how many speaker can match this channel
     *
     */
    public ChannelMatch() {
    }

    public void setSuperChannel(ChannelMatch superChannel) {
        this.superChannel = superChannel;
    }

    /**
     *
     * @param speakers
     */
    public ChannelMatch handleSpeakersIfMatch(List<Speaker> speakers){

        if (chackSpeakersMatch(speakers)){
            // yes, it is mstch
            return this;
        }else{
            if (superChannel!=null){
                //looking next channel if match
                superChannel.chackSpeakersMatch(speakers);
            }else{
                //no match channel
                return null;
            }
        }

        return null;
    }

    /**
     * chcke the list whether match the channel
     * @param speakers
     * @return
     */
    public abstract boolean chackSpeakersMatch(List<Speaker> speakers);

}
