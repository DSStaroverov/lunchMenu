package ru.dsstaroverov.lunchMenu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    @Column(name = "vote_date")
    private LocalDateTime voteDate;

    public Vote() {
    }

    public Vote(@NotNull User user, @NotNull Menu menu) {
        this.user = user;
        this.menu = menu;
    }
}
