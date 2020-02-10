package com.softwaresupermacy.androidtest.activities.adapter;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.databinding.GenreListItemBinding;

import java.util.List;



class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.GenreListViewHolder> {
    private List<Genre> mGenres;

    public GenreListAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @NonNull
    @Override
    public GenreListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GenreListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.genre_list_item, parent, false);

        return new GenreListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreListViewHolder holder, int position) {

        holder.mBinding.genreButton.setText(mGenres.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    class GenreListViewHolder extends RecyclerView.ViewHolder{
        GenreListItemBinding mBinding;
        public GenreListViewHolder(@NonNull GenreListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

        }
    }
}
