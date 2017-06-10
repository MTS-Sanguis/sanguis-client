package ru.mts.sanguis_client.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReaderException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.models.NewsModel;
import ru.mts.sanguis_client.mvp.presenters.NewsPresenter;
import ru.mts.sanguis_client.mvp.views.NewsView;
import ru.mts.sanguis_client.ui.adapters.NewsAdapter;

public class NewsFragment extends MvpAppCompatFragment implements NewsView {

    @InjectPresenter
    NewsPresenter presenter;

    @BindView(R.id.fragment_news_list) RecyclerView rvNews;

    private NewsAdapter adapter;

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
        adapter = new NewsAdapter(getContext());
        rvNews.setAdapter(adapter);
        rvNews.setNestedScrollingEnabled(false);
    }

    @Override
    public void setNews(List<NewsModel> news) {
        if(adapter != null) {
            adapter.setNews(news);
            adapter.notifyDataSetChanged();
        }
    }
}
