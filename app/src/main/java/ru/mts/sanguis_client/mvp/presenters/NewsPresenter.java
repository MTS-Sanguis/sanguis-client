package ru.mts.sanguis_client.mvp.presenters;

import com.arellomobile.mvp.MvpPresenter;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import ru.mts.sanguis_client.mvp.views.NewsView;

/**
 * Created by nmurzin on 10/06/2017.
 */

public class NewsPresenter extends MvpPresenter<NewsView> {
    public void populateNews() throws RSSReaderException {

        RSSReader reader = new RSSReader();
        String uri = "http://yadonor.ru/rss/news.rss?region=35";
        //RSSFeed feed = reader.load(uri);

        //getViewState().setNews(feed.getItems());
    }
}
