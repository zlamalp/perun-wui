package cz.metacentrum.perun.wui.admin.client;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.metacentrum.perun.wui.client.PerunPresenter;
import org.gwtbootstrap3.client.ui.Navbar;
import org.gwtbootstrap3.client.ui.html.Div;

/**
 * Implementation of View for generic PerunPresenter used in this app.
 * It defines GUI layout and sets pages (other presenters/views) to prepared content slots.
 *
 * @author Pavel Zlámal <zlamal@cesnet.cz>
 */
public class PerunWuiView extends ViewImpl implements PerunWuiPresenter.MyView {

	interface PerunWuiViewUiBinder extends UiBinder<Widget, PerunWuiView> {}

	@UiField(provided = true)
	Navbar navbar;

	@UiField
	Div menu;

	@UiField
	Div pageContent;

	private static TopMenu topMenu;
	LeftMenuPresenter leftMenu;

	@Inject
	PerunWuiView(final PerunWuiViewUiBinder binder, LeftMenuPresenter leftMenu) {

		// CREATE LAYOUT
		topMenu = new TopMenu();
		this.leftMenu = leftMenu;
		navbar = topMenu.getWidget();
		initWidget(binder.createAndBindUi(this));
		menu.clear();
		menu.add(leftMenu.asWidget());

	}

	@Override
	public void setInSlot(final Object slot, final IsWidget content) {
		if (slot == PerunPresenter.SET_MAIN_CONTENT) {
			pageContent.clear();
			if (content != null) {
				pageContent.add(content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}

}
