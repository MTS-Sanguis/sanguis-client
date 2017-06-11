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
        news.add(new NewsModel("11.06.2017", "15-й фестиваль татуировки в СПб"));
        news.add(new NewsModel("14.06.2017", "Всемирный день донора\nДонорская акция: \"Глиссада жизни\""));
        news.add(new NewsModel("15.06.2017", "1667 г. первое успешное переливание крови"));
        news.add(new NewsModel("24.06.2017", "День донора: рабочая суббота в ОПК ГКДБ Св. Владимира"));
        news.add(new NewsModel("28.06.2017", "Дни донора в общежитиях ФГБОУ ВО РНИМУ им. Н.И.Пирогова"));
    }
}
