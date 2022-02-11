package com.alzal.nadeulseoulbackend.domain.users.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="tb_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userSeq")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    @ColumnDefault("0")
    private int curationCount;

    @Column
    private String emoji;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String nickname,String name, String email, Role role) {
        this.nickname = name;
        this.name = name;
        this.email = email;
        this.role = role;
    }


    public User update(String nickname,String emoji) {
        this.nickname = nickname;
        this.emoji = emoji;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
