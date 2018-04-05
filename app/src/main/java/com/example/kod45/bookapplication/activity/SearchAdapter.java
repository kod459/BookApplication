package com.example.kod45.bookapplication.activity;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.kod45.bookapplication.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.parse.ParseFile;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kod45 on 04/04/2018.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> titleList;
    ArrayList<String> authorList;
    ArrayList<String> categoryList;
    ArrayList<String> priceList;
    ArrayList<String> quantityList;
    ArrayList<String> bookImageList;

    class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView mTitle, mAuthor;

        public SearchViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.bookImage);
            mTitle = (TextView) itemView.findViewById(R.id.titleSearchItem);
            mAuthor = (TextView) itemView.findViewById(R.id.authorSearchItem);

        }
    }

    public SearchAdapter(Context context, ArrayList<String> titleList, ArrayList<String> authorList, ArrayList<String> categoryList, ArrayList<String> priceList, ArrayList<String> quantityList,ArrayList<String> bookImageList) {
        this.context = context;
        this.titleList = titleList;
        this.authorList = authorList;
        this.categoryList = categoryList;
        this.priceList = priceList;
        this.quantityList = quantityList;
        this.bookImageList = bookImageList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.mTitle.setText(titleList.get(position));
        holder.mAuthor.setText(authorList.get(position));

        Picasso.with(context).load(bookImageList.get(position)).fit().placeholder(R.mipmap.ic_launcher_round).into(holder.bookImage);

        Uri imageUri = Uri.parse(bookImageList.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}