package com.softwaresupermacy.androidtest.repository.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import timber.log.Timber;

public class DBUpdaterWork extends Worker {
    private Application mApplication;
    public DBUpdaterWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mApplication = (Application) context;
    }

    @NonNull
    @Override
    public Result doWork() {
         MoviesRepository.getInstance(mApplication)
                .forceRefresh(getInputData().getStringArray(WorkUtil.DATA_INPUT_ID));
        WorkUtil.makeStatusNotification(mApplication);
        return Result.success();
    }
}
