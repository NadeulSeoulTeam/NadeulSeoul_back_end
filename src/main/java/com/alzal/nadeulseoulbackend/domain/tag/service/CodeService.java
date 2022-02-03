package com.alzal.nadeulseoulbackend.domain.tag.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeGroup;
import com.alzal.nadeulseoulbackend.domain.tag.exception.TagCustomException;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeGroupRepositoroy;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeRepository;
import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new TagCustomException(ErrorStatusEnum.CODE_EXCEPTION));
        List<Code> codeList = codeGroup.getCodeList();
        return codeList.stream().map(CodeDto::fromEntity).collect(Collectors.toList());
    }

}
