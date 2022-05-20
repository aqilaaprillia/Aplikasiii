package com.example.aplikasimanagemetaset.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplikasimanagemetaset.model.ModelDatabase;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM tbl_laporan")
    LiveData<List<ModelDatabase>> getAllReport();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelDatabase modelDatabases);

    @Update
    void updateSingleReport(ModelDatabase history);

    @Query("DELETE FROM tbl_laporan")
    void deleteAllReport();

    @Query("DELETE FROM tbl_laporan WHERE uid= :uid")
    void deleteSingleReport(int uid);

}
