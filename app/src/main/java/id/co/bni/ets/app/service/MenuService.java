package id.co.bni.ets.app.service;

import id.co.bni.ets.app.model.MenuResponse;
import id.co.bni.ets.app.repo.RoleMenuRepository;
import id.co.bni.ets.lib.model.entity.Menu;
import id.co.bni.ets.lib.model.entity.RoleMenu;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MenuService {

    private static final int MAX_DEPTH = 1;

    private final RoleMenuRepository roleMenuRepo;

    public MenuService(RoleMenuRepository roleMenuRepo) {
        this.roleMenuRepo = roleMenuRepo;
    }

    /**
     * Get menu tree by role with maximum depth of 1. Depth must adjusted by view requirement.
     *
     * @param roleIdList list of role's id from role table database.
     *
     * @return list of menu with tree structure. Return empty list if role not found.
     */
    @Transactional(readOnly = true)
    public List<MenuResponse> getMenuListByRoleAndModule(List<Integer> roleIdList, int moduleId) {
        try (Stream<RoleMenu> roleMenuStream = roleMenuRepo.findParentMenuIsInRoleAndModuleIdEquals(roleIdList,
                                                                                                    moduleId)) {
            return roleMenuStream.filter(roleMenu -> roleMenu.getSecondEntity() != null)
                    .map(roleMenuItem -> getCompleteNavigationMenu(roleMenuItem, roleIdList, moduleId, 0))
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    private List<MenuResponse> getMenuChild(@NonNull List<Integer> roleIdList, int parentId, int moduleId,
                                            int currentDepth) {
        if (currentDepth > MAX_DEPTH) {
            return new ArrayList<>();
        }

        try (Stream<RoleMenu> roleMenuChildStream = roleMenuRepo.findChildMenuIsInRoleAndModuleIdEquals(roleIdList,
                                                                                                        parentId,
                                                                                                        moduleId)) {
            return roleMenuChildStream.filter(roleMenu -> roleMenu.getSecondEntity() != null)
                    .map(roleMenuItem -> getCompleteNavigationMenu(roleMenuItem, roleIdList, moduleId,
                                                                   (currentDepth + 1)))
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    private MenuResponse getCompleteNavigationMenu(RoleMenu roleMenu, @NonNull List<Integer> roleIdList, int moduleId,
                                                   int currentDepth) {
        Menu menuItem = roleMenu.getSecondEntity();
        MenuResponse menuResponse = new MenuResponse(menuItem);
        menuResponse.setChildren(getMenuChild(roleIdList, menuItem.getId(), moduleId, currentDepth));

        return menuResponse;
    }
}
