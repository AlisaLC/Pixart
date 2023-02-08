package ir.webkhoon.pixart.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM settings WHERE setting_id = :id")
    SettingEntity getById(long id);

    @Insert
    void addSetting(SettingEntity setting);

    @Update
    void updateSetting(SettingEntity setting);
}
