package com.tixis.chainable;

public abstract class ChainListener {
	protected ChainableComponent component;
	protected boolean updateOnInit;

	public ChainListener() {
	}

	public ChainListener(boolean updateOnInit) {
		this.updateOnInit = updateOnInit;
	}

	public abstract void update(Object value);

	public abstract void doUpdate();

	public ChainableComponent getComponent() {
		return component;
	}

	public void setComponent(ChainableComponent component) {
		this.component = component;
		if (updateOnInit) {
			doUpdate();
		}
	}

}
