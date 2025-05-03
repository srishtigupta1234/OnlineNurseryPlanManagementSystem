package com.masai.service;

import com.masai.Errors.*;
import com.masai.model.LoginDTO;

public interface LoginService {
    public String LogIn (LoginDTO dto) throws LoginException;
    public String LogOut (String key, String type) throws LoginException;
}
