package com.mingrn.springbootmybatis.core;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 定义Service 抽象类,直接实现基础 Service 接口
 * 便于ServiceImpl层直接继承该抽象类调用基础接口
 *
 * @author MinGRn <br > 2018/5/27 15:10
 */
public abstract class AbstractService<T, DTO extends T, PK extends Serializable> implements Service<T, DTO, PK> {

	@Autowired
	protected Mapper<T> mapper;

	@Override
	public void insert(T t) {
		mapper.insert(t);
	}

	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<T> select(T t) {
		return mapper.select(t);
	}
}
