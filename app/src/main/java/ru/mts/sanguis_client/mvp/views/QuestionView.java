package ru.mts.sanguis_client.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface QuestionView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void updateQuestion(String question);
    @StateStrategyType(SkipStrategy.class)
    void showResult(boolean good);
    @StateStrategyType(SkipStrategy.class)
    void updateTitle(String title);
}
