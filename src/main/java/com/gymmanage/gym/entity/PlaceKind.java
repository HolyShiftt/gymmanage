package com.gymmanage.gym.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PlaceKind {

    private Integer id;

    private String name;

    private Integer managerId;

    private String managerName;
}
