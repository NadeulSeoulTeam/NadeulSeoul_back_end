package com.alzal.nadeulseoulbackend.domain.stores.entity;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.Member;
import lombok.Builder;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

// member_seq, store_seq 복합키로 사용
@Builder
@Embeddable
public class MemberStoreId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq")
    private StoreInfo storeInfo;

    public MemberStoreId() {}

}
