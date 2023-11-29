package com.apautomation.modules.reimbursement.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReimbursementInfoList {
	
	@JsonProperty("reimbList")
	private List<ReimbursementInfo> reimbList;
}
