package com.tixis.chainable;

import java.util.List;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class ChainableListbox extends ChainableComponent {
	private Listbox component;

	public ChainableListbox(AbstractComponent component) {
		super(component);
		this.component = (Listbox) component;
	}

	@Override
	public void update(Object value) {
		Listbox lb = (Listbox) component;
		for (Listitem it : lb.getItems()) {
			if (it.getValue().equals(value)) {
				it.setSelected(true);
				break;
			}
		}
	}

	@Override
	public void update(List<Object> value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue() {
		Listitem item = component.getSelectedItem();
		if (item != null) {
			return item.getValue();
		} else {
			return null;
		}
	}

	@Override
	protected void addEventlistener() {
		component.addEventListener(Events.ON_SELECT, new OnChangeListener());
	}

	@Override
	public AbstractComponent getComponent() {
		return component;
	}

	@Override
	public void setComponent(AbstractComponent component) {
		this.component = (Listbox) component;
	}

}
