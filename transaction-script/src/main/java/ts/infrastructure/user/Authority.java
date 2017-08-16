package ts.infrastructure.user;

import lombok.Data;

@Data
public class Authority {
    private Long id;
    private String authority;
    private String description;
}
