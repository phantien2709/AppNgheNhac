package com.example.nhaconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhaconline.Activity.DanhsachbaihatActivity;
import com.example.nhaconline.Model.Playlist;
import com.example.nhaconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacplaylistAdapter extends RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> mangplaylist;

    public DanhsachcacplaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playlist.getTen());

    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist;
        TextView txttencasiplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen=itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist=itemView.findViewById(R.id.textviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
