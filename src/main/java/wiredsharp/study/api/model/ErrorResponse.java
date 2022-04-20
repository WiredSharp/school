package wiredsharp.study.api.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterForReflection
public class ErrorResponse {

   public String error;

   public ErrorResponse(String message) {
      super();
      this.error = message;
   }

   public static Response notFound(String errorMessage) {
      return Response.status(404).header("Content-Type", MediaType.APPLICATION_JSON).entity(new ErrorResponse(errorMessage)).build();
   }

    public static Response serverError(String errorMessage) {
        return Response.serverError().header("Content-Type", MediaType.APPLICATION_JSON).entity(new ErrorResponse(errorMessage)).build();
    }
}
