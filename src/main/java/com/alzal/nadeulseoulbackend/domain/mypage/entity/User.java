package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Comment;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreBookmark;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long userSeq;
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

    //문의 사항 리스트
    @OneToMany(mappedBy = "user")
    private List<Inquiry> inquiryList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    private List<Curation> curationList;
    //찜한 장소 리스트
    @OneToMany(mappedBy = "user")
    @BatchSize(size = 8)
    private List<StoreBookmark> storeBookmarkList;

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