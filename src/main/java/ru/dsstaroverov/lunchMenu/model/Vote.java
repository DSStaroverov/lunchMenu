package ru.dsstaroverov.lunchMenu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @Column(name = "menu_id")
    @NotNull
    private int menuId;

    @Column(name = "vote_date")
    private LocalDate voteDate = LocalDate.now();

    public Vote() {
    }

    public Vote(Integer id,@NotNull int userId, @NotNull int menuId) {
        super(id);
        this.userId = userId;
        this.menuId = menuId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", menuId=" + menuId +
                ", voteDate=" + voteDate +
                ", id=" + id +
                '}';
    }
}
