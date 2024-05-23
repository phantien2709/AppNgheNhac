package com.example.nhaconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhaconline.Activity.PlayNhacActivity;
import com.example.nhaconline.Model.Baihat;
import com.example.nhaconline.R;
import com.example.nhaconline.SQLite.APP;
import com.example.nhaconline.Service.APIService;
import com.example.nhaconline.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenbaihat());
        holder.txtTencasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imgbaihat);
        holder.imgfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgfavorite.setImageResource(R.drawable.iconloved);
                String tenbaihat =baihat.getTenbaihat();
                String casi = baihat.getCasi();
                String hinhbaihat = baihat.getHinhbaihat();
                String idbaihat = baihat.getIdbaihat();
                String linkbaihat = baihat.getLinkbaihat();
                Baihat baihat = new Baihat(idbaihat,tenbaihat,casi,hinhbaihat,linkbaihat);
                APP.insert(context,baihat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat,txtTencasi;
        ImageView imgbaihat,imgluotthich, imgfavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat=itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtTencasi=itemView.findViewById(R.id.textviewsearchcasi);
            imgbaihat=itemView.findViewById(R.id.imageviewsearchbaihat);
            imgluotthich=itemView.findViewById(R.id.imageviewsearchluotthich);
            imgfavorite = itemView.findViewById(R.id.Ivfavorite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.baseline_star_24);
                    Dataservice dataservice= APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
