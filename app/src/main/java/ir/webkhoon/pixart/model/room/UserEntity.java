package ir.webkhoon.pixart.model.room;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "email")})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public long user_id;
    public String email;
    public String password;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
