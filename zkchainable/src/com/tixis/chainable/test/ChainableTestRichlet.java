package com.tixis.chainable.test;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Style;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.tixis.chainable.AnyChangeChainListener;
import com.tixis.chainable.ChainableComponent;
import com.tixis.chainable.ChainableListbox;
import com.tixis.chainable.ChainableTextbox;

public class ChainableTestRichlet extends GenericRichlet {

	public void service(Page page) throws Exception {
		Style style;
		style = new Style("/css/reportesq.css");
		style.setPage(page);
		style = new Style("/css/button.css");
		style.setPage(page);
		Window w = new Window("Grid", "normal", false);
		w.setPage(page);

		Textbox tb1 = new Textbox("HOLA1");
		Listbox lb1 = new Listbox();
		Textbox tb2 = new Textbox("");
		lb1.setWidth("150px");
		lb1.setMold("select");
		lb1.appendItem("Mar del Plata", "00");
		lb1.appendItem("Maipú", "01");
		lb1.appendItem("Miramar", "02");
		lb1.appendItem("Balcarce", "03");
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

	public class TbTextboxChainListener extends AnyChangeChainListener {

		public TbTextboxChainListener() {
			super();
		}

		@Override
		public void doUpdate() {
			ChainableComponent parent = component.getParents().get(0);
			if (parent != null) {
				AbstractComponent cParent = parent.getComponent();
				AbstractComponent cChild = component.getComponent();
				((Textbox) cChild).setValue(((Textbox) cParent).getValue()
						+ "-INGRESADO");
			}
		}

	}

	public class TbListboxChainListener extends AnyChangeChainListener {

		public TbListboxChainListener() {
			super();
		}

		@Override
		public void doUpdate() {
			ChainableComponent parent = component.getParents().get(0);
			if (parent != null) {
				AbstractComponent cParent = parent.getComponent();
				component.update(((Textbox) cParent).getValue());
			}
		}

	}

}
