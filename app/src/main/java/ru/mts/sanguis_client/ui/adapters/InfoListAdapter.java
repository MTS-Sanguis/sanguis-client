package ru.mts.sanguis_client.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ru.mts.sanguis_client.R;

import java.util.HashMap;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ItemHolder> {

    private HashMap<String, String> profileFields;

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setInfo("Какое-либо поле:", "Содержание поля");
    }

    @Override
    public int getItemCount() {
        return profileFields==null?0:profileFields.size();//просто константа из головы
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvField;
        private TextView tvValue;

        ItemHolder(View itemView) {
            super(itemView);

            tvField = (TextView) itemView.findViewById(R.id.item_list_field);
            tvValue = (TextView) itemView.findViewById(R.id.item_list_value);
        }

        void setInfo(String field, String value){
            tvField.setText(field);
            tvValue.setText(value);
        }
    }

    public void setProfileFields(HashMap<String, String> fields){
        this.profileFields = fields;
        this.notifyDataSetChanged();
    }
}
