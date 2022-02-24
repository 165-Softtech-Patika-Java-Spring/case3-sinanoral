package com.patika.service;

import com.patika.dao.ProductDao;
import com.patika.mapper.ProductMapper;
import com.patika.model.entity.Product;
import com.patika.model.requestDto.ProductCreateDto;
import com.patika.model.requestDto.ProductUpdateDto;
import com.patika.model.responseDto.ProductDto;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductMapper mapper;

    public DataResult<List<ProductDto>> getAll() {
        List<Product> products = productDao.findAll();
        List<ProductDto> productDtoList = mapper.productListToProductDtoList(products);
        return new SuccessDataResult<>(productDtoList);
    }

    public Result create(ProductCreateDto productCreateDto) {
        Product product = mapper.productCreateDtoToProduct(productCreateDto);
        productDao.save(product);
        return new SuccessResult("Product Created!");
    }

    public DataResult<ProductDto> getById(Long id) {
        Product product = productDao.findById(id).orElseThrow();
        ProductDto productDto = mapper.productToProductDto(product);
        return new SuccessDataResult<>(productDto);
    }

    private void existsById(Long id) {
        boolean exist = productDao.existsById(id);
        if (!exist)
            throw new NotFoundException("");
    }

    public Result deleteById(Long id) {
        existsById(id);
        productDao.deleteById(id);
        return new SuccessResult("Product deleted!");
    }

    @Transactional
    public Result updatePriceById(Long id, ProductUpdateDto productUpdateDto) {
        existsById(id);
        productDao.setPriceById(id, productUpdateDto.getPrice());
        return new SuccessResult("Product price updated");
    }
}
