package ru.mts.sanguis_client.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.presenters.ProfilePresenter;
import ru.mts.sanguis_client.mvp.views.ProfileView;
import ru.mts.sanguis_client.ui.adapters.InfoListAdapter;

import java.util.HashMap;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {

    @BindView(R.id.fragment_profile_info_list) RecyclerView rvInfoList;

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "ProfilePresenter")
    ProfilePresenter presenter;

    private InfoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater,parent,savedInstance);
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);

        rvInfoList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new InfoListAdapter();
        rvInfoList.setAdapter(this.adapter);
    }


    @Override
    public void setProfileFoto(Drawable drawable) {
        //пкачто нету элемента фотгшрафии, я это дополню
    }

    @Override
    public void setProfileName(String name) {

    }

    @Override
    public void setProfileFields(HashMap<String, String> fields) {

    }
}
