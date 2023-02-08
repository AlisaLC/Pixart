package ir.webkhoon.pixart.model.room;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings",
        foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "user_id", childColumns = "setting_id", onDelete = ForeignKey.CASCADE)})
public class SettingEntity {
    @PrimaryKey
    public long setting_id;

    public int difficulty;
    public int questionCount;
    public int category;

    public SettingEntity(long setting_id, int difficulty, int questionCount, int category) {
        this.setting_id = setting_id;
        this.difficulty = difficulty;
        this.questionCount = questionCount;
        this.category = category;
    }
}