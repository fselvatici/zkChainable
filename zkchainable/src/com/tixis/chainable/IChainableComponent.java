package com.tixis.chainable;

import java.util.List;

public interface IChainableComponent {
	public void update(Object value);

	public void update(List<Object> value);

	public Object getValue();
}
