package com.patika.mapper;

import com.patika.model.entity.Product;
import com.patika.model.requestDto.ProductCreateDto;
import com.patika.model.responseDto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

    List<ProductDto> productListToProductDtoList(List<Product> products);

    ProductDto productToProductDto(Product product);
}
