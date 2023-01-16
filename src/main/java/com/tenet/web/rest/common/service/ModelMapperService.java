package com.tenet.web.rest.common.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

	@Autowired
	private ModelMapper modelMapper;

	public Object convert(Object object, Class<?> type) {
		Object MapperObject = modelMapper.map(object, type);
		return MapperObject;

	}
}
