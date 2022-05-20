package com.example.aplikasimanagemetaset.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasimanagemetaset.dao.DatabaseDao;
import com.example.aplikasimanagemetaset.database.DatabaseClient;
import com.example.aplikasimanagemetaset.model.ModelDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class HistoryViewModel extends AndroidViewModel {

    LiveData<List<ModelDatabase>> modelLaundry;
    DatabaseDao databaseDao;

    public HistoryViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        modelLaundry = databaseDao.getAllReport();
    }

    public LiveData<List<ModelDatabase>> getDataLaporan() {
        return modelLaundry;
    }

    public void updateData(
            final int uid,
            final String kategori,
            final String image,
            final String nama,
            final String lokasi,
            final String tanggal,
            final String isi_laporan,
            final String telepon
    ) {
        Completable.fromAction(() -> {
            ModelDatabase modelDatabase = new ModelDatabase();
            modelDatabase.uid = uid;
            modelDatabase.kategori = kategori;
//        modelDatabase.image = image;
            modelDatabase.nama = nama;
            modelDatabase.lokasi = lokasi;
            modelDatabase.tanggal = tanggal;
            modelDatabase.isi_laporan = isi_laporan;
            modelDatabase.telepon = telepon;
            databaseDao.updateSingleReport(modelDatabase);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteDataById(final int uid) {
        Completable.fromAction(() -> databaseDao.deleteSingleReport(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
