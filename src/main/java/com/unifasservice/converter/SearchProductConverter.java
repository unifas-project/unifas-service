package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ImgResponse;
import com.unifasservice.dto.payload.response.SearchProduct;
import com.unifasservice.dto.payload.response.VariantResponse;
import com.unifasservice.entity.Product;
import com.unifasservice.entity.ProductImage;
import com.unifasservice.entity.Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SearchProductConverter implements Function<List<Product>, List<SearchProduct>> {
    private final Function<List<ProductImage>,List<ImgResponse>> imgFunction;
    private final CategoryConverter categoryConverter;
    private final SubCategoryConverter subCategoryConverter;
    private final Function<List<Variant>,List<VariantResponse>> variantFunction;
    @Override
    public List<SearchProduct> apply(List<Product> product) {
        List<SearchProduct> searchProductList = new ArrayList<>();
        for (Product element : product){
            SearchProduct searchProduct = new SearchProduct();
            BeanUtils.copyProperties(element,searchProduct);
            searchProduct.setImgResponseList(imgFunction.apply(element.getImageProductList()));
            searchProduct.setCategoryResponse(categoryConverter.categoryToCategoryResponse(element.getCategory()));
            searchProduct.setSubCategoryResponse(subCategoryConverter.subCategoryToSubCategoryResponse(element.getSubCategory()));
            searchProduct.setVariantResponseList(variantFunction.apply(element.getVariantList()));
            searchProductList.add(searchProduct);
        }
        return searchProductList;
    }
}
