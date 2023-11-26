package itis.Modules;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class User {

    private Long id;
    private String nameOfUser;
    private String surnameOfUser;
    private Integer ageOfUser;

}
