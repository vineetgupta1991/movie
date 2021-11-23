package com.gofore.movie.model.entity;

import com.gofore.movie.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String firstName;
    @Column()
    private String lastName;
    @Column()
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personEntity")
    private List<MovieCharacterMapEntity> movieCharacterMapEntity;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personEntityList")
    private List<MovieEntity> movieEntityList;
}
