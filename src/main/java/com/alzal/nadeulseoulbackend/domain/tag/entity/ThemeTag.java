package com.alzal.nadeulseoulbackend.domain.tag.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_theme_tag")
public class ThemeTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tagName;

    @Builder
    public ThemeTag(String tagName) {
        this.tagName = tagName;
    }
}
