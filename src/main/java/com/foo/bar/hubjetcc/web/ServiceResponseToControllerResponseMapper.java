package com.foo.bar.hubjetcc.web;

import com.foo.bar.hubjetcc.usecases.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

public class ServiceResponseToControllerResponseMapper implements ServiceResponse.Mapper<ResponseEntity> {
    @Override
    public ResponseEntity mapOkResponse(ServiceResponse.OkResponse okResponse) {
        if(okResponse.getData() == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(okResponse.getData());
    }

    @Override
    public ResponseEntity mapEntityCreatedResponse(ServiceResponse.EntityCreatedResponse entityCreated) {
        UriComponents aa = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(entityCreated.getEntityName())
                .pathSegment(entityCreated.getEntityId()).build();
        return ResponseEntity.created(aa.toUri()).build();
    }

    @Override
    public ResponseEntity mapValidationErrorResponse(ServiceResponse.ValidationErrorResponse validationErrorResponse) {
        return null;
    }

    @Override
    public ResponseEntity mapNotFoundResponse(ServiceResponse.NotFoundResponse notFoundResponse) {
        return null;
    }
}
