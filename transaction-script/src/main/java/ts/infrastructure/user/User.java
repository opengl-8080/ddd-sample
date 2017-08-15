package ts.infrastructure.user;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String loginId;
    private String name;
    private String password;
}
