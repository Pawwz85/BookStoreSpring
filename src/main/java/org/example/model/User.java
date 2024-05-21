package org.example;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String hashedPassword;
}
