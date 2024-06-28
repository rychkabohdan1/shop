package ecommerce.shop.service.impl;

import ecommerce.shop.dto.ProductRequestDto;
import ecommerce.shop.dto.ProductResponseDto;
import ecommerce.shop.mapper.ProductMapper;
import ecommerce.shop.model.Product;
import ecommerce.shop.repository.ProductRepository;
import ecommerce.shop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto getById(Long id) {
        return productMapper.toResponseDto(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product with id " + id)));
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        Product savedProduct = productRepository.save(productMapper.toModel(productRequestDto));
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product with id " + id));
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setUpc(productRequestDto.getUpc());
        product.setDescription(productRequestDto.getDescription());
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDto)
                .toList();
    }
}
