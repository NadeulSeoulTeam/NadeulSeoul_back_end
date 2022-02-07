package com.alzal.nadeulseoulbackend.mypage.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tb_follow")
public class FollowList {
    @EmbeddedId
    private Follow follow;
}
