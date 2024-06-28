package ecommerce.shop.mapper;

import ecommerce.shop.dto.ProductRequestDto;
import ecommerce.shop.dto.ProductResponseDto;
import ecommerce.shop.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .upc(product.getUpc())
                .build();
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .upc(productRequestDto.getUpc())
                .build();
    }
}
