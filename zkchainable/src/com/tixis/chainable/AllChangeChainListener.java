package com.tixis.chainable;

public abstract class AllChangeChainListener extends ChainListener {

	public AllChangeChainListener() {
		super();
	}

	public AllChangeChainListener(boolean updateOnInit) {
		super(updateOnInit);
	}

	@Override
	public void update(Object value) {
		boolean doUpdate = true;
		for (ChainableComponent p : component.getParents()) {
			if (!p.isChanged()) {
				doUpdate = false;
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
