package com.unifasservice.dto.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    protected String id;
    protected String name;
    protected String image;
    protected String category;
    protected boolean isShown;
//    private List<VariantDTO> variants;

    @Override
    public String toString() {
        return String.format("id = %s, name = %s, image = %s, category = %s, isShown = %b",
                getId(), getName(), getImage(), getCategory(), isShown());
    }
}
