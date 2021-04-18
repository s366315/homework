package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class VehicleEntity extends BaseModifyEntity<Integer>{

}
