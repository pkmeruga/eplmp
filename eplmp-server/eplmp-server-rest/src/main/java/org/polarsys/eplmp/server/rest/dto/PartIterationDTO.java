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


@ApiModel(value = "PartIterationDTO", description = "This class is a representation of a {@link org.polarsys.eplmp.core.product.PartIteration} entity")
public class PartIterationDTO implements Serializable {

    @ApiModelProperty(value = "Workspace id")
    private String workspaceId;

    @ApiModelProperty(value = "Part iteration")
    private int iteration;

    @ApiModelProperty(value = "Native CAD file")
    private BinaryResourceDTO nativeCADFile;

    @ApiModelProperty(value = "3D file URI")
    private String geometryFileURI;

    @ApiModelProperty(value = "Iteration note")
    private String iterationNote;

    @ApiModelProperty(value = "Part author")
    private UserDTO author;

    @ApiModelProperty(value = "Creation date")
    @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date creationDate;

    @ApiModelProperty(value = "Modification date")
    @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date modificationDate;

    @ApiModelProperty(value = "Checkin date")
    @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date checkInDate;

    @ApiModelProperty(value = "Part iteration attributes")
    private List<InstanceAttributeDTO> instanceAttributes;

    @ApiModelProperty(value = "Attribute templates for structure path data")
    private List<InstanceAttributeTemplateDTO> instanceAttributeTemplates;

    @ApiModelProperty(value = "List of children components")
    private List<PartUsageLinkDTO> components;

    @ApiModelProperty(value = "Part iteration linked documents")
    private List<DocumentRevisionDTO> linkedDocuments;

    @ApiModelProperty(value = "Part number")
    private String number;

    @ApiModelProperty(value = "Part name")
    private String name;

    @ApiModelProperty(value = "Part version")
    private String version;

    @ApiModelProperty(value = "Part attached files")
    private List<BinaryResourceDTO> attachedFiles;

    public PartIterationDTO() {
    }

    public PartIterationDTO(String pWorkspaceId, String pName, String pNumber, String pVersion, int pIteration) {
        workspaceId = pWorkspaceId;
        number = pNumber;
        name = pName;
        version = pVersion;
        iteration = pIteration;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public BinaryResourceDTO getNativeCADFile() {
        return nativeCADFile;
    }

    public void setNativeCADFile(BinaryResourceDTO nativeCADFile) {
        this.nativeCADFile = nativeCADFile;
    }

    public String getIterationNote() {
        return iterationNote;
    }

    public void setIterationNote(String iterationNote) {
        this.iterationNote = iterationNote;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public List<InstanceAttributeDTO> getInstanceAttributes() {
        return instanceAttributes;
    }

    public void setInstanceAttributes(List<InstanceAttributeDTO> instanceAttributes) {
        this.instanceAttributes = instanceAttributes;
    }

    public List<PartUsageLinkDTO> getComponents() {
        return components;
    }

    public void setComponents(List<PartUsageLinkDTO> components) {
        this.components = components;
    }

    public List<DocumentRevisionDTO> getLinkedDocuments() {
        return linkedDocuments;
    }

    public void setLinkedDocuments(List<DocumentRevisionDTO> linkedDocuments) {
        this.linkedDocuments = linkedDocuments;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InstanceAttributeTemplateDTO> getInstanceAttributeTemplates() {
        return instanceAttributeTemplates;
    }

    public void setInstanceAttributeTemplates(List<InstanceAttributeTemplateDTO> instanceAttributeTemplates) {
        this.instanceAttributeTemplates = instanceAttributeTemplates;
    }

    public List<BinaryResourceDTO> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(List<BinaryResourceDTO> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

    public String getGeometryFileURI() {
        return geometryFileURI;
    }

    public void setGeometryFileURI(String geometryFileURI) {
        this.geometryFileURI = geometryFileURI;
    }
}
