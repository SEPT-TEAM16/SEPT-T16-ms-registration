package com.rmit.sept.msregistration.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {

    private String jwtToken;

    public JwtToken(){
    }

    public JwtToken(String jwtToken){
        this.jwtToken = jwtToken;
    }
}