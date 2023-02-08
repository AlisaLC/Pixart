package ir.webkhoon.pixart.model.room;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "profiles", indices = {@Index(value = "username")},
        foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "user_id", childColumns = "profile_id", onDelete = ForeignKey.CASCADE)})
public class ProfileEntity {
    @PrimaryKey
    public long profile_id;

    public String name;
    public String username;
    public String phone;

    public ProfileEntity(long profile_id, String name, String username, String phone) {
        this.profile_id = profile_id;
        this.name = name;
        this.username = username;
        this.phone = phone;
    }
}
