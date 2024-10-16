package com.audit.models.orm;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    private Long id;

    public <T, V> V mapDataToObject(T source, Class<V> targetClass) {
	try {
	    V target = targetClass.newInstance();
	    BeanUtils.copyProperties(source, target);
	    return target;
	} catch (BeansException | InstantiationException | IllegalAccessException ex) {
	    throw new RuntimeException(ex);
	}
    }

}
