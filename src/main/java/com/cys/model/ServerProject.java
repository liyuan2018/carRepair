package com.cys.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "server_project")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ServerProject")
public class ServerProject extends BaseModel {
	
	 @Column(name = "order_id", columnDefinition = "VARCHAR")
	 private String orderId;
	 
	 @Column(name = "project", columnDefinition = "VARCHAR")
	 private String project;
	 
	 @Column(name = "service_price", columnDefinition = "VARCHAR")
	 private String servicePrice;
	 
	 @Column(name = "commission", columnDefinition = "VARCHAR")
	 private String commission;
	 
	 @Column(name = "work_time_cost", columnDefinition = "VARCHAR")
	 private String workTimeCost;
	 
	 @Column(name = "product_name", columnDefinition = "VARCHAR")
	 private String productName;
	 
	 @Column(name = "yl1", columnDefinition = "VARCHAR")
	 private String yl1;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getWorkTimeCost() {
		return workTimeCost;
	}

	public void setWorkTimeCost(String workTimeCost) {
		this.workTimeCost = workTimeCost;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getYl1() {
		return yl1;
	}

	public void setYl1(String yl1) {
		this.yl1 = yl1;
	}

   
}