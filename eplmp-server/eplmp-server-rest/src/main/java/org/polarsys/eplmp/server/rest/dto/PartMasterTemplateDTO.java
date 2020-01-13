/*******************************************************************************
  * Copyright (c) 2017-2019 DocDoku.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributors:
  *    DocDoku - initial API and implementation
  *******************************************************************************/

package org.polarsys.eplmp.server.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.polarsys.eplmp.core.util.DateUtils;

import javax.json.bind.annotation.JsonbDateFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@ApiModel(value="PartMasterTemplateDTO", description="This class is a representation of a {@link org.polarsys.eplmp.core.product.PartMasterTemplate} entity")
public class PartMasterTemplateDTO implements Serializable {

    @ApiModelProperty(value = "Workspace id")
    private String workspaceId;

    @ApiModelProperty(value = "Part template id")
    private String id;

    @ApiModelProperty(value = "Part template reference")
    private String reference;

    @ApiModelProperty(value = "Part template type")
    private String partType;

    @ApiModelProperty(value = "Part template author")
    private UserDTO author;

    @ApiModelProperty(value = "Part template creation date")
    @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date creationDate;

    @ApiModelProperty(value = "Part template modification date")
        @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date modificationDate;

    @ApiModelProperty(value = "Generate id flag")
    private boolean idGenerated;

    @ApiModelProperty(value = "Part template mask")
    private String mask;

    @ApiModelProperty(value = "Part template attached files")
    private String attachedFile;

    @ApiModelProperty(value = "Part template attributes")
    private List<InstanceAttributeTemplateDTO> attributeTemplates;

    @ApiModelProperty(value = "Part template structure path attributes")
    private List<InstanceAttributeTemplateDTO> attributeInstanceTemplates;

    @ApiModelProperty(value = "Attributes locked flag")
    private boolean attributesLocked;

    @ApiModelProperty(value = "Workflow to instantiate on part creation")
    private String workflowModelId;

    @ApiModelProperty(value = "Part template ACL")
    private ACLDTO acl;

    public PartMasterTemplateDTO() {
    }

    public PartMasterTemplateDTO(String workspaceId, String id, String partType) {
        this.workspaceId = workspaceId;
        this.id = id;
        this.partType = partType;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getWorkflowModelId() {
        return workflowModelId;
    }

    public void setWorkflowModelId(String workflowModelId) {
        this.workflowModelId = workflowModelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    public boolean isIdGenerated() {
        return idGenerated;
    }

    public void setIdGenerated(boolean idGenerated) {
        this.idGenerated = idGenerated;
    }

    public List<InstanceAttributeTemplateDTO> getAttributeInstanceTemplates() {
        return attributeInstanceTemplates;
    }

    public void setAttributeInstanceTemplates(List<InstanceAttributeTemplateDTO> attributeInstanceTemplates) {
        this.attributeInstanceTemplates = attributeInstanceTemplates;
    }

    public List<InstanceAttributeTemplateDTO> getAttributeTemplates() {
        return attributeTemplates;
    }

    public void setAttributeTemplates(List<InstanceAttributeTemplateDTO> attributeTemplates) {
        this.attributeTemplates = attributeTemplates;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isAttributesLocked() {
        return attributesLocked;
    }

    public void setAttributesLocked(boolean attributesLocked) {
        this.attributesLocked = attributesLocked;
    }

    public ACLDTO getAcl() {
        return acl;
    }

    public void setAcl(ACLDTO acl) {
        this.acl = acl;
    }
}
