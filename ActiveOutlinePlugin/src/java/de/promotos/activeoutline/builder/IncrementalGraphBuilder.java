package de.promotos.activeoutline.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.annotation.Nullable;

import de.promotos.activeoutline.builder.visitor.DelegatingVisitor;

public class IncrementalGraphBuilder extends IncrementalProjectBuilder {

    public static final String BUILDER_ID = "ActiveOutlinePlugin.IncrementalGraphBuilder";
    private static final String TASK_NAME = "Build Active Outline Graph";

    @Override
    protected IProject @Nullable [] build(final int kind, @SuppressWarnings("rawtypes") final @Nullable Map args,
            final @Nullable IProgressMonitor monitor) throws CoreException {
        IProgressMonitor monitorSafe = getProgressMonitorSafe(monitor);
        if (kind == FULL_BUILD) {
            fullBuild(monitorSafe); 
        } else {
            final IResourceDelta delta = getDelta(getProject());
            if (delta == null) {
                fullBuild(monitorSafe);
            } else {
                incrementalBuild(delta, monitorSafe);
            }
        }
        return null;
    }

    protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask(TASK_NAME, IProgressMonitor.UNKNOWN);
            getProject().accept(new DelegatingVisitor(monitor));
        } finally {
            monitor.done();
        }
    }

    protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask(TASK_NAME, IProgressMonitor.UNKNOWN);
            delta.accept(new DelegatingVisitor(monitor));
        } finally {
            monitor.done();
        }
    }
    
    private final IProgressMonitor getProgressMonitorSafe(final @Nullable IProgressMonitor monitor) {
        if (monitor == null) {
            return new NullProgressMonitor();
        }
        return monitor;
    }

}
