package com.google.cloud.language.automl.entity.extraction.samples;

// [START automl_natural_language_entity_list_operations_status]
import com.google.cloud.automl.v1beta1.AutoMlClient;
import com.google.cloud.automl.v1beta1.LocationName;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.Operation;
import java.io.IOException;

class ListOperationsStatus {

  static void listOperationsStatus(String projectId, String computeRegion, String filter)
      throws IOException {
    // String projectId = "YOUR_PROJECT_ID";
    // String computeRegion = "YOUR_COMPUTE_REGION";
    // String filter = "YOUR_FILTER_EXPRESSION";

    // Instantiates a client.
    AutoMlClient client = AutoMlClient.create();

    // A resource that represents Google Cloud Platform location.
    LocationName projectLocation = LocationName.of(projectId, computeRegion);

    // Create list operations request.
    ListOperationsRequest listrequest =
        ListOperationsRequest.newBuilder()
            .setName(projectLocation.toString())
            .setFilter(filter)
            .build();

    // List all the operations names available in the region by applying filter.
    for (Operation operation :
        client.getOperationsClient().listOperations(listrequest).iterateAll()) {
      // Display operation details.
      System.out.println(String.format("Operation details:"));
      System.out.println(String.format("\tName: %s", operation.getName()));
      System.out.println(String.format("\tMetadata:"));
      System.out.println(String.format("\t\tType Url: %s", operation.getMetadata().getTypeUrl()));
      System.out.println(
          String.format(
              "\t\tValue: %s",
              operation.getMetadata().getValue().toStringUtf8().replace("\n", "")));

      System.out.println(String.format("\tDone: %s", operation.getDone()));
      if (operation.hasResponse()) {
        System.out.println("\tResponse:");
        System.out.println(String.format("\t\tType Url: %s", operation.getResponse().getTypeUrl()));
        System.out.println(
            String.format(
                "\t\tValue: %s",
                operation.getResponse().getValue().toStringUtf8().replace("\n", "")));
      }
      if (operation.hasError()) {
        System.out.println("\tResponse:");
        System.out.println(String.format("\t\tError code: %s", operation.getError().getCode()));
        System.out.println(
            String.format("\t\tError message: %s", operation.getError().getMessage()));
      }
    }
  }
}
// [END automl_natural_language_entity_list_operations_status]