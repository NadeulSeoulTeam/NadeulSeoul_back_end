package com.alzal.nadeulseoulbackend.domain.tag.service;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeGroup;
import com.alzal.nadeulseoulbackend.domain.tag.exception.TagNotFoundException;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeGroupRepositoroy;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeGroupRepositoroy codeGroupRepositoroy;

    public List<CodeDto> findAllByGroup(Long groupSeq) {
        CodeGroup codeGroup = codeGroupRepositoroy.findById(groupSeq)
                .orElseThrow(() -> new TagNotFoundException("코드 그룹이 "));
        List<Code> codeList = codeGroup.getCodeList();
        return codeList.stream().map(CodeDto::fromEntity).collect(Collectors.toList());
    }

}
