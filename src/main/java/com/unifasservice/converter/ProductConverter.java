package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {
    private final CategoryConverter categoryConverter;
    private final SubCategoryConverter subCategoryConverter;
    private final ProductImageConverter productImageConverter;
    private final ReviewConverter reviewConverter;
    private final VariantConverter variantConverter;

    public ProductConverter(CategoryConverter categoryConverter, SubCategoryConverter subCategoryConverter, ProductImageConverter productImageConverter, ReviewConverter reviewConverter, VariantConverter variantConverter) {
        this.categoryConverter = categoryConverter;
        this.subCategoryConverter = subCategoryConverter;
        this.productImageConverter = productImageConverter;
        this.reviewConverter = reviewConverter;
        this.variantConverter = variantConverter;
    }
    public ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .star(product.getStar())
                .category(categoryConverter.categoryToCategoryResponse(product.getCategory()))
                .subCategory(subCategoryConverter.subCategoryToSubCategoryResponse(product.getSubCategory()))
                .imageProductList(productImageConverter.listProductImageToListProductImageResponse(product.getImageProductList()))
                .reviews(reviewConverter.listReviewToListReviewResponse(product.getReviews()))
                .variantList(variantConverter.listVariantToListVariantResponse(product.getVariantList()))
                .build();
    }
    public List<ProductResponse> listProductToListProductResponse(List<Product> productList) {
        List<ProductResponse> list = new ArrayList<>();
        for (Product product : productList) {
            list.add(productToProductResponse(product));
        }
        return list;
    }

//    public Product convertRequestToEntity(CreateProductRequest productRequest) {
//    }
//
//    public CreateProductResponse convertEntityToResponse(Product originProduct) {
//    }
}
