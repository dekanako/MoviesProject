package com.softwaresupermacy.androidtest.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.StringCheck;
import com.softwaresupermacy.androidtest.api.NetworkingUtil;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.databinding.InnerListItemBinding;

import java.util.List;

import timber.log.Timber;

public class InnerListAdapter extends RecyclerView.Adapter<InnerListAdapter.InnerListViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;
    public InnerListAdapter(List<Movie> movies, Context context) {
        mMovies = movies;
        this.mContext = context;
    }

    @NonNull
    @Override
    public InnerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        InnerListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.inner_list_item, parent, false);

        return new InnerListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerListViewHolder holder, int position) {
        Timber.d(mMovies.get(position).getFilmTitle());
        holder.mBinding.getRoot().setOnClickListener(v ->
                startDetailActivity());
        holder.mBinding.movieTitleId.setText(StringCheck.stringFixer(mMovies.get(position).getFilmTitle()));
        //TODO fic the genre
//        holder.mBinding.genres.setText(mMovies.get(position).get);
        holder.mBinding.genreseView.setText(
                StringCheck.genreFixer(mMovies.get(position).getGenresString().getGenreString())
        );
        Glide.with(mContext).load(NetworkingUtil.buildPhotoURL(mMovies.get(position).getImageLink(),
                NetworkingUtil.POSTER_IMAGE_W500)).apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.mBinding.posterViewId);

    }

    private void startDetailActivity() {

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class InnerListViewHolder extends RecyclerView.ViewHolder{
        private InnerListItemBinding mBinding;
        public InnerListViewHolder(@NonNull InnerListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
        }
    }
}
