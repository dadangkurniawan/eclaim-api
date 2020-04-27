package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminController;
import id.co.bni.ets.admin.service.UserService;
import id.co.bni.ets.lib.model.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractAdminController<User, Long, User> {

    public UserController(UserService userService) {
        super(userService);
    }

    @Override
    protected String getEntityName() {
        return "User";
    }
}
