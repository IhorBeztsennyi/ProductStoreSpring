package ua.goit.controller.propertyEditors;

import ua.goit.model.dto.VendorDto;

import java.beans.PropertyEditorSupport;
import java.util.Objects;
import java.util.UUID;

public class VendorDtoPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.isBlank()) {
            setValue(null);
            return;
        }
        final String[] split = text.split(",");
        VendorDto dto = new VendorDto();
        dto.setId(UUID.fromString(split[0].trim()));
        dto.setName(split[1].trim());
        setValue(dto);
    }
}
