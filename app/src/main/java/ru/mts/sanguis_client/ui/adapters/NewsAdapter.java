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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewHolder> {

    private List<RSSItem> news;

    @Override
    public NewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("news", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewHolder holder, int position) {
        RSSItem item = news.get(position);
        Log.d("news", "onBindViewHolder");

        holder.setTitle(item.getTitle());
        holder.setDescription(Html.fromHtml(item.getDescription()));
        try {
            if (item.getThumbnails().size() > 0)
                holder.setThumbnail(item.getThumbnails().get(0).getUrl().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int getItemCount() {
        if (news != null)
            Log.d("news", String.format("Size: %d", news.size()));
        return news == null?0:news.size();
    }

    public void setNews(List<RSSItem> news){
        this.news = news;
        Log.d("news", "setNews");
    }

    public List<RSSItem> getNews() {
        return this.news;
    }



    class NewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;
        private ImageView thumbnail;

        Drawable drawable;

        NewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.item_news_titile);
            description = (TextView) itemView.findViewById(R.id.item_news_descripion);
            thumbnail = (ImageView) itemView.findViewById(R.id.item_news_thumb);
        }

        void setTitle(String title){
            this.title.setText(title);
        }

        void setDescription(Spanned string){
            this.description.setText(string);
        }

        class GetPicture extends AsyncTask<String, Void, Drawable> {
            public Drawable drawableFromUrl(String url) throws IOException {
                Bitmap x;

                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();

                x = BitmapFactory.decodeStream(input);
                return new BitmapDrawable(x);
            }

            protected Drawable doInBackground(String... uri) {
                Drawable drawable = null;

                try {
                    drawable = drawableFromUrl(uri[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return drawable;
            }

            protected void onPostExecute(Drawable getDrawable) {
                thumbnail.setImageDrawable(getDrawable);
            }
        }

        void setThumbnail(String url) throws IOException {
            Log.i("thumbnail", url);
            new GetPicture().execute(url);
        }
    }

}
