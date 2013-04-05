/**
 * 
 */
package org.evosuite.setup;

import java.util.LinkedHashSet;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gordon Fraser
 * 
 */
public class CollectParameterTypesVisitor extends SignatureVisitor {

	private final static Logger logger = LoggerFactory.getLogger(CollectParameterTypesVisitor.class);

	private final Set<Type> classes = new LinkedHashSet<Type>();

	private boolean topLevel = true;
	
	public Set<Type> getClasses() {
		return classes;
	}

	/**
	 * @param api
	 */
	public CollectParameterTypesVisitor() {
		super(Opcodes.ASM4);
	}

	@Override
	public void visitFormalTypeParameter(String name) {
		logger.debug("  visitFormalTypeParameter(" + name + ")");
	}

	@Override
	public SignatureVisitor visitClassBound() {
		logger.debug("  visitClassBound()");
		return this;
	}

	@Override
	public SignatureVisitor visitInterfaceBound() {
		logger.debug("  visitInterfaceBound()");
		return this;
	}

	@Override
	public SignatureVisitor visitSuperclass() {
		logger.debug("  visitSuperclass()");
		return this;
	}

	@Override
	public SignatureVisitor visitInterface() {
		logger.debug("  visitInterface()");
		return this;
	}

	@Override
	public SignatureVisitor visitParameterType() {
		logger.debug("  visitParameterType()");
		topLevel = true;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.signature.SignatureVisitor#visitClassType(java.lang.String)
	 */
	@Override
	public void visitClassType(String name) {
		logger.debug("  visitClassType(" + name + ")");

		if(topLevel)
			topLevel = false;
		else
			classes.add(Type.getObjectType(name));
		
		super.visitClassType(name);
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.signature.SignatureVisitor#visitTypeVariable(java.lang.String)
	 */
	@Override
	public void visitTypeVariable(String name) {
		logger.debug("  visitTypeVariable(" + name + ")");

		super.visitTypeVariable(name);
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.signature.SignatureVisitor#visitTypeArgument()
	 */
	@Override
	public void visitTypeArgument() {
		logger.debug("  visitTypeArgument");
		super.visitTypeArgument();
	}
}