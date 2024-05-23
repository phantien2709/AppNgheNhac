package com.example.nhaconline.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhaconline.Adapter.BaihathotAdapter;
import com.example.nhaconline.Adapter.FavoriteAdapter;
import com.example.nhaconline.Model.Baihat;
import com.example.nhaconline.R;
import com.example.nhaconline.SQLite.APP;
import com.example.nhaconline.SQLite.FavoriteDB;
import com.example.nhaconline.Service.APIService;
import com.example.nhaconline.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Favarite extends Fragment {
    View view;
    RecyclerView recyclerViewFavorite;
    FavoriteAdapter favoriteAdapter;
    FavoriteDB favoriteDB;
    ArrayList<Baihat> baihatArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite,container,false);
        recyclerViewFavorite = view.findViewById(R.id.recyclerviewfavorite);
        GetData();
        return view;
    }

    private void GetData() {
        favoriteDB = new FavoriteDB(getActivity());
        baihatArrayList = APP.getBaiHatFav(getActivity());
        favoriteAdapter = new FavoriteAdapter(getActivity(),baihatArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFavorite.setLayoutManager(linearLayoutManager);
        recyclerViewFavorite.setAdapter(favoriteAdapter);
    }


}
