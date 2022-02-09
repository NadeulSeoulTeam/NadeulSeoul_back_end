package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long memberSeq;
    private String nickName;
    private String email;
    private String name;
    private String emoji;
    private Long followeeCount = 0L;
    private Long followerCount = 0L;

    // MemberEntity에 추가
    public void addFollowee() {
        this.followeeCount++;
    }

    public void addFollower() {
        this.followerCount++;
    }

    public void deleteFollowee() {
        this.followeeCount--;
    }

    public void deleteFollower() {
        this.followerCount--;
    }

}