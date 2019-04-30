package college.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserPo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    private String name;

    private String password;

}
