package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@Table(name = "tb_follow")
public class FollowInfo {

    @EmbeddedId
    private Follow follow;
}
