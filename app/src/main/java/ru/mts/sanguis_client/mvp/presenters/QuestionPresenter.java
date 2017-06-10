package ru.mts.sanguis_client.mvp.presenters;

import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.mts.sanguis_client.mvp.views.QuestionView;

@InjectViewState
public class QuestionPresenter extends MvpPresenter<QuestionView> {

    public void getNextQuestion(boolean prevResult){
        if(prevResult)
            Log.d("TEST", "TRUE");
        else
            Log.d("TEST", "FALSE");
    }

}
