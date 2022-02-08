package com.alzal.nadeulseoulbackend.domain.stores.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_store_bookmark")
public class StoreBookmark {

    @EmbeddedId
    private MemberStoreId memberStoreId;
}
