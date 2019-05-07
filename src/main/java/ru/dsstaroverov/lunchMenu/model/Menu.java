package ru.dsstaroverov.lunchMenu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    @OrderBy("name DESC")
    //@JsonIgnore
    private List<LunchItem> lunchItems;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "price", columnDefinition = "double default 0")
    private double price;

    public Menu() {
    }

    public Menu(Restaurant restaurant) {
        this(restaurant,0.0);
    }

    public Menu(Restaurant restaurant,double price) {
        this(null,LocalDate.now(),price,restaurant);
    }

    public Menu(Integer id, LocalDate date, double price, Restaurant restaurant){
        super(id);
        this.createDate = date;
        this.price = price;
        this.restaurant=restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<LunchItem> getLunchItems() {
        return lunchItems;
    }

    public void setLunchItems(List<LunchItem> lunchItems) {
        this.lunchItems = lunchItems;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant=" + restaurant +
                ", createDate=" + createDate +
                ", price=" + price + ", "+
                "\n lunchItems=" + lunchItems +
                '}';
    }
}
