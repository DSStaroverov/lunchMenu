package ru.dsstaroverov.lunchMenu.to;



import java.time.LocalDate;
import java.util.Objects;

public class MenuTo extends BaseTo{
    private Integer restaurant;

    private LocalDate createDate;

    private double price;

    private double totalCalories;

    public MenuTo() {
    }


    public MenuTo(Integer id,int restaurant, LocalDate createDate, double price) {
        this(id,restaurant,createDate,price,0);
    }

    public MenuTo(Integer id, Integer restaurant, LocalDate createDate, double price, double totalCalories) {
        super(id);
        this.restaurant = restaurant;
        this.createDate = createDate;
        this.price = price;
        this.totalCalories = totalCalories;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
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
                ", createDate=" + createDate +
                ", price=" + price +
                ", totalCalories=" + totalCalories +
                '}';
    }
}
