package com.softwaresupermacy.androidtest.activities.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.databinding.MainRecyclerViewListItemBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {
    private static final int GENRE_TYPE = -1;
    private Context mContext;
    private List<PackagedMovie> mPackagedMovieList;
    private List<Genre> mGenres;

    public MainListAdapter(Context context) {
        mContext = context;
        mPackagedMovieList = new ArrayList<>();
        this.mGenres = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 1){
            return GENRE_TYPE;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        MainRecyclerViewListItemBinding mainBinding = DataBindingUtil.inflate(inflater,
                R.layout.main_recycler_view_list_item, parent, false);

        return new MainListViewHolder(mainBinding);
    }
    //TODO add pendingExecute tayaraa muhym
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder holder, int position) {

        holder.mBinding.innerList.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));

        if (getItemViewType(position) == GENRE_TYPE){
            holder.mBinding.innerList.setAdapter(new GenreListAdapter(mGenres));
            holder.mBinding.textView2.setText("");
            holder.mBinding.packageTitle.setText("Genres");
            return;
        }
        if (position > 1){
            position = position -1;
        }
        holder.mBinding.packageTitle.setText(mPackagedMovieList.get(position).getPackage());
        holder.mBinding.innerList.setAdapter(new InnerListAdapter(mPackagedMovieList.get(position).getMovies(),mContext));

        holder.mBinding.textView2.setOnClickListener( r->{
            startDetailActivity();
        });
    }

    private void startDetailActivity(){

    }

    @Override
    public int getItemCount() {
        if (mPackagedMovieList.size() == 0){
            return mPackagedMovieList.size();
        }
        return mPackagedMovieList.size() + 1;
    }

    public void setMovies(List<PackagedMovie> movieList, List<Genre> genres) {
        mPackagedMovieList.addAll(movieList);
        mGenres.addAll(genres);

        notifyDataSetChanged();
    }

    public void clear() {
        mPackagedMovieList.clear();
        mGenres.clear();
        notifyDataSetChanged();
    }

    class MainListViewHolder extends RecyclerView.ViewHolder{
        MainRecyclerViewListItemBinding mBinding;
        public MainListViewHolder(@NonNull MainRecyclerViewListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
