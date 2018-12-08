package com.example.administrator.fta8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class outputAdapter extends RecyclerView.Adapter<outputAdapter.Viewholder>{
    private final ClickListener listener;
    ArrayList<outputItem> output;
    Context context;

    public outputAdapter(ArrayList<outputItem> output, Context context, ClickListener listener) {
        this.listener = listener;
        this.output = output;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.outputitem, viewGroup, false);
        int itemWidth = viewGroup.getMeasuredWidth() / 3;

        return new Viewholder(itemView,listener,itemWidth);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        String cause = output.get(i).getCause();
        String[] desPic = output.get(i).getDescripPic();
        String desPicDir = output.get(i).descripPicDir;
        ArrayAdapter adapterDes = new ArrayAdapter(context,R.layout.mytextview,output.get(i).getDescrip());
        ArrayAdapter adapterAction = new ArrayAdapter(context,R.layout.mytextview,output.get(i).getAction());
        ArrayAdapter adapterDetail = new ArrayAdapter(context,R.layout.mytextview,output.get(i).getDetail());

        viewholder.tvJCause.setText(cause);
        Picasso.get().load(new File(desPicDir + "/" + desPic[0] + ".jpg")).into(viewholder.imgJDescrip);
        viewholder.lvJDescrip.setAdapter(adapterDes);
        viewholder.lvJAction.setAdapter(adapterAction);
        viewholder.lvJDetail.setAdapter(adapterDetail);

    }

    @Override
    public int getItemCount() {
        return output.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        LinearLayout llJParentItem;
        TextView tvJCause;
        ImageView imgJDescrip;
        ListView lvJDescrip;
        ListView lvJAction;
        ListView lvJDetail;
        private WeakReference<ClickListener> listenerRef;

        public Viewholder(@NonNull View itemView, ClickListener listener, int itemWidth) {

            super(itemView);
            this.llJParentItem = itemView.findViewById(R.id.llparentItem);
            //llJParentItem.getLayoutParams().width = itemWidth;
            //llJParentItem.getLayoutParams().width = 600;

            this.tvJCause = itemView.findViewById(R.id.tvCause);
            this.imgJDescrip = itemView.findViewById(R.id.imgDes);
            this.lvJDescrip = itemView.findViewById(R.id.lvDes);
            this.lvJAction = itemView.findViewById(R.id.lvAction);
            this.lvJDetail = itemView.findViewById(R.id.lvDetail);

            this.listenerRef = new WeakReference<>(listener);

            imgJDescrip.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listenerRef.get().onOutputImgLongClicked(getAdapterPosition());
                    return true;
                }
            });

            lvJDescrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listenerRef.get().onOutputDescripSelected(getAdapterPosition(),position);
                }
            });

            lvJAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listenerRef.get().onOutputActionSelected(getAdapterPosition(),position);
                }
            });

            lvJDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listenerRef.get().onOutputDetailSelected(getAdapterPosition(),position);
                }
            });

        }
    }
}
