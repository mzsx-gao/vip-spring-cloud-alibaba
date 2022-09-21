package com.tuling.mall.feigndemo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVo  implements Serializable {

	private Integer userId;

	private String commodityCode;
	
	private Integer count;

	private Integer amount;

}