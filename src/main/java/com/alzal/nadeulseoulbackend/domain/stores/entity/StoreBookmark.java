package com.alzal.nadeulseoulbackend.domain.stores.entity;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_store_bookmark")
public class StoreBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeBookmarkSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq")
    private StoreInfo storeInfo;

}
