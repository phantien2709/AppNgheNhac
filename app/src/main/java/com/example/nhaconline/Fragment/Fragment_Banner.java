package com.example.nhaconline.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nhaconline.Adapter.BannerAdapter;
import com.example.nhaconline.Model.Quangcao;
import com.example.nhaconline.R;
import com.example.nhaconline.Service.APIService;
import com.example.nhaconline.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    Handler handler;
    Runnable runnable;
    int currentItem;
    BannerAdapter bannerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        anhxa();
        GetData();
        return  view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewpager);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Quangcao>> callback= dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners=(ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem=viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem>=viewPager.getAdapter().getCount())
                        {
                            currentItem =0;
                        }
                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable,6500);
                    }
                };
                handler.postDelayed(runnable,6500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }
}
