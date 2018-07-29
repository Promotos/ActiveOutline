package de.promotos.activeoutline.builder.visitor;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageDeclaration;
import org.eclipse.jdt.core.JavaModelException;

public class JavaPackageVisitor extends AbstractVisitorJava {

    @Override
    protected void added(final ICompilationUnit cu) {
        upsertPackage(cu);
    }

    @Override
    protected void changed(final ICompilationUnit cu) {
        upsertPackage(cu);
    }

    @Override
    protected void removed(final ICompilationUnit cu) {

    }

    private void upsertPackage(final ICompilationUnit cu) {
        try {
            IPackageDeclaration[] packageDeclarations = cu.getPackageDeclarations();
            System.out.println(packageDeclarations);
        } catch (JavaModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
