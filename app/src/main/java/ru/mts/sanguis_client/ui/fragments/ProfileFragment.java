package ru.mts.sanguis_client.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import ru.mts.sanguis_client.ui.activities.BonucesActivity;
import ru.mts.sanguis_client.ui.activities.FaqActivity;
import ru.mts.sanguis_client.ui.activities.MainActivity;
import ru.mts.sanguis_client.ui.adapters.InfoListAdapter;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView, View.OnClickListener {

    @BindView(R.id.fragment_profile_name) TextView tvUserName;
    @BindView(R.id.fragment_profile_photo) CircleImageView photo;
    @BindView(R.id.fragment_profile_description) TextView tvDescription;
    @BindView(R.id.fragment_profile_blood) TextView tvBlood;

    @BindView(R.id.fragment_profile_calendar) RelativeLayout rlCalrndar;
    @BindView(R.id.fragment_profile_faq) RelativeLayout rlFaq;
    @BindView(R.id.fragment_profile_bonuce) RelativeLayout rlBonuce;
    @BindView(R.id.fragment_profile_charity) RelativeLayout rlCharity;

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
        rlCalrndar.setOnClickListener(this);
        rlFaq.setOnClickListener(this);
        rlBonuce.setOnClickListener(this);
        rlCharity.setOnClickListener(this);

        presenter.setCurrentInformation();
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

    @Override
    public void setBlood(String setBlood) {
        tvBlood.setText(setBlood);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fragment_profile_calendar:
                Activity activity = getActivity();
                if(activity instanceof MainActivity){
                    ((MainActivity) activity).setCalendar();
                }
                break;
            case R.id.fragment_profile_faq:
                startActivity(new Intent(getContext(), FaqActivity.class));
                break;
            case R.id.fragment_profile_bonuce:
                startActivity(new Intent(getContext(), BonucesActivity.class));
                break;
            case R.id.fragment_profile_charity:

                break;
            default:
                break;
        }
    }
}
