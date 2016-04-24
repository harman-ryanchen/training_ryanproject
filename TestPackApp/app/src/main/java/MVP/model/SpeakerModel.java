package MVP.model;

import java.util.List;

/**
 * Created by ryan on 4/18/16.
 */
public class SpeakerModel implements ISpeakerModel{
    private List<Speaker> speakers;
    @Override
    public List<Speaker> getSpeakers() {
        return speakers;
    }

}
