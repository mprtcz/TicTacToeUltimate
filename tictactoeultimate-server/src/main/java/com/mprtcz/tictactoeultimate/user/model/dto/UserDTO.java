package com.mprtcz.tictactoeultimate.user.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Getter
@Setter
public class UserDTO {

    private String ssoId;

    private String nickname;

    private String email;

    private String role;
}
