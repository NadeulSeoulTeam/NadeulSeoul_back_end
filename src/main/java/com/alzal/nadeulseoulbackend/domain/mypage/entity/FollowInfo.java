package com.alzal.nadeulseoulbackend.domain.mypage.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@Data
@Table(name = "tb_follow")
public class FollowInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_seq")
    private Long followSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_seq")
    private User followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_seq")
    private User follower;
}
