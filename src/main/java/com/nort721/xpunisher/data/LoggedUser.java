package com.nort721.xpunisher.data;

import com.nort721.xpunisher.data.enums.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoggedUser {
    private String username;
    private AccessLevel accessLevel;
}
