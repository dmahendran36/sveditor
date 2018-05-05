package net.sf.sveditor.vhdl.core.parser;

import net.sf.sveditor.core.db.ISVDBChildItem;
import net.sf.sveditor.core.db.vhdl.VHEntityDecl;
import net.sf.sveditor.vhdl.core.parser.vhdlParser.Entity_declarationContext;
import net.sf.sveditor.vhdl.core.parser.vhdlParser.Entity_declarative_itemContext;
import net.sf.sveditor.vhdl.core.parser.vhdlParser.Entity_statementContext;

public class EntityFactory extends VHFactoryBase {
	
	public EntityFactory(VHFactories f) {
		super(f);
	}
	
	public ISVDBChildItem visit(Entity_declarationContext ctx) {
		VHEntityDecl entity = new VHEntityDecl(ctx.identifier(0).getText());
		ISVDBChildItem entity_header = ctx.entity_header().accept(this);
		
		if (entity_header != null) {
			entity.addChildItem(entity_header);
		}
		
		if (ctx.entity_declarative_part() != null) {
			for (Entity_declarative_itemContext di : 
				ctx.entity_declarative_part().entity_declarative_item()) {
			
				ISVDBChildItem it = di.accept(this);
				
				if (it != null) {
					entity.addChildItem(it);
				}
			}
		}
		
		if (ctx.entity_statement_part() != null) {
			for (Entity_statementContext si :
				ctx.entity_statement_part().entity_statement()) {
				
				ISVDBChildItem it = si.accept(this);
				
				if (it != null) {
					entity.addChildItem(it);
				}
			}
		}
		
		return entity;
	}
}
