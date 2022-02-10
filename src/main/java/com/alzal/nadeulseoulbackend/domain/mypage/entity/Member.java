package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

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

    //팔로잉 리스트 (사용자가 팔로우한 사람)
    @OneToMany(mappedBy = "followee")
    private Set<FollowInfo> followeeList;

    //팔로워 리스트 (사용자를 팔로우한 사람)
    @OneToMany(mappedBy = "follower")
    private Set<FollowInfo> followerList;

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