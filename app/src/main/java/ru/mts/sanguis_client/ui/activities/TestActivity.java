package ru.mts.sanguis_client.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.presenters.QuestionPresenter;
import ru.mts.sanguis_client.mvp.views.QuestionView;

public class TestActivity extends MvpAppCompatActivity implements View.OnClickListener, QuestionView{

    @InjectPresenter
    QuestionPresenter presenter;

    @BindView(R.id.activity_test_yes)
    Button btnYes;
    @BindView(R.id.activity_test_no)
    Button btnNo;
    @BindView(R.id.activity_test_question)
    TextView tvQuestion;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_test);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Вопрос #1");
        }

        ButterKnife.bind(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.activity_test_yes:
                presenter.getNextQuestion(true);
                break;
            case R.id.activity_test_no:
                presenter.getNextQuestion(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateQuestion(String question) {
        tvQuestion.setText(question);
    }

    @Override
    public void showResult(boolean good) {
        //nothing yet
    }

    @Override
    public void updateTitle(String title) {
        //nothing yet
    }
}
