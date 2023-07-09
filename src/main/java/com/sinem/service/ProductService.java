package com.sinem.service;

import com.sinem.dto.request.ProductSaveRequestDto;
import com.sinem.mapper.IProductMapper;
import com.sinem.repository.IProductRepository;
import com.sinem.repository.entity.Product;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product, Long> {
    private final IProductRepository productRepository;
    public ProductService(IProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    public void save(ProductSaveRequestDto dto) {
        Product product = IProductMapper.INSTANCE.fromSaveDtoToProduct(dto);
        productRepository.save(product);
    }
}
