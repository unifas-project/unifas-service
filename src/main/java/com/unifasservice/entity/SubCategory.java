package com.unifasservice.entity;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUB_CATEGORY")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_SHOWN")
    private boolean isShown;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private List<Product> productList ;

}
