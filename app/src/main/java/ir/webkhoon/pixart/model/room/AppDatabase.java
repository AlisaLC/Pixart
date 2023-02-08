package ir.webkhoon.pixart.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, ProfileEntity.class, SettingEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "quiz-app").
                    allowMainThreadQueries().
                    build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract ProfileDao profileDao();

    public abstract SettingDao settingDao();

}
