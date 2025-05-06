package com.mymulya.globaltables.controller;


import com.mymulya.globaltables.dto.LoginRequest;
import com.mymulya.globaltables.dto.LoginResponse;
import com.mymulya.globaltables.model.GlobalUser;
import com.mymulya.globaltables.service.GlobalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://35.188.150.92", "http://192.168.0.140:3000", "http://192.168.0.139:3000","https://mymulya.com","http://localhost:3000","http://192.168.0.135:8080","http://192.168.0.135",
        "http://182.18.177.16"})
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
