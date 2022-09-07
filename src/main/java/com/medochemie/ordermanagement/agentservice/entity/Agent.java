package com.medochemie.ordermanagement.agentservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "Agent")
@ApiModel(value = "Agent detail", description = "Detail information for a particular Agent")
public class Agent {

    @Id
    @ApiModelProperty(name = "id", value = "Unique Agent Id", dataType = "String", example = "16098257253ndd")
    private String id;
    private String agentName;
    private String agentCode;
    private Address address;
    private String countryId;
    private String countryName;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

}
