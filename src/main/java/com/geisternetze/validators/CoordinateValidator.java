package com.geisternetze.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("coordinateValidator")
public class CoordinateValidator implements Validator<Double> {

    @Override
    public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException {
        String componentId = component.getId();

        if ("breitengrad".equals(componentId)) {
            if (value < -90 || value > 90) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiger Breitengrad", "Der Breitengrad muss zwischen -90 und 90 liegen.")
                );
            }
        } else if ("laengengrad".equals(componentId)) {
            if (value < -180 || value > 180) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiger Längengrad", "Der Längengrad muss zwischen -180 und 180 liegen.")
                );
            }
        }
    }
}
