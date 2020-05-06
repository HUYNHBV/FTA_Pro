package com.example.huynhbv.recycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class imageFilterAdapter extends RecyclerView.Adapter<imageFilterAdapter.Viewholder>{
    private final ClickListener listener;
    ArrayList<imageFilter> filter;

    public imageFilterAdapter(ArrayList<imageFilter> filter, ClickListener listener) {
        this.listener = listener;
        this.filter = filter;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.filterimage, viewGroup, false);
        int width = viewGroup.getMeasuredWidth() / 3;

        return new Viewholder(itemView,listener,width);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.imgFilter.setImageResource(filter.get(i).getImgsrc());
    }

    @Override
    public int getItemCount() {
        return filter.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener {

        private ImageView imgFilter;
        private WeakReference<ClickListener> listenerRef;

        public Viewholder(@NonNull View itemView, ClickListener listener, int width) {

            super(itemView);

            this.listenerRef = new WeakReference<>(listener);
            this.imgFilter = itemView.findViewById(R.id.imgfilterImage);

            imgFilter.getLayoutParams().width = width;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imgFilter.setOnClickListener(this);
            imgFilter.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == imgFilter.getId()){
                listenerRef.get().onPositionClicked(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {

            if (v.getId() == imgFilter.getId()){
                listenerRef.get().onLongClicked(getAdapterPosition());
            }

            return true;
        }
    }
}
