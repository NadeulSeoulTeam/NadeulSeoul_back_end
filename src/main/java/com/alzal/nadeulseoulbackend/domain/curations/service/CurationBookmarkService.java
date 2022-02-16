package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationBookmarkDto;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.entity.CurationBookmark;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationBookmarkExistenceException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationBookmarkNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.MyCurationBookmarkException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationBookmarkRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationTagRepository;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurationBookmarkService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CurationTagRepository curationRepository;

    @Autowired
    CurationBookmarkRepository curationBookmarkRepository;

    // 큐레이션 스크랩하기
    @Transactional
    public void insertCurationBookmark(Long userSeq, Long curationSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("해당 큐레이션이"));

        // 본인이 만든 큐레이션 찜하기 금지 예외처리
        if(curation.getUser().getUserSeq() == userSeq) {
            throw new MyCurationBookmarkException("사용자가 만든 큐레이션은 스크랩 할 수 없습니다.");
        }

        // 스크랩 여부 확인 api 미작동시 예외처리
        Optional<CurationBookmark> curationBookmark = curationBookmarkRepository.findByUserAndCuration(user, curation);
        curationBookmark.ifPresent(c -> {throw new CurationBookmarkExistenceException("이미 사용자가 해당 큐레이션을 스크랩하였습니다.");});

        curationBookmarkRepository.save(CurationBookmark.builder()
                        .user(user)
                        .curation(curation)
                        .build());
    }

    // 큐레이션 스크랩 취소하기
    @Transactional
    public void deleteCurationBookmark(Long userSeq, Long curationSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("해당 큐레이션이"));

        CurationBookmark curationBookmark = curationBookmarkRepository.findByUserAndCuration(user, curation)
                .orElseThrow(() -> new CurationBookmarkNotFoundException("사용자가 해당 큐레이션을 스크랩한적이 없습니다."));

        curationBookmarkRepository.delete(curationBookmark);
    }

    // 큐레이션 스크랩 여부
    public boolean isCurationBookmark(Long userSeq, Long curationSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("해당 큐레이션이"));

        Optional<CurationBookmark> curationBookmark = curationBookmarkRepository.findByUserAndCuration(user, curation);
        return curationBookmark.isPresent();
    }

    // 스크랩한 큐레이션 나열
    public Page<CurationBookmarkDto> getCurationBookmarkList(Long userSeq, int page, int size){
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        Pageable pageRequest = PageRequest.of(page, size);
        Page<CurationBookmark> pageCuration = curationBookmarkRepository.findByUser(pageRequest, user);

        Page<CurationBookmarkDto> pageCurationResponseDto
                = pageCuration.map(CurationBookmark::getCuration).map(CurationBookmarkDto::fromEntity);

        return pageCurationResponseDto;
    }

}
