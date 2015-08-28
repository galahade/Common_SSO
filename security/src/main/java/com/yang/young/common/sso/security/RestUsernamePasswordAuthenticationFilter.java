package com.yang.young.common.sso.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.yang.young.common.sso.rest.model.UserModel;

public class RestUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	

    protected String obtainPassword(HttpServletRequest request) {
    	
        return extractUser(request).getUsername();
    }


    protected String obtainUsername(HttpServletRequest request) {
        return extractUser(request).getPassword();
    }
    
    private UserModel extractUser(HttpServletRequest request) {
    	Gson gson = new GsonBuilder().create();
    	UserModel user;
		try {
			user = gson.fromJson(request.getReader(), UserModel.class);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
    }

}
