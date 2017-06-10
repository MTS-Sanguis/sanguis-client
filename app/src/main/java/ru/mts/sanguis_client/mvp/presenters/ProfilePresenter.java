package ru.mts.sanguis_client.mvp.presenters;

import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.mts.sanguis_client.common.Utils;
import ru.mts.sanguis_client.mvp.views.ProfileView;

import java.util.HashMap;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    private HashMap<String, String> profileFields;

    @Override
    public void onFirstViewAttach(){
        Log.d(Utils.PRESENTER_DEBUG_TAG, "Created:" + getClass().getSimpleName());
        //далее все что угодно по загрузке в профиль инфомрации.
    }

    public void setCurrentInformation(){
        getViewState().setProfileName("Петров Петр Петрович");
        if(profileFields == null)
            loadProfileFields();

        getViewState().setProfileFields(new HashMap<>(profileFields)); //ради ищбежания утечки, отсоединяю ссылки.
    }

    private void loadProfileFields(){
        profileFields = new HashMap<>();
        //загружать информацию.
    }

}
