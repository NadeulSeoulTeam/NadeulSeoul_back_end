package com.alzal.nadeulseoulbackend.mypage.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Follow implements Serializable {
    @Column(name = "followee_seq")
    private Long followeeSeq;

    @Column(name = "follower_seq")
    private Long followerSeq;

    public Follow() {}
}
