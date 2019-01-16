package com.foo.bar.hubjetcc.usecases;

import java.util.*;

public abstract class ServiceResponse {
    public static final NotFoundResponse NOT_FOUND = new NotFoundResponse();
    public abstract <T> T map(Mapper<T> mapper);

    public interface Mapper<T> {
        T mapOkResponse(OkResponse okResponse);

        T mapEntityCreatedResponse(EntityCreatedResponse entityCreated);

        T mapValidationErrorResponse(ValidationErrorResponse validationErrorResponse);

        T mapNotFoundResponse(NotFoundResponse notFoundResponse);

    }

    public static final class EntityCreatedResponse extends ServiceResponse {

        private final String entityName;
        private final String entityId;

        public EntityCreatedResponse(String entityName, String entityId) {
            this.entityName = entityName;
            this.entityId = entityId;
        }

        @Override
        public String toString() {
            return "EntityCreatedResponse{" +
                    "entityName='" + entityName + '\'' +
                    ", entityId='" + entityId + '\'' +
                    '}';
        }

        @Override
        public <T> T map(Mapper<T> mapper) {
            return mapper.mapEntityCreatedResponse(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EntityCreatedResponse that = (EntityCreatedResponse) o;
            return Objects.equals(entityName, that.entityName) &&
                    Objects.equals(entityId, that.entityId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(entityName, entityId);
        }

        public String getEntityName() {
            return entityName;
        }

        public String getEntityId() {
            return entityId;
        }
    }


    public static final class OkResponse extends ServiceResponse {
        public static final OkResponse Empty = new OkResponse(null);
        private final Object data;

        public OkResponse(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OkResponse that = (OkResponse) o;
            return Objects.equals(data, that.data);
        }

        @Override
        public int hashCode() {

            return Objects.hash(data);
        }

        @Override
        public <T> T map(Mapper<T> mapper) {
            return mapper.mapOkResponse(this);
        }

        @Override
        public String toString() {
            return "OkResponse{" +
                    "data=" + data +
                    "} " + super.toString();
        }
    }


    public static final class NotFoundResponse extends ServiceResponse {
        private NotFoundResponse() { }

        @Override
        public <T> T map(Mapper<T> mapper) {
            return mapper.mapNotFoundResponse(this);
        }
    }


    public static final class ValidationErrorResponse extends ServiceResponse {
        private final List<ValidationError> validationErrors;

        public ValidationErrorResponse(List<ValidationError> validationErrors) {
            this.validationErrors = new ArrayList(validationErrors);
        }

        public ValidationErrorResponse(ValidationError validationError) {
            this.validationErrors = Arrays.asList(validationError);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ValidationErrorResponse that = (ValidationErrorResponse) o;
            return Objects.equals(validationErrors, that.validationErrors);
        }

        @Override
        public int hashCode() {

            return Objects.hash(validationErrors);
        }

        @Override
        public <T> T map(Mapper<T> mapper) {
            return mapper.mapValidationErrorResponse(this);
        }

        public List<ValidationError> getValidationErrors() {
            return Collections.unmodifiableList(validationErrors);
        }
    }
}


