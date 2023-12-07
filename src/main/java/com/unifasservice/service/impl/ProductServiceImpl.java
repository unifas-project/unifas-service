package com.unifasservice.service.impl;

import com.unifasservice.converter.ProductConverter;
import com.unifasservice.dto.payload.request.CreateProductRequest;
import com.unifasservice.dto.payload.response.CreateProductResponse;
import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.entity.Product;
import com.unifasservice.repository.ProductRepository;
import com.unifasservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductResponse> findAll() {
        List<ProductResponse> productResponseDTOs = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for ( Product product : products) {
                ProductResponse productResponseDTO = productConverter.productToProductResponseDTO(product);
                productResponseDTOs.add(productResponseDTO);
        }
        return productResponseDTOs;
    }

//    @Override
//    public CreateProductResponse createProduct(CreateProductRequest productRequest) {
//        Product product = productConverter.convertRequestToEntity(productRequest);
//        Product productDatabase = productRepository.save(product);
//
//
////        List<Variant> variantInputs = new ArrayList<>(productRequestDTO.getVariants().stream().map(item -> {
////            Variant variantInput = variantConverter.convertRequestToEntity(item);
////            variantInput.setProduct(productDatabase);
////            return variantInput;
////        }).toList());
////        List<Variant> variantsDatabase = variantRepository.findByProductOrderByColorAsc(productDatabase);
////        if (!variantsDatabase.isEmpty()) {
////            variantInputs.forEach(variantInput -> {
////                variantsDatabase.forEach(variantDatabase -> {
////                    if (Objects.equals(variantDatabase.getSize().getName(), variantInput.getSize().getName())
////                            && Objects.equals(variantDatabase.getColor().getName(), variantInput.getColor().getName())) {
////                        variantInput.setId(variantDatabase.getId());
////                    }
////                });
////            });
////            variantsDatabase.removeAll(variantInputs);
////            List<Variant> variantsToRemove = new ArrayList<>(variantsDatabase);
////            variantsToRemove.forEach(variant -> variant.setActive(false));
////            variantInputs.addAll(variantsToRemove);
////        }
////        variantRepository.saveAll(variantInputs);
//        Product originProduct = productRepository.save(productDatabase);
//        return productConverter.convertEntityToResponse(originProduct);
//    }
}
