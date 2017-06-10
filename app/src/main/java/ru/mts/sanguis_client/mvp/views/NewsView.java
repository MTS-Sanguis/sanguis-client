package ru.mts.sanguis_client.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import ru.mts.sanguis_client.mvp.models.NewsModel;

import java.util.List;

public interface NewsView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void setNews(List<NewsModel> news);

}
