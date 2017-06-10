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
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.mcsoxford.rss.RSSItem;

import java.util.List;

import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.presenters.MapPresenter;
import ru.mts.sanguis_client.mvp.presenters.NewsPresenter;
import ru.mts.sanguis_client.mvp.views.NewsView;

public class NewsFragment extends MvpAppCompatFragment implements NewsView {

    @InjectPresenter
    NewsPresenter presenter;

    @BindView(R.id.fragment_news_list) RecyclerView rvNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater, parent, savedInstance);
        return inflater.inflate(R.layout.fragment_news, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);




        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
                return new RecyclerView.ViewHolder(view) {};
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 24; //очоердное число от балды
            }
        });
    }

    @Override
    public void setNews(List<RSSItem> news) {

    }
}
