package com.alzal.nadeulseoulbackend.domain.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_code_group")
public class CodeGroup {

    @Id
    private Long groupSeq;

    private String groupName;

    @OneToMany(mappedBy = "codeGroup")
    private final List<Code> codeList = new ArrayList<>();

    @Builder
    public CodeGroup(Long groupSeq, String groupName) {
        this.groupSeq = groupSeq;
        this.groupName = groupName;
    }
}
