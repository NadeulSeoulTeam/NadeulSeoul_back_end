package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
public class Follow implements Serializable {
    @Id
    private Long Id;

    // 팔로우를 요청한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member followee;

    // 팔로우를 요청받은 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member follower;

    public Follow() {}

//    public Follow(Long followeeSeq, Long followerSeq) {
//        this.followeeSeq = followeeSeq;
//        this.followerSeq = followerSeq;
//    }
}
