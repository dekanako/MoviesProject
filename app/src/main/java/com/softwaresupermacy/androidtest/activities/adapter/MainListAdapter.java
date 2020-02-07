package com.softwaresupermacy.androidtest.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.databinding.MainRecyclerViewListItemBinding;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {
    private Context mContext;
    private List<PackagedMovie> mPackagedMovieList;

    public MainListAdapter(Context context, List<PackagedMovie> packagedMovieList) {
        mContext = context;
        mPackagedMovieList = packagedMovieList;
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        MainRecyclerViewListItemBinding mainBinding = DataBindingUtil.inflate(inflater,
                R.layout.main_recycler_view_list_item, parent, false);

        return new MainListViewHolder(mainBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder holder, int position) {
        holder.mBinding.setPack(mPackagedMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPackagedMovieList.size();
    }

    class MainListViewHolder extends RecyclerView.ViewHolder{
        MainRecyclerViewListItemBinding mBinding;
        public MainListViewHolder(@NonNull MainRecyclerViewListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
