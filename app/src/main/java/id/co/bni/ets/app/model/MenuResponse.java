package id.co.bni.ets.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.model.entity.Menu;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused", "WeakerAccess"})
public class MenuResponse implements Comparable<MenuResponse> {

    private String name;

    private String url;

    private String icon;

    private boolean title;

    @JsonIgnore
    private int orderNo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuResponse> children;

    public MenuResponse(Menu menuEntity) {
        this.name = menuEntity.getName();
        this.url = menuEntity.getUrl();
        this.icon = menuEntity.getIcon();
        this.orderNo = menuEntity.getOrderNo();
        this.title = (Constant.YES_FLAG == Optional.ofNullable(menuEntity.getTitleFlag())
                .orElse(Constant.NO_FLAG));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isTitle() {
        return title;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public List<MenuResponse> getChildren() {
        return children;
    }

    public void setChildren(List<MenuResponse> children) {
        this.children = children;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int compareTo(MenuResponse o) {
        return getOrderNo() - o.getOrderNo();
    }
}
