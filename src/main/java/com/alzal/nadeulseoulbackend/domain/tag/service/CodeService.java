package com.alzal.nadeulseoulbackend.domain.tag.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeGroup;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeGroupRepositoroy;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO:
//  Optional 관련 null 처리 로직 필요
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeGroupRepositoroy codeGroupRepositoroy;

    public List<CodeDto> findAllByGroup(Long groupSeq) {
        Optional<CodeGroup> codeGroup = codeGroupRepositoroy.findById(groupSeq);
        List<Code> codeList = codeGroup.get().getCodeList();
        return codeList.stream().map(CodeDto::fromEntity).collect(Collectors.toList());
    }

}
