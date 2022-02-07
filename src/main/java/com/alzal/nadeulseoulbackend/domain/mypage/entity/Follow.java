package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class Follow implements Serializable {
    // 팔로우를 요청한 유저
    @Column(name = "followee_seq")
    private Long followeeSeq;

    // 팔로우를 요청받은 유저
    @Column(name = "follower_seq")
    private Long followerSeq;

    public Follow() {}

    public Follow(Long followeeSeq, Long followerSeq) {
        this.followeeSeq = followeeSeq;
        this.followerSeq = followerSeq;
    }
}
