package ru.mts.sanguis_client.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.presenters.ProfilePresenter;
import ru.mts.sanguis_client.mvp.views.ProfileView;
import ru.mts.sanguis_client.ui.adapters.InfoListAdapter;

import java.util.HashMap;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {

    @BindView(R.id.fragment_profile_name) TextView tvUserName;
    @BindView(R.id.fragment_profile_photo) CircleImageView photo;
    @BindView(R.id.fragment_profile_description) TextView tvDescription;

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
    }


    @Override
    public void setProfileFoto(Drawable drawable) {
        photo.setImageDrawable(drawable);
    }

    @Override
    public void setProfileName(String name) {
        tvUserName.setText(name);
    }


    @Override
    public void setDescription(String description) {
        tvDescription.setText(description);
    }
}
