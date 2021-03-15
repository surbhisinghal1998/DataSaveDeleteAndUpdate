package com.solution.datasaveanddelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    Context context;
    private List<String> data;
    private GetItem getItem;

    public AdapterClass(Context context, List<String> data, GetItem getItem) {
        this.context = context;
        this.data = data;
        this.getItem = getItem;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(data.get(position));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String theRemovedItem = data.get(position);
                data.remove(position);  // remove the item from list
                notifyItemRemoved(position); // notify the adapter about the removed item
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem.getItemValue(position,data.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private Button delete;
        private Button update;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            delete=itemView.findViewById(R.id.deletebutton);
            update=itemView.findViewById(R.id.updatabutton);

        }
    }

    interface GetItem{
        void getItemValue(int position, String name);
    }
}
