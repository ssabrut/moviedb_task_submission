package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.uc.moviedb.R;
import com.uc.moviedb.adapter.NowPlayingAdapter;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_now_playing;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        rv_now_playing = findViewById(R.id.rv_now_playing);
        viewModel = new ViewModelProvider(NowPlayingActivity.this).get(MovieViewModel.class);
        viewModel.getNowPlaying();
        viewModel.getResultNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };
}