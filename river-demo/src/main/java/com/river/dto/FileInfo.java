package com.river.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author riverplant
 *
 */
public class FileInfo {
   @ApiModelProperty(value = "file's path")
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FileInfo(String path) {
		super();
		this.path = path;
	}
	
	
	
}
