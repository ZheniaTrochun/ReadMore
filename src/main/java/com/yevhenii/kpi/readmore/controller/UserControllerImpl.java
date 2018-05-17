package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.response.UsernameResponse;
import com.yevhenii.kpi.readmore.service.UserService;
import com.yevhenii.kpi.readmore.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public UserControllerImpl(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }
    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Endpoint for get user authentication"
    )
    @RequestMapping(value = "/is-authenticated", method = GET)
    public ResponseEntity<Void> isAuthenticated() {

        return new ResponseEntity<>(
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ?
                        HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Endpoint for get username from session"
    )
    @RequestMapping(value = "/username", method = GET)
    public ResponseEntity<UsernameResponse> getUsername() {

        return ResponseEntity.ok(
                new UsernameResponse(SecurityUtils.getUsername())
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Endpoint for get username from session"
    )
    @RequestMapping(value = "/logout", method = GET)
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
