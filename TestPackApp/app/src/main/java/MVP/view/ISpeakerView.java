package MVP.view;

import java.util.List;

import MVP.model.Speaker;
import MVP.presenter.ISpeakerPresenter;

/**
 * Created by ryan on 4/18/16.
 */
public interface ISpeakerView {
    void initView();
    void detectSpeakerComplete(List<Speaker> speakers);
}
