package ru.mts.sanguis_client.ui.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.models.NewsModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewHolder> {

    private List<NewsModel> news;

    @Override
    public NewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("news", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewHolder holder, int position) {
        NewsModel item = news.get(position);
        Log.d("news", "onBindViewHolder");

        holder.setTitle(item.getTitle());
        holder.setDescription(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return news == null?0:news.size();
    }

    public void setNews(List<NewsModel> news){
        this.news = news;
    }

    class NewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private ImageView thumbnail;

        Drawable drawable;

        NewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_news_titile);
            description = (TextView) itemView.findViewById(R.id.item_news_descripion);
            thumbnail = (ImageView) itemView.findViewById(R.id.item_news_thumb);
        }

        void setTitle(String title) {
            this.title.setText(title);
        }

        void setDescription(String string) {
            this.description.setText(string);
        }
    }
}
