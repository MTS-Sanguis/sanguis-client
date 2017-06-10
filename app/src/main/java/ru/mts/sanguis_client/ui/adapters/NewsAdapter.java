package ru.mts.sanguis_client.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
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
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewHolder> {

    private List<NewsModel> news;

    private ArrayList<Drawable> drawables;
    public NewsAdapter(Context context){
        drawables = new ArrayList<>();

        drawables.add(ContextCompat.getDrawable(context, R.drawable.bacteria));
        drawables.add(ContextCompat.getDrawable(context, R.drawable.virus));
        drawables.add(ContextCompat.getDrawable(context, R.drawable.medicine));
        drawables.add(ContextCompat.getDrawable(context, R.drawable.thumbnail));
    }

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
        holder.setThumbnail(drawables.get(position % drawables.size()));


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
        private ImageView background;

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

        void setThumbnail(Drawable thumbnail){
            this.thumbnail.setImageDrawable(thumbnail);
            //this.background.setImageDrawable(thumbnail);
        }
    }
}
