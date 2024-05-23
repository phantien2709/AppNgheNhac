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
import androidx.viewpager.widget.PagerAdapter;

import com.example.nhaconline.Activity.DanhsachbaihatActivity;
import com.example.nhaconline.Model.Quangcao;
import com.example.nhaconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter  {
    Context context;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    ArrayList<Quangcao> arrayListbanner;
    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_banner,null);

        ImageView imgbackgroundbanner = view.findViewById(R.id.ivBackgroudbanner);
        ImageView imgsongbanner=view.findViewById(R.id.ivbanner);
        TextView txttitlesongbanner = view.findViewById(R.id.tvTitlebannerbaihat);
        TextView txtnoidung=view.findViewById(R.id.tvnoidung);

        Picasso.with(context).load(arrayListbanner.get(position).getHinhanh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(imgsongbanner);
        txttitlesongbanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
