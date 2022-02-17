package com.alzal.nadeulseoulbackend.domain.curations.entity;

import com.alzal.nadeulseoulbackend.domain.curations.dto.StoreInCurationDto;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "STORE_IN_CURATION_SEQ_GENERATOR",
        sequenceName = "STORE_IN_CURATION_SEQ",
        initialValue = 1, // 초기값
        allocationSize = 1 // 미리 할당 받은 시퀀스 수
)
@Table(name = "tb_store_in_curation")
public class StoreInCuration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "store_in_curation_seq")
    private Long storeInCurationSeq;

    @Column(name = "store_order")
    private Integer storeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_seq")
    private Curation curation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq")
    private StoreInfo storeInfo;

    @Builder
    public StoreInCuration(Integer storeOrder, Curation curation, StoreInfo storeInfo) {
        this.storeOrder = storeOrder;
        this.curation = curation;
        this.storeInfo = storeInfo;
    }

}
