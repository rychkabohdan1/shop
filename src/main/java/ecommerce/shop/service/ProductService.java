package ecommerce.shop.service;

import ecommerce.shop.dto.ProductRequestDto;
import ecommerce.shop.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {

    ProductResponseDto getById(Long id);

    ProductResponseDto save(ProductRequestDto productRequestDto);

    void deleteById(Long id);

    ProductResponseDto update(ProductRequestDto productRequestDto, Long id);

    List<ProductResponseDto> findAll();
}
