package com.yuralubinec.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dto.BannerDTO;

/**
 * Implementation of {@link Validator} interface for additional checking {@link BannerDTO} instance. Checks the
 * format of BannerDTO's photo parameter.
 *
 */

@Component
public class BannerDTOValidator implements Validator  {

    @Override
    public boolean supports(Class<?> clazz) {
        
        return BannerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        BannerDTO bannerDTO = (BannerDTO) target;
        MultipartFile photo = bannerDTO.getPhoto();
        if (!photo.isEmpty() && !photo.getContentType().equals("image/jpeg")){
            errors.rejectValue("photo", "NotRightFormat.photo");       
        }    
        
    }

}
