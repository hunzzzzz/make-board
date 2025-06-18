package com.example.board.file.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.file.entity.File;

@Mapper
public interface FileMapper {
	void save(File file);
}
