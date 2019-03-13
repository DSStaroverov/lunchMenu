package ru.dsstaroverov.lunchMenu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private List<LunchItem> lunchItems;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "vote_count")
    private int voteCount;

    public Menu() {
    }

    public Menu(@NotNull Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
