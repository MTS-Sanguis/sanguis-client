package ru.mts.sanguis_client.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.mcsoxford.rss.RSSItem;
import ru.mts.sanguis_client.R;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewHolder> {

    private List<RSSItem> news;

    @Override
    public NewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewHolder holder, int position) {
        RSSItem item = news.get(position);

        holder.setTitle(item.getTitle());
        holder.setDescription(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return news == null?0:news.size();
    }

    public void setNews(List<RSSItem> news){
        this.news = news;
    }

    class NewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;

        NewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.item_news_titile);
            description = (TextView) itemView.findViewById(R.id.item_news_descripion);
        }

        void setTitle(String title){
            this.title.setText(title);
        }

        void setDescription(String string){
            this.description.setText(string);
        }
    }

}
