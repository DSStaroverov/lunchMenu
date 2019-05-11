package ru.dsstaroverov.lunchMenu.to;

public class VoteTo extends BaseTo {
    private int menuId;
    private int userId;
    private int count;

    public VoteTo() {
    }

    public VoteTo(Integer id, int userId, int menuId, int count) {
        super(id);
        this.menuId = menuId;
        this.userId = userId;
        this.count = count;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "menuId=" + menuId +
                ", userId=" + userId +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
