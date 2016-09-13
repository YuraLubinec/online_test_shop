package com.yuralubinec.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dto.ItemDTO;

@Component
public class ItemDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        
        return ItemDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        ItemDTO item = (ItemDTO) target;
        MultipartFile photo = item.getPhoto();
        if (!photo.isEmpty() && !photo.getContentType().equals("image/jpeg")){
            errors.rejectValue("photo", "NotRightFormat.photo");       
        }    
    }
}
