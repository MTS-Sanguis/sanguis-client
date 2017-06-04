package ru.mts.sanguis_client.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.ui.adapters.InfoListAdapter;

public class ProfileFragment extends MvpAppCompatFragment {

    @BindView(R.id.fragment_profile_info_list) RecyclerView rvInfoList;

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
        rvInfoList.setAdapter(new InfoListAdapter());
    }

}
