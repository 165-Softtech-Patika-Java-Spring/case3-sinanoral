package com.patika.service;

import com.patika.dao.CommentDao;
import com.patika.mapper.CommentMapper;
import com.patika.model.entity.Comment;
import com.patika.model.entity.Product;
import com.patika.model.requestDto.CommentCreateDto;
import com.patika.model.responseDto.CommentDto;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
    private final CommentMapper mapper;

//    public DataResult<List<CommentDto>> getCommentsByUserId(Long id) {
//        List<Comment> comments = commentDao.getCommentsByUserId(id);
//        if(comments.isEmpty())
//            throw new NotFoundException("User did not have any comment");
//
//        List<CommentDto> commentDtoList = mapper.commentListToCommentDtoList(comments);
//        return new SuccessDataResult<>(commentDtoList);
//    }

    public DataResult<List<CommentDto>> getCommentsByProductId(Long id) {
        List<Comment> comments = commentDao.getCommentsByProductId(id);
        if(comments.isEmpty())
            throw new NotFoundException("Product did not have any comment");

        List<CommentDto> commentDtoList = mapper.commentListToCommentDtoList(comments);
        return new SuccessDataResult<>(commentDtoList);
    }

    public Result create(CommentCreateDto commentCreateDto) {
        Comment comment = mapper.commentCreateDtoToComment(commentCreateDto);
        commentDao.save(comment);
        return new SuccessResult("Comment Created!");
    }

    private void existsById(Long id) {
        boolean exist = commentDao.existsById(id);
        if (!exist)
            throw new NotFoundException("");
    }

    public Result deleteById(Long id) {
        existsById(id);
        commentDao.deleteById(id);
        return new SuccessResult("Comment deleted!");
    }
}
