package com.example.nhaconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhaconline.Activity.DanhsachbaihatActivity;
import com.example.nhaconline.Model.Album;
import com.example.nhaconline.Model.Baihat;
import com.example.nhaconline.Model.Playlist;
import com.example.nhaconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> mangplaylist;

    public PlaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangplaylist.get(position);
        holder.txttenplaylist.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgbackground);;
        Picasso.with(context).load(playlist.getIcon()).into(holder.imgplaylist);

    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttenplaylist;
        ImageView imgbackground, imgplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenplaylist = itemView.findViewById(R.id.tvtenplaylist);
            imgbackground = itemView.findViewById(R.id.ivbackgroudplaylist);
            imgplaylist=itemView.findViewById(R.id.ivplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
