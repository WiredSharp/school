package wiredsharp.study.api;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ErrorResponse {

   public String error;

   public ErrorResponse(String message) {
      super();
      this.error = message;
   }

   public static Response NotFound(String errorMessage) {
      return Response.status(404).header("Content-Type", MediaType.APPLICATION_JSON).entity(new ErrorResponse("no matching student")).build();
   }
}
