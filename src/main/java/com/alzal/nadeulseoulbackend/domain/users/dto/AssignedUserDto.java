package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AssignedUserDto {
    Long userSeq;
    String nickname;
    String role;

    @Builder
    public AssignedUserDto(Long userSeq, String nickname,String role) {
        this.nickname = nickname;
        this.userSeq = userSeq;
        this.role = role;
    }
}
