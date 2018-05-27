package com.mingrn.springbootmybatis.core;


import java.io.Serializable;
import java.util.List;

/**
 * 基础 Service 接口
 * 因 repository 层直接继承通用mapper,因此这里简单定义些通用接口
 *
 * @author MinGRn <br > 2018/5/27 15:08
 */
public interface Service<T, DTO extends T, PK extends Serializable> {
	void insert(T t);

	List<T> selectAll();

	List<T> select(T t);
}
