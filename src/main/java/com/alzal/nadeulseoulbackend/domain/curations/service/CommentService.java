package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Comment;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CommentDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CommentNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CommentRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CurationRepository curationRepository;

    public void insertComment(CommentDto commentDto) {
        Curation curation = curationRepository.findById(commentDto.getCurationSeq())
                .orElseThrow(()-> new CurationNotFoundException("큐레이션이"));
        Comment comment = Comment.builder()
                .curation(curation)
                .memberSeq(commentDto.getMemberSeq())
                .content(commentDto.getContent())
                .build();
        commentRepository.save(comment);
    }

    public void updateComment(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getCommentSeq())
                .orElseThrow(() -> new CommentNotFoundException("댓글이"));
        comment.change(commentDto.getContent());
    }

    public void deleteByCommentSeq(Long commentSeq) {
        Comment comment = commentRepository.findById(commentSeq)
                .orElseThrow(() -> new CommentNotFoundException("댓글이"));
        commentRepository.deleteById(commentSeq);
    }
}
