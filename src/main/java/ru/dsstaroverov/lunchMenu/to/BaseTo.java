package ru.dsstaroverov.lunchMenu.to;


import ru.dsstaroverov.lunchMenu.model.HasId;

public abstract class BaseTo implements HasId {

    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
