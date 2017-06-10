package ru.mts.sanguis_client.mvp.presenters;

import android.os.AsyncTask;
import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;
import ru.mts.sanguis_client.mvp.models.NewsModel;
import ru.mts.sanguis_client.mvp.views.NewsView;

import java.util.ArrayList;


@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {
//    class GetNews extends AsyncTask<String, Void, RSSFeed> {
//        protected RSSFeed doInBackground(String... uri) {
//            RSSReader reader = new RSSReader();
//            RSSFeed feed = null;
//            try {
//                feed = reader.load(uri[0]);
//            } catch (RSSReaderException e) {
//                e.printStackTrace();
//            }
//
//            return feed;
//        }
//
//        protected void onPostExecute(RSSFeed feed) {
//            Log.d("feed", feed.getTitle());
//
//            getViewState().setNews(feed.getItems().subList(0, 10)); //ЧЕ?!
//        }
//    }

    private ArrayList<NewsModel> news;

    @Override
    public void onFirstViewAttach(){
        loadNewsFeed();

        getViewState().setNews(new ArrayList<>(news));
    }

    private void loadNewsFeed(){
        news = new ArrayList<>();
        for(int i=0;i<15;i++){
            news.add(new NewsModel("Новость "+i, "Длинное захватывающее описание, которое привлекает внимание "+ i));
        }
    }
}
