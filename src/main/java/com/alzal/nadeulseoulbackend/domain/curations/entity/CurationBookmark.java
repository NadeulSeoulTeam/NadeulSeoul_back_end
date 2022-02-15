package com.alzal.nadeulseoulbackend.domain.curations.entity;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Table(name = "tb_curation_bookmark")
public class CurationBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curationBookmarkSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_seq")
    private Curation curation;

}
