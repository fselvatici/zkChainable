package com.tixis.chainable;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public abstract class ChainableComponent implements IChainableComponent {
	public static final int ALL_CHANGED = 1;
	public static final int FOCUS_LOST = 2;
	public static final int ENTER_KEY_ON_LAST = 3;
	public static final int ANY_CHANGED = 4;

	protected List<ChainableComponent> childs;
	protected List<ChainableComponent> parents;

	protected int behavior;
	protected boolean changed = false;
	protected ChainListener listener;

	public ChainableComponent(AbstractComponent component) {
		this(component, ChainableComponent.ANY_CHANGED);
	}

	public ChainableComponent(AbstractComponent component, int behavior) {
		this.behavior = behavior;
		childs = new ArrayList<ChainableComponent>();
		parents = new ArrayList<ChainableComponent>();
		setComponent(component);
		addEventlistener();
	}

	protected abstract void addEventlistener();

	public void addChild(ChainableComponent component) {
		childs.add(component);
		component.addParent(this);
	}

	public void addParent(ChainableComponent component) {
		parents.add(component);
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public abstract AbstractComponent getComponent();

	public abstract void setComponent(AbstractComponent component);

	public void fireUpdate(Object value) {
		if (listener != null) {
			listener.update(value);
		}
		updateChilds();
	}

	public void updateChilds() {
		for (ChainableComponent c : childs) {
			c.fireUpdate(getValue());
		}
	}

	public class OnChangeListener implements EventListener<Event> {

		@Override
		public void onEvent(Event event) throws Exception {
			changed = true;
			updateChilds();
		}
	}

	public void addChainListener(ChainListener listener) {
		this.listener = listener;
		listener.setComponent(this);
	}

	public List<ChainableComponent> getChilds() {
		return childs;
	}

	public List<ChainableComponent> getParents() {
		return parents;
	}

}
