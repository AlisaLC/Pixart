package ir.webkhoon.pixart.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE user_id = :id")
    UserEntity getById(long id);

    @Query("SELECT * FROM users WHERE email = :email")
    UserEntity getByEmail(String email);

    @Insert
    void registerUser(UserEntity user);
}
