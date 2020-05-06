package com.example.administrator.fta8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class filter6Adapter extends RecyclerView.Adapter<filter6Adapter.Viewholder>{
    private final ClickListener listener;
    ArrayList<filter6Item> filter6;
    Context context;

    public filter6Adapter(ArrayList<filter6Item> filter6, Context context, ClickListener listener) {
        this.listener = listener;
        this.filter6 = filter6;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.filter6item, viewGroup, false);
        int itemWidth = viewGroup.getMeasuredWidth() / 3;

        return new Viewholder(itemView,listener,itemWidth);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        String imgDir = filter6.get(i).getImgFilter6Dir();
        String filter6PicDir = filter6.get(i).getFilterPicDir();

        Picasso.get().load(new File(filter6PicDir + "/" + imgDir + ".jpg")).into(viewholder.imgJFilter6);
    }

    @Override
    public int getItemCount() {
        return filter6.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        LinearLayout llJParentItem;
        ImageView imgJFilter6;
        private WeakReference<ClickListener> listenerRef;

        public Viewholder(@NonNull View itemView, ClickListener listener, int itemWidth) {

            super(itemView);
            this.llJParentItem = itemView.findViewById(R.id.llfilter6Parent);
            this.imgJFilter6 = itemView.findViewById(R.id.imgFilter6);

            //llJParentItem.getLayoutParams().width = itemWidth;
            //llJParentItem.getLayoutParams().width = 300;

            this.listenerRef = new WeakReference<>(listener);

            imgJFilter6.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listenerRef.get().onFilter6LongClick(getAdapterPosition());
                    return true;
                }
            });

            imgJFilter6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerRef.get().onFilter6Click(getAdapterPosition());
                }
            });

        }
    }
}
