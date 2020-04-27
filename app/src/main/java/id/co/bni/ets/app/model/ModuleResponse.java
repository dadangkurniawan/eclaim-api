package id.co.bni.ets.app.model;

import id.co.bni.ets.lib.model.entity.Module;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
public class ModuleResponse implements Serializable {

    private Integer id;

    private String name;

    public ModuleResponse() {
        // Empty constructor.
    }

    public ModuleResponse(Module moduleEntity) {
        this.id = moduleEntity.getId();
        this.name = moduleEntity.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleResponse that = (ModuleResponse) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
