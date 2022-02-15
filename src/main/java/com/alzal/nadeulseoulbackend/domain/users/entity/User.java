package com.alzal.nadeulseoulbackend.domain.users.entity;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Comment;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreBookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name="tb_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userSeq")
    private Long userSeq;

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

    @ColumnDefault("0")
    private int followeeCount;

    @ColumnDefault("0")
    private int followerCount;

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
