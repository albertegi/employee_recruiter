package com.alvirg.employee_recruiter.role;

import com.alvirg.employee_recruiter.common.BaseEntity;
import com.alvirg.employee_recruiter.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ROLES")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;
    
}
