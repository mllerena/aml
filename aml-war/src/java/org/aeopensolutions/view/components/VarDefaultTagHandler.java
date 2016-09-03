package org.aeopensolutions.view.components;

import java.io.IOException;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException; 
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class VarDefaultTagHandler extends TagHandler{ 
	
	private final TagAttribute var;

	private final TagAttribute value;

	public VarDefaultTagHandler(TagConfig config) {
		super(config);
		this.var = this.getRequiredAttribute("var");
		this.value = this.getRequiredAttribute("value");
	}

	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException, FacesException, FaceletException, ELException {
		String paramStr = var.getValue(ctx);
		if (ctx.getVariableMapper().resolveVariable(paramStr) == null) {
			ValueExpression valueVe = value.getValueExpression(ctx,
					Object.class);
			ctx.getVariableMapper().setVariable(paramStr, valueVe);
		}
	}

}
