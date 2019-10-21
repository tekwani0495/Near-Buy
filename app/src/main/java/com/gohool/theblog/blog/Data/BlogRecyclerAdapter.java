package com.gohool.theblog.blog.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gohool.theblog.blog.Model.Blog;
import com.gohool.theblog.blog.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by paulodichone on 4/17/17.
 */

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Blog> blogList;

    public BlogRecyclerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_product, parent, false);


        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Blog blog = blogList.get(position);
        String imageUrl = null;

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())).getTime());

        //holder.title.setText(blog.getTitle());
        holder.timestamp.setText(formattedDate);

        holder.shop.setText("ShopType: "+blog.getShopType());
        holder.price.setText("Price: "+blog.getPrice());

        imageUrl = blog.getImage();


        //TODO: Use Picasso library to load image
        Picasso.with(context)
                .load(imageUrl)
                .into(holder.image);

        holder.title.setText(blog.getUsername());
        holder.desc.setText("Description: "+blog.getDesc());
        holder.qty.setText("Quantity: "+blog.getQty());


    }

    public void setUserData(String name){

//        blogUserImage = mView.findViewById(R.id.blog_user_image);
//        TextView blogUserName = mView.findViewById(R.id.blog_user_name);
//
//        blogUserName.setText(name);
//
//        RequestOptions placeholderOption = new RequestOptions();
//        placeholderOption.placeholder(R.drawable.profile_placeholder);
//
//        Glide.with(context).applyDefaultRequestOptions(placeholderOption).load(image).into(blogUserImage);

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public TextView shoptype;

        public TextView qty;
        public TextView desc;
        public TextView timestamp;
        public TextView title;
        public TextView shop;
        public ImageView image;
        public TextView price;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;

            qty = (TextView) view.findViewById(R.id.quantity);
            desc = (TextView) view.findViewById(R.id.postTextList);
            image = (ImageView) view.findViewById(R.id.postImageList);
            timestamp = (TextView) view.findViewById(R.id.timestampList);
            title = (TextView) view.findViewById(R.id.blog_user_name);
            shop=(TextView)view.findViewById(R.id.shoptype);
            price=(TextView)view.findViewById(R.id.price);

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to the next activity...

                }
            });

        }
    }
}
