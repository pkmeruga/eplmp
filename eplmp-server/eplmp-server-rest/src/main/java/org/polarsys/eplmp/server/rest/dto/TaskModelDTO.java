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

import java.io.Serializable;

/**
 * @author Emmanuel Nhan
 */

@ApiModel(value="TaskModelDTO", description="This class is a representation of a {@link org.polarsys.eplmp.core.workflow.TaskModel} entity")
public class TaskModelDTO implements Serializable {

    @ApiModelProperty(value = "Task model num")
    private int num;

    @ApiModelProperty(value = "Task model title")
    private String title;

    @ApiModelProperty(value = "Task model instructions")
    private String instructions;

    @ApiModelProperty(value = "Task model assigned role")
    private RoleDTO role;

    @ApiModelProperty(value = "Task model duration")
    private int duration;

    public TaskModelDTO() {
    }

    public TaskModelDTO(int num, String title, String instructions, RoleDTO role, int duration) {
        this.num = num;
        this.title = title;
        this.instructions = instructions;
        this.role = role;
        this.duration = duration;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
