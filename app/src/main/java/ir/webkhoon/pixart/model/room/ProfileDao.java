package ir.webkhoon.pixart.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM profiles WHERE profile_id = :id")
    ProfileEntity getById(long id);

    @Insert
    void addProfile(ProfileEntity profile);

    @Update
    void updateProfile(ProfileEntity profile);
}