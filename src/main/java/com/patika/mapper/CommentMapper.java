package com.patika.mapper;

import com.patika.model.entity.Comment;
import com.patika.model.entity.Product;
import com.patika.model.requestDto.CommentCreateDto;
import com.patika.model.responseDto.CommentDto;
import com.patika.service.ProductService;
import com.patika.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    List<CommentDto> commentListToCommentDtoList(List<Comment> comments);

    Comment commentCreateDtoToComment(CommentCreateDto commentCreateDto);
}
