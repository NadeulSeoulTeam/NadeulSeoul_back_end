package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.*;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Comment;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CommentNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CommentRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationRepository;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


// TODO :
//      User 관련 변수 전부 1L 로 임의 값 설정

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CurationRepository curationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CommentResponseDto> getCommentList(Long curationSeq) {
//        List<Comment> commentList = curationRepository.findById(curationSeq)
//                .orElseThrow(()-> new CurationNotFoundException("큐레이션이 "))
//                .getCommentList();
//        return commentList.stream().map(CommentResponseDto::fromEntity).collect(Collectors.toList());

        Set<Comment> commentSet = curationRepository.findById(curationSeq)
                .orElseThrow(()-> new CurationNotFoundException("큐레이션이 "))
                .getCommentList();
        return commentSet.stream().map(CommentResponseDto::fromEntity).collect(Collectors.toList());
    }

    public Page<CommentResponseDto> getCommentListByPage(Long curationSeq, Pageable pageable){
        Page<Comment> commentPage = commentRepository.findByCurationSeq(curationSeq, pageable);
        return commentPage.map(CommentResponseDto::fromEntity);
    }

    public void insertComment(CommentRequestDto commentRequestDto) {
        Curation curation = curationRepository.findById(commentRequestDto.getCurationSeq())
                .orElseThrow(()-> new CurationNotFoundException("큐레이션이 "));
        User user = userRepository.findById(1L) // 현재 임의 값
                .orElseThrow(()-> new UserNotFoundException("사용자가 "));

        Comment comment = Comment.builder()
                .curation(curation)
                .user(user)
                .content(commentRequestDto.getContent())
                .build();

        commentRepository.save(comment);
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentRequestDto.getCommentSeq())
                .orElseThrow(() -> new CommentNotFoundException("댓글이"));
        comment.change(commentRequestDto.getContent());
    }

    public void deleteByCommentSeq(Long commentSeq) {
        Comment comment = commentRepository.findById(commentSeq)
                .orElseThrow(() -> new CommentNotFoundException("댓글이"));
        commentRepository.deleteById(commentSeq);
    }
}
