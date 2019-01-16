package com.foo.bar.hubjetcc.usecases;

import javax.validation.ConstraintViolation;

public class ValidationError {
    private final String message;
    private final String propertyName;

    public ValidationError(ConstraintViolation error) {
        this.propertyName = sanitizeHibernateValidator6NodeName(error);
        this.message = error.getMessage();
    }

    private String sanitizeHibernateValidator6NodeName(ConstraintViolation error) {
        return error.getPropertyPath().toString().replaceAll("\\.<.*>", "");
    }

    public ValidationError(String propertyName, String message) {
        this.propertyName = propertyName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
