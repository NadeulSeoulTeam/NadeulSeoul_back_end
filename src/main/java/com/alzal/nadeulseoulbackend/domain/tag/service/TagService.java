package com.alzal.nadeulseoulbackend.domain.tag.service;

import java.util.List;
import java.util.stream.Collectors;

import com.alzal.nadeulseoulbackend.domain.tag.dto.ThemeTagDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.LocalTagDto;
import com.alzal.nadeulseoulbackend.domain.tag.repository.LocalTagRepository;
import com.alzal.nadeulseoulbackend.domain.tag.repository.ThemeTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    @Autowired
    private LocalTagRepository localTagRepository;

    @Autowired
    private ThemeTagRepository themeTagRepository;

    public List<LocalTagDto> findAllLocalTag() {
        return localTagRepository.findAll().stream()
                .map(LocalTagDto::fromEntity).collect(Collectors.toList());
    }

    public List<ThemeTagDto> findAllThemeTag() {
        return themeTagRepository.findAll().stream()
                .map(ThemeTagDto::fromEntity).collect(Collectors.toList());
    }
}
