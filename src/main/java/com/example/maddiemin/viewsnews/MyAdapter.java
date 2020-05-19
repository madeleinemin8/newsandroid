package com.example.maddiemin.viewsnews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Article> values;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ImageView imgSource;
        public TextView txtHeadline;
        //public TextView txtDescription;
        public View layout;
        private String url;
        private final Context context;
        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            layout = v;
            txtHeadline = (TextView) v.findViewById(R.id.headline_line);
            //txtDescription = (TextView) v.findViewById(R.id.description_line);
            img = (ImageView)v.findViewById(R.id.article_icon);
            imgSource = (ImageView)v.findViewById(R.id.source_icon);
            url = "google.com";
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("address", url);
                    context.startActivity(intent);
                }
            });
        }
    }
    public void add (int position, Article art) {
        values.add(position, art);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(List<Article> myDataset) {
        values = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.article_display, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Article art = values.get(position);

        //source images
        if(art.getSrc().equals("abc-news-au")) {
            holder.imgSource.setImageResource(R.drawable.abc_logo);
        }
        if(art.getSrc().equals("the-new-york-times")) {
            holder.imgSource.setImageResource(R.drawable.nyt_logo);
        }
        if(art.getSrc().equals("business-insider")) {
            holder.imgSource.setImageResource(R.drawable.bi_logo);
        }
        if(art.getSrc().equals("breitbart-news")) {
            holder.imgSource.setImageResource(R.drawable.breitbart_logo);
        }
        if(art.getSrc().equals("the-wall-street-journal")) {
            holder.imgSource.setImageResource(R.drawable.wsj_logo);
        }
        if(art.getSrc().equals("associated-press")) {
            holder.imgSource.setImageResource(R.drawable.ap_logo);
        }
        if(art.getSrc().equals("the-washington-post")) {
            holder.imgSource.setImageResource(R.drawable.wapo_logo);
        }
        //the rest
        holder.txtHeadline.setText(art.getHead());
        /*holder.txtDescription.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        holder.txtDescription.setText(art.getDesc()); */
        //url image loading
        String address = art.getiUrl();
        holder.url = art.getLink();
        new AsyncTaskLoadImage(holder.img).execute(address);
    }
    @Override
    public int getItemCount() {
        return values.size();
    }
}
