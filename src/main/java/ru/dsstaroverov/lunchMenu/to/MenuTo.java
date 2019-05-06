package ru.dsstaroverov.lunchMenu.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MenuTo extends BaseTo{
    private Integer restaurant;

    private List<LunchItem> lunchItems;

    private LocalDate createDate;

    private double price;

    private Integer voteCount;

    private double totalCalories;

    public MenuTo() {
    }


    public MenuTo(Integer id,int restaurant, List<LunchItem> lunchItems, LocalDate createDate, double price) {
        super(id);
        this.restaurant = restaurant;
        this.lunchItems = lunchItems;
        this.createDate = createDate;
        this.price = price;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuTo menuTo = (MenuTo) o;

        if (Double.compare(menuTo.price, price) != 0) return false;
        if (Double.compare(menuTo.totalCalories, totalCalories) != 0) return false;
        if (!restaurant.equals(menuTo.restaurant)) return false;
        return  !createDate.equals(menuTo.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(restaurant,createDate,price) ;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "restaurant=" + restaurant +
                ", lunchItems=" + lunchItems +
                ", createDate=" + createDate +
                ", price=" + price +
                ", voteCount=" + voteCount +
                ", totalCalories=" + totalCalories +
                '}';
    }
}
