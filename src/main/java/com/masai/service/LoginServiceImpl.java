package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Errors.LoginException;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customers;
import com.masai.model.LoginDTO;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerDao cDao;

    @Autowired
    private AdminDao aDao;

    @Autowired
    private SessionDao sDao;

    @Override
    public String LogIn(LoginDTO dto) throws LoginException {
        if (dto.getType().equalsIgnoreCase("Customer")) {
            Customers existingCustomer = cDao.findByUsername(dto.getUsername());

            if (existingCustomer == null) {
                throw new LoginException("Please enter a valid username.");
            }

            Optional<CurrentUserSession> cs = sDao.findById(existingCustomer.getCustomer_id());

            if (cs.isPresent()) {
                throw new LoginException("User already logged in with this username.");
            }

            if (existingCustomer.getCustomer_password().equals(dto.getPassword())) {
                String key = RandomString.make(6);
                CurrentUserSession session = new CurrentUserSession(existingCustomer.getCustomer_id(), "Customer", key, LocalDateTime.now());
                sDao.save(session);
                return "Login successful! Your session key: " + key;
            } else {
                throw new LoginException("Invalid password.");
            }

        } else if (dto.getType().equalsIgnoreCase("Admin")) {
            Admin existingAdmin = aDao.findByUsername(dto.getUsername());

            if (existingAdmin == null) {
                throw new LoginException("Please enter a valid username.");
            }

            Optional<CurrentUserSession> cs = sDao.findById(existingAdmin.getAdmin_Id());

            if (cs.isPresent()) {
                throw new LoginException("Admin already logged in with this username.");
            }

            if (existingAdmin.getPassword().equals(dto.getPassword())) {
                String key = RandomString.make(6);
                CurrentUserSession session = new CurrentUserSession(existingAdmin.getAdmin_Id(), "Admin", key, LocalDateTime.now());
                sDao.save(session);
                return "Login successful! Your session key: " + key;
            } else {
                throw new LoginException("Invalid password.");
            }

        } else {
            throw new LoginException("Invalid user type.");
        }
    }

    @Override
    public String LogOut(String key, String type) throws LoginException {
        CurrentUserSession session = sDao.findByUuid(key);

        if (session == null || !session.getType().equalsIgnoreCase(type)) {
            throw new LoginException("No active session found for this key and type.");
        }

        sDao.delete(session);
        return "Logged Out Successfully!";
    }
}
