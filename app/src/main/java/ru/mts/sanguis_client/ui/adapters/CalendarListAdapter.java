package ru.mts.sanguis_client.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ru.mts.sanguis_client.R;

public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListAdapter.ItemHolder>{


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subtitile, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setInfo("Событие "+position, "Описание события "+position);
    }

    @Override
    public int getItemCount() {
        return 12; //константа взята от балды на данный момент
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView subtitile;

        ItemHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_subtitle_title);
            subtitile = (TextView) itemView.findViewById(R.id.item_subtitle_subtitle);
        }

        void setInfo(String title, String subtiitle){
            this.title.setText(title);
            this.subtitile.setText(subtiitle);
        }
    }
}
