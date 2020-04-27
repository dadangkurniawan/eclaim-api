package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminLookupController;
import id.co.bni.ets.admin.service.MenuService;
import id.co.bni.ets.lib.model.entity.Menu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractAdminLookupController<Menu, Integer, Menu> {

    public MenuController(MenuService menuService) {
        super(menuService);
    }

    @Override
    protected String getEntityName() {
        return "Menu";
    }
}
