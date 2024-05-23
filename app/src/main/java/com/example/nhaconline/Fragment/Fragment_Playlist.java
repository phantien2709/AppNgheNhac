package com.example.nhaconline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhaconline.Activity.DanhsachbaihatActivity;
import com.example.nhaconline.Activity.DanhsachcacplaylistActivity;
import com.example.nhaconline.Adapter.PlaylistAdapter;
import com.example.nhaconline.Model.Playlist;
import com.example.nhaconline.R;
import com.example.nhaconline.Service.APIService;
import com.example.nhaconline.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView txttitleplaylist, txtviewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        recyclerView=view.findViewById(R.id.rvplaylist);
        txttitleplaylist=view.findViewById(R.id.tvtitleplaylist);
        txtviewxemthemplaylist=view.findViewById(R.id.tvviewmoreplaylist);
        GetData();
        txtviewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachcacplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Playlist>> callback=dataservice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter=new PlaylistAdapter(getActivity(),mangplaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

}
