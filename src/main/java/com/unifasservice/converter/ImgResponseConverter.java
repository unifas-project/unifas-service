package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ImgResponse;
import com.unifasservice.entity.ProductImage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class ImgResponseConverter implements Function<List<ProductImage>,List<ImgResponse>> {
    @Override
    public List<ImgResponse> apply(List<ProductImage> productImages) {
        List<ImgResponse> imgResponseList= new ArrayList<>();
        for (ProductImage element: productImages){
            ImgResponse imgResponse = new ImgResponse();
            BeanUtils.copyProperties(element,imgResponse);
            imgResponseList.add(imgResponse);
        }
        return imgResponseList;
    }
}
