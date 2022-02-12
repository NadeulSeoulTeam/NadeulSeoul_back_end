package com.alzal.nadeulseoulbackend.domain.stores.service;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.mypage.repository.UserRepository;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreBookmarkInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreBookmark;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.stores.exception.StoreBookmarkNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.exception.StoreInfoNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreBookmarkRepository;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StoreService {

    @Autowired
    StoreInfoRepository storeInfoRepository;

    @Autowired
    StoreBookmarkRepository storeBookmarkRepository;

    @Autowired
    UserRepository userRepository;

    // 찜한 장소 정보 리스트 가져오기
    public List<StoreBookmarkInfoDto> getStoreBookmarkInfoDto(Long userSeq) {
        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
        List<StoreInfo> storeInfoList = user.getStoreInfoList();

        List<StoreBookmarkInfoDto> storeBookmarkInfoDtoList = new ArrayList<>();
        for (StoreInfo storeInfo : storeInfoList) {
            storeBookmarkInfoDtoList.add(StoreBookmarkInfoDto.builder()
                            .storeSeq(storeInfo.getStoreSeq())
                            .storeName(storeInfo.getStoreName())
                            .categoryName(storeInfo.getCategoryName())
                            .build());
        }

        // pagination 구현

        return storeBookmarkInfoDtoList;
    }

    // 장소 상세 정보 가져오기
    public StoreInfoDto getStoreInfoDto(Long storeSeq) {
        StoreInfo storeInfo = storeInfoRepository.findByStoreSeq(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoDto.fromEntity(storeInfo);
        return storeInfoDto;
    }

    // 장소 찜하기
    @Transactional
    public void insertStoreBookmark(Long userSeq, Long storeSeq) {
        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        StoreInfo storeInfo = storeInfoRepository.findByStoreSeq(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        StoreBookmark storeBookmarkEntity = storeBookmarkRepository.save(
                StoreBookmark.builder()
                        .user(user)
                        .storeInfo(storeInfo)
                        .build()
                );
    }

    // 장소 찜하기 취소
    @Transactional
    public void deleteStoreBookmark(Long userSeq, Long storeSeq) {
        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        StoreInfo storeInfo = storeInfoRepository.findByStoreSeq(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        StoreBookmark storeBookmark = storeBookmarkRepository.findByUserAndStoreInfo(user, storeInfo)
                .orElseThrow(() -> new StoreBookmarkNotFoundException("사용자가 해당 장소를 찜한적이 없습니다."));

        storeBookmarkRepository.delete(storeBookmark);
    }

    // 장소가 이미 찜한 장소인지 확인하기
    public boolean getIsBookmark(Long userSeq, Long storeSeq) {
        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        StoreInfo storeInfo = storeInfoRepository.findByStoreSeq(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        // 찜한적이 없으면 예외 발생
        StoreBookmark storeBookmark = storeBookmarkRepository.findByUserAndStoreInfo(user, storeInfo)
                .orElseThrow(() -> new StoreBookmarkNotFoundException("사용자가 해당 장소를 찜한적이 없습니다."));

        // 찜한적이 있으면 true
        return true;

//        if(storeBookmark != null){ // 이미 찜한 장소
//            return false;
//        }
//        return true; // 찜하지 않은 장소
    }





}
