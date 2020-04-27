package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_menu", schema = "dbo")
public class Menu extends AbstractActiveFlaggedEntity<Integer> {

    @NotBlank(message = "Name must not blank.")
    public String name;

    private String description;

    private String url;

    private String icon;

    @PositiveOrZero(message = "Wrong order number.")
    private Integer orderNo;

    private Character titleFlag;

    private Integer parentId;

    private Integer moduleId;

    @OneToMany(mappedBy = "secondEntity")
    @JsonIgnore
    private Collection<RoleMenu> roleMenus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String path) {
        this.url = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String cssClass) {
        this.icon = cssClass;
    }

    public Integer getOrderNo() {
        return Optional.ofNullable(orderNo)
                .orElse(Integer.MAX_VALUE);
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Collection<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Collection<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public Character getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(Character titleFlag) {
        this.titleFlag = titleFlag;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @PrePersist
    @PreUpdate
    private void prepareSaving() {
        if (titleFlag == null || Constant.YES_FLAG != Character.toUpperCase(titleFlag)) {
            titleFlag = Constant.NO_FLAG;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;

        return Objects.equals(name, menu.name) &&
                Objects.equals(description, menu.description) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(orderNo, menu.orderNo) &&
                Objects.equals(titleFlag, menu.titleFlag) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(moduleId, menu.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, url, icon, orderNo, titleFlag, parentId, moduleId,
                roleMenus);
    }
}
