package ru.mts.sanguis_client.mvp.views;

import android.graphics.drawable.Drawable;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.HashMap;
import java.util.List;

@StateStrategyType(SkipStrategy.class)
public interface ProfileView extends MvpView {

    //передаешь Drawable, который как-либо создаешь уже внутри контекста Android
    void setProfileFoto(Drawable drawable);
    void setProfileName(String name);
    //Пары ключ-значение, где ключ это имя поля ("ФИО", "Адрес", "Телефон"), а значение это хбуквально значение
    // ("Иванов Иван Иванович", "г. Москва" итд)
    void setDescription(String description);
}
