package MVP.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ryan.testpackapp.R;

import java.util.List;

import MVP.model.Speaker;
import MVP.presenter.ISpeakerPresenter;
import MVP.presenter.SpeakerPresenter;

/**
 * Created by ryan on 4/18/16.
 */
public class SpeakerView extends AppCompatActivity implements ISpeakerView{
    private ISpeakerPresenter iSpeakerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_pattern);
        iSpeakerPresenter = new SpeakerPresenter(this);
    }

    @Override
    public void initView() {
        findViewById(R.id.dosth_mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSpeakerPresenter.removeSpeaker(2);
            }
        });
    }

    @Override
    public void detectSpeakerComplete(List<Speaker> speakers) {

    }
}
