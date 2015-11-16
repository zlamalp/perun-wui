package cz.metacentrum.perun.wui.userprofile.client;

import com.google.gwt.core.client.EntryPoint;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import cz.metacentrum.perun.wui.client.PerunPlaceManager;
import cz.metacentrum.perun.wui.client.PerunPresenter;
import cz.metacentrum.perun.wui.client.resources.PerunResources;
import cz.metacentrum.perun.wui.client.utils.Utils;
import cz.metacentrum.perun.wui.pages.*;
import cz.metacentrum.perun.wui.userprofile.client.resources.PerunUserProfileResources;

/**
 * Entry point class and GWTP module for Perun WUI User profile.
 *
 * @author Pavel Zlámal <zlamal@cesnet.cz>
 */
public class PerunUserProfile extends AbstractPresenterModule implements EntryPoint {

	@Override
	protected void configure() {

		install(new DefaultModule(PerunPlaceManager.class));

		// Main Application must bind generic Presenter and custom View !!
		bindPresenter(PerunPresenter.class, PerunPresenter.MyView.class, PerunUserProfileView.class, PerunPresenter.MyProxy.class);

		// bind app-specific pages
		//bindPresenter(FormPresenter.class, FormPresenter.MyView.class, FormView.class, FormPresenter.MyProxy.class);
		//bindPresenter(AppsPresenter.class, AppsPresenter.MyView.class, AppsView.class, AppsPresenter.MyProxy.class);
		//bindPresenter(AppDetailPresenter.class, AppDetailPresenter.MyView.class, AppDetailView.class, AppDetailPresenter.MyProxy.class);

		// pre-defined places
		bindConstant().annotatedWith(DefaultPlace.class).to(PerunUserProfilePlaceTokens.PERSONAL);
		bindConstant().annotatedWith(ErrorPlace.class).to(PerunUserProfilePlaceTokens.NOT_FOUND);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(PerunUserProfilePlaceTokens.UNAUTHORIZED);

		// generic pages
		bindPresenter(NotAuthorizedPresenter.class, NotAuthorizedPresenter.MyView.class, NotAuthorizedView.class, NotAuthorizedPresenter.MyProxy.class);
		bindPresenter(NotFoundPresenter.class, NotFoundPresenter.MyView.class, NotFoundView.class, NotFoundPresenter.MyProxy.class);
		bindPresenter(LogoutPresenter.class, LogoutPresenter.MyView.class, LogoutView.class, LogoutPresenter.MyProxy.class);

	}

	@Override
	public void onModuleLoad() {

		// set default for Growl plugin
		Utils.getDefaultGrowlOptions().makeDefault();

		// ensure injecting custom CSS styles of PerunWui
		PerunResources.INSTANCE.gss().ensureInjected();

		PerunUserProfileResources.INSTANCE.gss().ensureInjected();
	}

}