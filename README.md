zkChainable
===========

ZK Chainable form components

Let you define form components like Textbox or Listbox and chain them to dinamically change their content depending on the others.

In the following example, when you change the first Texbox or select an item of the Listbox, the second Textbox is updated with the concatenation of the others:

    public void draw() {
  	    Window w = new Window("Chainable Test", "normal", false);
		w.setPage(page);
		Textbox tb1 = new Textbox("Textbox 1");
		Listbox lb1 = new Listbox();
		Textbox tb2 = new Textbox("");
		lb1.setWidth("150px");
		lb1.setMold("select");
		lb1.appendItem("Mar del Plata", "00");
		lb1.appendItem("Rosario", "01");
		lb1.appendItem("Córdoba", "02");
		lb1.appendItem("Mendoza", "03");
		lb1.setSelectedIndex(2);
		ChainableTextbox ctb1 = new ChainableTextbox(tb1);
		ChainableListbox ctb2 = new ChainableListbox(lb1);
		ChainableTextbox ctb3 = new ChainableTextbox(tb2);
		ctb1.addChild(ctb3);
		ctb2.addChild(ctb3);
		ctb3.addChainListener(new TbMultiChainListener());
		w.appendChild(tb1);
		w.appendChild(lb1);
		w.appendChild(tb2);
    }

    public class TbMultiChainListener extends AnyChangeChainListener {
		public TbMultiChainListener() {
			super(true);
		}

		@Override
		public void doUpdate() {
			ChainableComponent parent1 = component.getParents().get(0);
			ChainableComponent parent2 = component.getParents().get(1);
			if (parent1 != null && parent2 != null) {
				AbstractComponent cChild = component.getComponent();
				((Textbox) cChild).setValue(parent1.getValue() + "-"
						+ parent2.getValue());
			}
		}

    }
