package ru.mts.sanguis_client.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;

import java.util.List;

/**
 * Created by nmurzin on 10/06/2017.
 */

public interface NewsView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void setNews(List<RSSItem> news);

}
