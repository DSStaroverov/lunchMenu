package ru.dsstaroverov.lunchMenu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {
    @Column(name = "address",  columnDefinition="unknown")
    @NotBlank
    private String address;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OrderBy("createDate DESC")
    private List<Menu> menuList;

    public Restaurant() {
    }

    public Restaurant(String name, String address) {
        this.address = address;
        this.name = name;
    }
}
