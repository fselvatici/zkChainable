package com.tixis.chainable;

public abstract class AnyChangeChainListener extends ChainListener {
	public AnyChangeChainListener() {
		super();
	}

	public AnyChangeChainListener(boolean updateOnInit) {
		super(updateOnInit);
	}

	@Override
	public void update(Object value) {
		boolean doUpdate = false;
		for (ChainableComponent p : component.getParents()) {
			if (p.isChanged()) {
				doUpdate = true;
				break;
			}
		}
		if (doUpdate) {
			doUpdate();
			for (ChainableComponent p : component.getParents()) {
				p.setChanged(false);
			}
		}
	}

}
