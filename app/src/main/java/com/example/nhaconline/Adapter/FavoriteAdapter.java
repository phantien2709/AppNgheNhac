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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>{
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public void notifyDataSetChanged1(){
        super.notifyDataSetChanged();
    }

    public FavoriteAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }
    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_favorite,parent,false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtten.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    class FavViewHolder extends RecyclerView.ViewHolder{

        TextView txtten,txtcasi;
        ImageView imghinh,imgluotthich, imgfavorite;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi=itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
            imgfavorite = itemView.findViewById(R.id.Ivfavorite);
            imgfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean rs = APP.delete(context, baihatArrayList.get(getAdapterPosition()).getIdbaihat());
                    if (rs) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                        resetData();
                    } else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_LONG).show();
                    }
                    notifyDataSetChanged1();
                }

            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
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

    void resetData() {
        baihatArrayList.clear();
        baihatArrayList.addAll(APP.getBaiHatFav(context));
    }
}
