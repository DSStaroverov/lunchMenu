package ru.dsstaroverov.lunchMenu.to;

public class LunchItemTo extends BaseTo{
    private String name;
    private double calories;
    private int menuId;

    public LunchItemTo() {
    }

    public LunchItemTo(Integer id, String name, double calories, int menuId) {
        super(id);
        this.name = name;
        this.calories = calories;
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "LunchItemTo{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", menuId=" + menuId +
                ", id=" + id +
                '}';
    }
}
