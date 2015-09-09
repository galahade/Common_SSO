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
	
	private Gson gson = new GsonBuilder().create();
	
	private static ThreadLocal<UserModel> userModel = new ThreadLocal<UserModel>();
	

    protected String obtainPassword(HttpServletRequest request) {
    	
        return extractUser(request).getPassword();
    }


    protected String obtainUsername(HttpServletRequest request) {
        return extractUser(request).getUsername();
    }
    
    private UserModel extractUser(HttpServletRequest request) {
    	UserModel user = userModel.get();
    	if(user == null) {
    	
			try {
				user = gson.fromJson(request.getReader(), UserModel.class);
			} catch (JsonSyntaxException | JsonIOException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			userModel.set(user);
    	}
		return user;
    }

}
