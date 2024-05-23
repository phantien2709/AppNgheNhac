package com.example.nhaconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhaconline.Activity.PlayNhacActivity;
import com.example.nhaconline.Model.Baihat;
import com.example.nhaconline.R;
import com.example.nhaconline.SQLite.APP;
import com.example.nhaconline.SQLite.FavoriteDB;
import com.example.nhaconline.Service.APIService;
import com.example.nhaconline.Service.Dataservice;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;
    int i = 0;
    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtten.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);
        holder.imgfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i == 0){
                    holder.imgfavorite.setImageResource(R.drawable.iconloved);
                    String tenbaihat =baihat.getTenbaihat();
                    String casi = baihat.getCasi();
                    String hinhbaihat = baihat.getHinhbaihat();
                    String idbaihat = baihat.getIdbaihat();
                    String linkbaihat = baihat.getLinkbaihat();
                    Baihat baihat = new Baihat(idbaihat,tenbaihat,casi,hinhbaihat,linkbaihat);
                    APP.insert(context,baihat);
                    i=1;
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return baihatArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtcasi;
        ImageView imghinh,imgluotthich , imgfavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi=itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
            imgfavorite = itemView.findViewById(R.id.Ivfavorite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
//            imgfavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    imgfavorite.setImageResource(R.drawable.iconloved);
//                    String tenbaihat = baihatArrayList.get(getAdapterPosition()).getTenbaihat();
//                    String casi = baihatArrayList.get(getAdapterPosition()).getCasi();
//                    String hinhbaihat = baihatArrayList.get(getAdapterPosition()).getHinhbaihat();
//                    String idbaihat = baihatArrayList.get(getAdapterPosition()).getIdbaihat();
//                    String linkbaihat = baihatArrayList.get(getAdapterPosition()).getLinkbaihat();
//                    Baihat baihat = new Baihat(idbaihat,tenbaihat,casi,hinhbaihat,linkbaihat);
//                    APP.insert(context,baihat);
//                    baihatArrayList.clear();
//
//                }
//            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.baseline_star_24);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback=dataservice.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
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
