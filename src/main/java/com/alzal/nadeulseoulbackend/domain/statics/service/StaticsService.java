package com.alzal.nadeulseoulbackend.domain.statics.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationSearchResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationTagRepository;
import com.alzal.nadeulseoulbackend.domain.statics.dto.NadeulerDto;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreBookmarkInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreInfoRepository;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StaticsService {

    @Autowired
    private CurationTagRepository curationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreInfoRepository storeInfoRepository;

    public List<CurationSearchResponseDto> getHotCurationList() {
        List<Curation> curationList = curationRepository.findTop10ByHiddenFalseOrderByViewsDesc();
        return curationList.stream().map(CurationSearchResponseDto::fromEntity).collect(Collectors.toList());
    }

    public List<NadeulerDto> getNadeulerList() {
        List<User> userList = userRepository.findTop10ByOrderByMyCurationCountDesc();
        return userList.stream().map(NadeulerDto::fromEntity).collect(Collectors.toList());
    }


    public List<StoreBookmarkInfoDto> getStoreBookmarkList() {
        List<StoreInfo> storeBookmarkList = storeInfoRepository.findTop10ByOrderByBookmarkCountDesc();
        return storeBookmarkList.stream().map(StoreBookmarkInfoDto::fromEntity).collect(Collectors.toList());
//        return null;
    }

}
