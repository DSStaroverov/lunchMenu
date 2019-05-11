package ru.dsstaroverov.lunchMenu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "lunch_items")
public class LunchItem extends AbstractNamedEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    @JsonIgnore
    private Menu menu;

    @Column(name = "calories")
    private double calories;

    public LunchItem() {
    }

    public LunchItem(String name,double calories){
        this(null,name,null,calories);
    }

    public LunchItem(Integer id, String name, Menu menu, double calories){
        super(id,name);
        this.menu=menu;
        this.calories=calories;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "LunchItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
               // ", menu='" + menu + '\'' +
                ", calories=" + calories +
                '}';
    }
}
