package MVP.presenter;

import MVP.model.ISpeakerModel;
import MVP.model.SpeakerModel;
import MVP.view.ISpeakerView;

/**
 * Created by ryan on 4/18/16.
 */
public class SpeakerPresenter implements ISpeakerPresenter {
    private ISpeakerModel iSpeakerModel;
    private ISpeakerView iSpeakerView;


    public SpeakerPresenter(ISpeakerView iSpeakerView) {
        this.iSpeakerView = iSpeakerView;
        iSpeakerModel = new SpeakerModel();

    }

    @Override
    public void showSpeakers() {
        iSpeakerView.detectSpeakerComplete(iSpeakerModel.getSpeakers());
    }

    @Override
    public void removeSpeaker(int i) {
        iSpeakerModel.getSpeakers().remove(i);
    }
}
