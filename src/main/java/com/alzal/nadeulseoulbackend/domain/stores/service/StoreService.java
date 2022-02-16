package com.alzal.nadeulseoulbackend.domain.stores.service;

import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreBookmarkInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.dto.BookmarkDto;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreBookmark;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.stores.exception.StoreBookmarkNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.exception.StoreInfoNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreBookmarkRepository;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreInfoRepository;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public Page<StoreBookmarkInfoDto> getStoreBookmarkInfoDto(Long userSeq, int page, int size) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        // pagination 구현
        Pageable pageRequest = PageRequest.of(page, size);
        Page<StoreInfo> pageStoreInfo = storeInfoRepository.findAll(pageRequest);
        Page<StoreBookmarkInfoDto> pageStoreInfoDto
                = pageStoreInfo.map(StoreInfo -> StoreBookmarkInfoDto.builder()
                                                                    .storeSeq(StoreInfo.getStoreSeq())
                                                                    .storeName(StoreInfo.getStoreName())
                                                                    .addressName(StoreInfo.getAddressName())
                                                                    .categoryName(StoreInfo.getCategoryName())
                                                                    .build());

        return pageStoreInfoDto;
    }

    // 장소 상세 정보 가져오기
    public StoreInfoDto getStoreInfoDto(Long storeSeq) {
        StoreInfo storeInfo = storeInfoRepository.findById(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        return new StoreInfoDto().fromEntity(storeInfo);
    }

    // 장소 찜하기
    @Transactional
    public void insertStoreBookmark(Long userSeq, Long storeSeq, StoreInfoDto storeInfoDto) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        // 없을 경우 새로 만들어서 데이터를 넣어줌
        // 있을 경우 원래 있던 데이터를 업데이트
        StoreInfo storeInfo = storeInfoRepository.findById(storeSeq)
                .orElse(StoreInfo.builder()
                        .storeSeq(storeSeq)
                        .storeName(storeInfoDto.getStoreName())
                        .categoryName(storeInfoDto.getCategoryName())
                        .addressName(storeInfoDto.getAddressName())
                        .placeUrl(storeInfoDto.getPlaceUrl())
                        .phone(storeInfoDto.getPhone())
                        .x(storeInfoDto.getX())
                        .y(storeInfoDto.getY())
                        .bookmarkCount(0L)
                        .build());

        storeInfo.updateBookmarkCount();
        StoreInfo storeInfoEntity = storeInfoRepository.save(storeInfo);

        // 유저가 상가를 찜한 정보 테이블에 insert
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
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        StoreInfo storeInfo = storeInfoRepository.findById(storeSeq)
                .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

        StoreBookmark storeBookmark = storeBookmarkRepository.findByUserAndStoreInfo(user, storeInfo)
                .orElseThrow(() -> new StoreBookmarkNotFoundException("사용자가 해당 장소를 찜한적이 없습니다."));

        // 1이상이면 bookmarkCount -1
        if(storeInfo.getBookmarkCount() > 0) storeInfo.deleteBookmarkCount();

        // 상가 찜하기 테이블에서 삭제
        storeBookmarkRepository.delete(storeBookmark);
    }

    // 장소가 이미 찜한 장소인지 확인하기
    public BookmarkDto getIsBookmark(Long userSeq, Long storeSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        // 해당 장소가 없을 때 -> 찜하기 0, false
        Optional<StoreInfo> storeInfo = storeInfoRepository.findById(storeSeq);
        if(storeInfo.isEmpty()){
            return BookmarkDto.builder()
                    .BookmarkCount(0L)
                    .IsBookmark(false)
                    .build();
        }

        // 찜한적이 없을 때 -> 상가정보의 bookmarkCount, false
        Optional<StoreBookmark> storeBookmark = storeBookmarkRepository.findByUserAndStoreInfo(user, storeInfo.get());
        if(storeBookmark.isEmpty()){
            return BookmarkDto.builder()
                    .BookmarkCount(storeInfo.get().getBookmarkCount())
                    .IsBookmark(false)
                    .build();
        }

        // 찜한적이 있으면 true
        return BookmarkDto.builder()
                .BookmarkCount(storeInfo.get().getBookmarkCount())
                .IsBookmark(true)
                .build();
    }

    // 찜한 장소 목록에서 선택한 순서대로 장소 정보 가져오기
    public List<StoreInfoDto> getStoreInfoListInOrder(Long userSeq, List<Long> storeSeqList) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));

        List<StoreInfoDto> storeInfoDtoList = new ArrayList<>();
        for (Long storeSeq : storeSeqList) {
            StoreInfo storeInfo = storeInfoRepository.findById(storeSeq)
                    .orElseThrow(() -> new StoreInfoNotFoundException("해당 장소가 존재하지 않습니다."));

            StoreBookmark storeBookmark = storeBookmarkRepository.findByUserAndStoreInfo(user, storeInfo)
                    .orElseThrow(() -> new StoreBookmarkNotFoundException("해당 장소를 찜한적이 없습니다."));

            storeInfoDtoList.add(StoreInfoDto.fromEntity(storeInfo));
        }
        return storeInfoDtoList;
    }

}
