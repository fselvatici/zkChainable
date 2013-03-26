package com.tixis.chainable;

import java.util.List;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Textbox;

public class ChainableTextbox extends ChainableComponent {
	private Textbox component;

	public ChainableTextbox(AbstractComponent component) {
		super(component);
		this.component = (Textbox) component;
	}

	@Override
	public void update(Object value) {
		component.setValue((String) value);
	}

	@Override
	public void update(List<Object> value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue() {
		return component.getValue();
	}

	@Override
	protected void addEventlistener() {
		component.addEventListener(Events.ON_CHANGE, new OnChangeListener());
	}

	@Override
	public AbstractComponent getComponent() {
		return component;
	}

	@Override
	public void setComponent(AbstractComponent component) {
		this.component = (Textbox) component;
	}

}
