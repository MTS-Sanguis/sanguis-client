package ru.mts.sanguis_client.mvp.presenters;

import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import ru.mts.sanguis_client.mvp.views.NewsView;

/**
 * Created by nmurzin on 10/06/2017.
 */

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {
    class GetNews extends AsyncTask<String, Void, RSSFeed> {
        protected RSSFeed doInBackground(String... uri) {
            RSSReader reader = new RSSReader();
            RSSFeed feed = null;
            try {
                feed = reader.load(uri[0]);
            } catch (RSSReaderException e) {
                e.printStackTrace();
            }

            return feed;
        }

        protected void onPostExecute(RSSFeed feed) {
            Log.d("feed", feed.getTitle());
            
            getViewState().setNews(feed.getItems().subList(0, 10));
        }
    }

    public void populateNews() throws RSSReaderException {
        new GetNews().execute("http://yadonor.ru/rss/news.rss?region=35");
    }
}
