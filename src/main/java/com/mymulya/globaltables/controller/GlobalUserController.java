package com.mymulya.globaltables.controller;


import com.mymulya.globaltables.dto.LoginRequest;
import com.mymulya.globaltables.dto.LoginResponse;
import com.mymulya.globaltables.model.GlobalUser;
import com.mymulya.globaltables.service.GlobalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/global")
public class GlobalUserController {

    @Autowired
    private GlobalUserService globalUserService;

    /**
     * Insert a new GlobalUser into the database
     *
     * @param globalUser The GlobalUser object containing the data to be saved.
     * @return The saved GlobalUser object.
     */
    @PostMapping("/add")
    public GlobalUser addGlobalUser(@RequestBody GlobalUser globalUser) {
        return globalUserService.save(globalUser);
    }
     @PostMapping("/hostlogin")
    public LoginResponse hostLogin(@RequestBody LoginRequest request) {
        try {
            return globalUserService.hostLogin(request.getEmail(), request.getPassword());
        } catch (Exception e) {
            return new LoginResponse(false, "Login failed: " + e.getMessage(), null, e.getMessage());
        }
    }

}
