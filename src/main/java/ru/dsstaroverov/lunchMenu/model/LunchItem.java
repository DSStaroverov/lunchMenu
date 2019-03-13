package ru.dsstaroverov.lunchMenu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lunch_items")
public class LunchItem extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    @Column(name = "price")
    private double price;

    public LunchItem(@NotNull Menu menu, double price) {
        this.menu = menu;
        this.price = price;
    }


    public LunchItem() {
    }
}
