package no.kantega.kriska.webcommando;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * 
 * @author kristofferskaret
 * 
 */
public class CommandLinePage extends WebPage {

	private static final long serialVersionUID = 9075966137730110013L;

	private final WebMarkupContainer commandos;
	private final List<Commando> commandoList;
	private Component text;

	public CommandLinePage(final PageParameters parameters) {
		commandos = new WebMarkupContainer("commandos");
		add(commandos.setOutputMarkupId(true));

		commandoList = new ArrayList<Commando>();

		ListView<Commando> commandosView = new ListView<Commando>("command",
				new PropertyModel<List<Commando>>(this, "commandoList")) {

			private static final long serialVersionUID = -1365775968245355834L;

			@Override
			protected void populateItem(ListItem<Commando> item) {
				final Commando p = item.getModelObject();
				item.add(new Label("input", p.getInput()));
				item.add(new Label("output", "" + p.getOutput()));
			}
		};

		commandos.add(commandosView);

		CommandoForm commentForm = new CommandoForm("commandoForm");
		add(commentForm);

		commentForm.add(new AjaxFormSubmitBehavior(commentForm, "onsubmit") {

			private static final long serialVersionUID = -403044747250203296L;

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AjaxCallDecorator() {

					private static final long serialVersionUID = 7943748004970748980L;

					@Override
					public CharSequence decorateScript(Component c,
							CharSequence script) {
						return script + "return false;";
					}
				};
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				target.add(commandos);
				target.add(text);

				target.appendJavaScript("document.getElementById('"
						+ text.getMarkupId() + "').focus();");
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
			}
		});

	}

	public final class CommandoForm extends Form<Commando> {
		public CommandoForm(String id) {
			super(id, new CompoundPropertyModel<Commando>(new Commando()));

			text = new TextField<String>("input").setOutputMarkupId(true);
			add(text);
		}

		private static final long serialVersionUID = 1L;

		@Override
		public final void onSubmit() {
			final Commando commando = getModelObject();
			final Commando newCommando = new Commando(commando.getInput(), "answer");
			commandoList.add(newCommando);
			commando.setInput("");
		}
	}
}
